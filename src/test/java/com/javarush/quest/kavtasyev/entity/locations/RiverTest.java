package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Spring;
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

class RiverTest
{
	@Spy
	River spyRiver;

	@InjectMocks
	River river;

	@Mock
	ThreadLocalRandom random;
	@Mock
	Spring spring;
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
		spyRiver.setRandom(random);
		LocationProperties properties = spyRiver.getProperties();

		StringBuilder spyHtmlLocationText = Mockito.spy(spyRiver.htmlLocationText);
		StringBuilder spyHtmlLocationButtons = Mockito.spy(spyRiver.htmlLocationButtons);
		StringBuilder spyHtmlActionButtons = Mockito.spy(spyRiver.htmlActionButtons);
		StringBuilder spyHtmlAlerts = Mockito.spy(spyRiver.htmlAlerts);
		spyRiver.htmlLocationText = spyHtmlLocationText;
		spyRiver.htmlLocationButtons = spyHtmlLocationButtons;
		spyRiver.htmlActionButtons = spyHtmlActionButtons;
		spyRiver.htmlAlerts = spyHtmlAlerts;

		spyRiver.executeEvents(user);

		assertAll(() -> {
			assertSame(spyRiver, user.getLocation());
			Mockito.verify(spyRiver).formHtml();
			Mockito.verify(spyRiver).setHealth();
			Mockito.verify(spyRiver).generateHtml();
			Mockito.verify(spyHtmlLocationText).append(TEXT_RIVER);
			Mockito.verify(spyHtmlLocationButtons).append(RIVER_LOCATION_BUTTONS);
			Mockito.verify(spyHtmlActionButtons).append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);
			Mockito.verify(spyRiver).findOutThePlane(properties.findOutThePlaneProbability());
			Mockito.verify(spyRiver).addActionButtonSetTheSnare();
			Mockito.verify(spyRiver).addActionButtonCheckTheSnare();
			Mockito.verify(spyRiver).addActionButtonDrinkWaterFromRiver();
			Mockito.verify(spyRiver).addActionButtonFryTheFowl();
			Mockito.verify(spyRiver).addActionButtonFryTheFish();
			Mockito.verify(spyRiver).addActionButtonFishing();
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
		LocationProperties locationProperties = river.getProperties();

		river.executeEvents(user);

		assertAll(() -> {

			if (randomValue < locationProperties.findSpringProbability())
			{
				verify(spring, only()).findSpring(river);
			}
			else
				verify(spring, never()).findSpring(river);

			if (randomValue < locationProperties.snakeBiteProbability())
				verify(snake, only()).attack(user, river);
			else
				verify(snake, never()).attack(user, river);

			if (randomValue < locationProperties.predatorAttackProbability())
			{
				if (randomValue > 0.33)
					verify(jackal).attack(user, river);
				else
					verify(tiger).attack(user, river);
			}
			else
			{
				assertAll(() -> {
					verify(tiger, never()).attack(user, river);
					verify(jackal, never()).attack(user, river);
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
		river.setRandom(random);
		river.user = user;

		Method thePredatorAttacked = River.class.getDeclaredMethod("thePredatorAttacked", double.class);
		thePredatorAttacked.setAccessible(true);
		thePredatorAttacked.invoke(river, 1.0);
		thePredatorAttacked.setAccessible(false);

		assertAll(() -> {
			if (randomPredator > 0.33)
				verify(jackal, only()).attack(user, river);
			else
				verify(tiger, only()).attack(user, river);
		});
	}

}