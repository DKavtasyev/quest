package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Berries;
import com.javarush.quest.kavtasyev.entity.predators.Predator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class PlainTest
{
	@Spy
	Plain spyPlain;

	@InjectMocks
	Plain plain;

	@Mock
	ThreadLocalRandom random;
	@Mock
	Berries berries;
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
		spyPlain.setRandom(random);
		LocationProperties properties = spyPlain.getProperties();

		StringBuilder spyHtmlLocationText = Mockito.spy(spyPlain.htmlLocationText);
		StringBuilder spyHtmlLocationButtons = Mockito.spy(spyPlain.htmlLocationButtons);
		StringBuilder spyHtmlActionButtons = Mockito.spy(spyPlain.htmlActionButtons);
		StringBuilder spyHtmlAlerts = Mockito.spy(spyPlain.htmlAlerts);
		spyPlain.htmlLocationText = spyHtmlLocationText;
		spyPlain.htmlLocationButtons = spyHtmlLocationButtons;
		spyPlain.htmlActionButtons = spyHtmlActionButtons;
		spyPlain.htmlAlerts = spyHtmlAlerts;

		spyPlain.executeEvents(user);

		assertAll(() -> {
			assertSame(spyPlain, user.getLocation());
			Mockito.verify(spyPlain).formHtml();
			Mockito.verify(spyPlain).setHealth();
			Mockito.verify(spyPlain).generateHtml();
			Mockito.verify(spyHtmlLocationText).append(TEXT_PLAIN);
			Mockito.verify(spyHtmlLocationButtons).append(PLAIN_LOCATION_BUTTONS);
			Mockito.verify(spyHtmlActionButtons).append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);
			Mockito.verify(spyPlain).findOutThePlane(properties.findOutThePlaneProbability());
			Mockito.verify(spyPlain).addActionButtonSetTheSnare();
			Mockito.verify(spyPlain).addActionButtonCheckTheSnare();
			Mockito.verify(spyPlain).addActionButtonFryTheFowl();
			Mockito.verify(spyPlain).addActionButtonFryTheFish();
			Mockito.verify(spyHtmlActionButtons).append(CLOSE_DIV_TAG);
			Mockito.verify(spyHtmlAlerts).append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
			Mockito.verify(spyHtmlAlerts, atLeast(1)).append(CLOSE_DIV_TAG);
		});
	}

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@ValueSource(doubles = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
	void executeEvents(double randomValue)
	{
		User user = new User();

		doReturn(randomValue).when(random).nextDouble();
		LocationProperties locationProperties = plain.getProperties();

		plain.executeEvents(user);

		assertAll(() -> {

			if (randomValue < locationProperties.findBerriesProbability())
			{
				verify(berries, only()).findBerries(plain);
			}
			else
				verify(berries, never()).findBerries(plain);

			if (randomValue < locationProperties.snakeBiteProbability())
				verify(snake, only()).attack(user, plain);
			else
				verify(snake, never()).attack(user, plain);

			if (randomValue < locationProperties.predatorAttackProbability())
			{
				if (randomValue > 0.33)
					verify(jackal).attack(user, plain);
				else
					verify(tiger).attack(user, plain);
			}
			else
			{
				assertAll(() -> {
					verify(tiger, never()).attack(user, plain);
					verify(jackal, never()).attack(user, plain);
				});
			}
		});
	}

	@ParameterizedTest
	@ValueSource(doubles = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
	@ExtendWith(MockitoExtension.class)
	void thePredatorAttacked(double randomPredator) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		User user = mock(User.class);
		doReturn(randomPredator).when(random).nextDouble();
		plain.setRandom(random);
		plain.user = user;

		Method thePredatorAttacked = Plain.class.getDeclaredMethod("thePredatorAttacked", double.class);
		thePredatorAttacked.setAccessible(true);
		thePredatorAttacked.invoke(plain, 1.0);
		thePredatorAttacked.setAccessible(false);

		assertAll(() -> {
			if (randomPredator > 0.33)
				verify(jackal, only()).attack(user, plain);
			else
				verify(tiger, only()).attack(user, plain);
		});
	}
}