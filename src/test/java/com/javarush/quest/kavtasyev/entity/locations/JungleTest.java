package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.predators.Predator;
import com.javarush.quest.kavtasyev.entity.tool.Rope;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

class JungleTest
{
	@Spy
	Jungle spyJungle;

	@InjectMocks
	Jungle jungle;

	@Mock
	ThreadLocalRandom random;
	@Mock
	Rope rope;
	@Mock
	Predator tiger;
	@Mock
	Predator jackal;
	@Mock
	Predator snake;

	@Test
	@ExtendWith(MockitoExtension.class)
	void executeEvents()
	{
		User user = new User();
		spyJungle.setRandom(random);
		LocationProperties properties = spyJungle.getProperties();

		StringBuilder spyHtmlLocationText = Mockito.spy(spyJungle.htmlLocationText);
		StringBuilder spyHtmlLocationButtons = Mockito.spy(spyJungle.htmlLocationButtons);
		StringBuilder spyHtmlActionButtons = Mockito.spy(spyJungle.htmlActionButtons);
		StringBuilder spyHtmlAlerts = Mockito.spy(spyJungle.htmlAlerts);
		spyJungle.htmlLocationText = spyHtmlLocationText;
		spyJungle.htmlLocationButtons = spyHtmlLocationButtons;
		spyJungle.htmlActionButtons = spyHtmlActionButtons;
		spyJungle.htmlAlerts = spyHtmlAlerts;

		spyJungle.executeEvents(user);

		assertAll(() -> {
			assertSame(spyJungle, user.getLocation());
			Mockito.verify(spyJungle).formHtml();
			Mockito.verify(spyJungle).setHealth();
			Mockito.verify(spyJungle).generateHtml();
			Mockito.verify(spyHtmlLocationText).append(TEXT_JUNGLE);
			Mockito.verify(spyHtmlLocationButtons).append(JUNGLE_LOCATION_BUTTONS);
			Mockito.verify(spyHtmlActionButtons).append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);
			Mockito.verify(spyJungle).getLost(properties.getLostProbability());
			Mockito.verify(spyJungle).findOutThePlane(properties.findOutThePlaneProbability());
			Mockito.verify(spyHtmlActionButtons).append(CLOSE_DIV_TAG);
			Mockito.verify(spyHtmlAlerts).append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
			Mockito.verify(spyHtmlAlerts, atLeast(1)).append(CLOSE_DIV_TAG);
		});
	}

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@CsvSource({
			"0.0, false",
			"0.1, false",
			"0.2, false",
			"0.3, false",
			"0.4, false",
			"0.5, false",
			"0.6, false",
			"0.7, false",
			"0.8, false",
			"0.9, false",
			"1.0, false",

			"0.0, true "
	})
	void executeEvents(double randomValue, boolean hasRope)
	{
		User user = new User();
		if (hasRope)
			user.getTools().add(new Rope());

		doReturn(randomValue).when(random).nextDouble();
		LocationProperties locationProperties = jungle.getProperties();

		jungle.executeEvents(user);

		assertAll(() -> {

			if (randomValue < locationProperties.findRopeProbability())
			{
				if (!hasRope)
				{
					verify(rope, only()).findRope(user, jungle);
				}
				else
				{
					verify(rope, never()).findRope(user, jungle);
				}
			}
			else
				verify(rope, never()).findRope(user, jungle);

			if (randomValue < locationProperties.snakeBiteProbability())
				verify(snake, only()).attack(user, jungle);
			else
				verify(snake, never()).attack(user, jungle);

			if (randomValue < locationProperties.predatorAttackProbability())
			{
				if (randomValue > 0.33)
					verify(jackal).attack(user, jungle);
				else
					verify(tiger).attack(user, jungle);
			}
			else
			{
				assertAll(() -> {
					verify(tiger, never()).attack(user, jungle);
					verify(jackal, never()).attack(user, jungle);
				});
			}
		});
	}


}