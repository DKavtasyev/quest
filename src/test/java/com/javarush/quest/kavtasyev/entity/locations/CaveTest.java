package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Spring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class CaveTest
{
	@Spy
	Cave spyCave;

	@InjectMocks
	Cave cave;

	@Mock
	ThreadLocalRandom random;
	@Spy
	Spring spring;

	@Test
	@ExtendWith(MockitoExtension.class)
	void executeEvents()
	{
		User user = new User();
		spyCave.setRandom(random);
		LocationProperties properties = spyCave.getProperties();

		StringBuilder spyHtmlLocationText = Mockito.spy(spyCave.htmlLocationText);
		StringBuilder spyHtmlLocationButtons = Mockito.spy(spyCave.htmlLocationButtons);
		StringBuilder spyHtmlActionButtons = Mockito.spy(spyCave.htmlActionButtons);
		StringBuilder spyHtmlAlerts = Mockito.spy(spyCave.htmlAlerts);
		spyCave.htmlLocationText = spyHtmlLocationText;
		spyCave.htmlLocationButtons = spyHtmlLocationButtons;
		spyCave.htmlActionButtons = spyHtmlActionButtons;
		spyCave.htmlAlerts = spyHtmlAlerts;

		spyCave.executeEvents(user);

		assertAll(() -> {
			assertSame(spyCave, user.getLocation());
			Mockito.verify(spyCave).formHtml();
			Mockito.verify(spyCave).setHealth();
			Mockito.verify(spyCave).generateHtml();
			Mockito.verify(spyHtmlLocationText).append(TEXT_CAVE);
			Mockito.verify(spyHtmlLocationButtons).append(CAVE_LOCATION_BUTTONS);
			Mockito.verify(spyHtmlActionButtons).append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);
			Mockito.verify(spyCave).getLost(properties.getLostProbability());
			Mockito.verify(spyHtmlActionButtons).append(CLOSE_DIV_TAG);
			Mockito.verify(spyHtmlAlerts).append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
			Mockito.verify(spyHtmlAlerts, atLeast(1)).append(CLOSE_DIV_TAG);
		});
	}

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@ValueSource(doubles = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0})
	void executeEvents(double randomValue)
	{
		User user = new User();

		Mockito.doReturn(randomValue).when(random).nextDouble();
		LocationProperties locationProperties = cave.getProperties();

		cave.executeEvents(user);

		assertAll(() -> {

			if (randomValue < locationProperties.findSpringProbability())
			{
				Mockito.verify(spring, only()).findSpring(cave);
			}
			else
			{
				Mockito.verify(spring, never()).findSpring(cave);
			}

		});
	}
}