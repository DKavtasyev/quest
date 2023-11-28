package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.Spear;
import com.javarush.quest.kavtasyev.entity.arms.Truncheon;
import com.javarush.quest.kavtasyev.entity.food.Fruits;
import com.javarush.quest.kavtasyev.entity.food.Spring;
import com.javarush.quest.kavtasyev.entity.predators.Predator;
import com.javarush.quest.kavtasyev.entity.tool.Rope;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

class ForestTest
{
	@Spy
	Forest spyForest;

	@InjectMocks
	Forest forest;

	@Mock
	ThreadLocalRandom random;
	@Mock
	Rope rope;
	@Mock
	Truncheon truncheon;
	@Mock
	Spear spear;
	@Mock
	Spring spring;
	@Mock
	Fruits fruits;
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
		spyForest.setRandom(random);
		LocationProperties properties = spyForest.getProperties();

		StringBuilder spyHtmlLocationText = Mockito.spy(spyForest.htmlLocationText);
		StringBuilder spyHtmlLocationButtons = Mockito.spy(spyForest.htmlLocationButtons);
		StringBuilder spyHtmlActionButtons = Mockito.spy(spyForest.htmlActionButtons);
		StringBuilder spyHtmlAlerts = Mockito.spy(spyForest.htmlAlerts);
		spyForest.htmlLocationText = spyHtmlLocationText;
		spyForest.htmlLocationButtons = spyHtmlLocationButtons;
		spyForest.htmlActionButtons = spyHtmlActionButtons;
		spyForest.htmlAlerts = spyHtmlAlerts;

		spyForest.executeEvents(user);

		assertAll(() -> {
			assertSame(spyForest, user.getLocation());
			Mockito.verify(spyForest).formHtml();
			Mockito.verify(spyForest).setHealth();
			Mockito.verify(spyForest).generateHtml();
			Mockito.verify(spyHtmlLocationText).append(TEXT_FOREST);
			Mockito.verify(spyHtmlLocationButtons).append(FOREST_LOCATION_BUTTONS);
			Mockito.verify(spyHtmlActionButtons).append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);
			Mockito.verify(spyForest).getLost(properties.getLostProbability());
			Mockito.verify(spyForest).findOutThePlane(properties.findOutThePlaneProbability());
			Mockito.verify(spyForest).addActionButtonSetTheSnare();
			Mockito.verify(spyForest).addActionButtonCheckTheSnare();
			Mockito.verify(spyForest).addActionButtonFryTheFowl();
			Mockito.verify(spyForest).addActionButtonFryTheFish();
			Mockito.verify(spyHtmlActionButtons).append(CLOSE_DIV_TAG);
			Mockito.verify(spyHtmlAlerts).append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
			Mockito.verify(spyHtmlAlerts, atLeast(1)).append(CLOSE_DIV_TAG);
		});
	}

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@CsvSource({
			"0.0, false, false, false, false",
			"0.1, false, false, false, false",
			"0.2, false, false, false, false",
			"0.3, false, false, false, false",
			"0.4, false, false, false, false",
			"0.5, false, false, false, false",
			"0.6, false, false, false, false",
			"0.7, false, false, false, false",
			"0.8, false, false, false, false",
			"0.9, false, false, false, false",
			"1.0, false, false, false, false",

			"0.0, true , true , true , true ",
			"0.0, false, true , false, false",
			"0.0, false, false, true , false",
			"0.0, false, false, false, false",
			"0.0, false, false, false, true "
	})
	void executeEvents(
			double randomValue,

			boolean hasRope,	boolean hasTruncheon,
			boolean hasSpear,	boolean truncheonInsteadOfSpear)
	{
		User user = new User();
		if (hasRope)
			user.getTools().add(new Rope());
		if (hasTruncheon)
			user.getArms().add(new Truncheon());
		if (hasSpear)
			user.getArms().add(new Spear());

		lenient().when(random.nextBoolean()).thenReturn(truncheonInsteadOfSpear);
		doReturn(randomValue).when(random).nextDouble();
		LocationProperties locationProperties = forest.getProperties();

		forest.executeEvents(user);

		assertAll(() -> {

			if (randomValue < locationProperties.findRopeProbability())
			{
				if (!hasRope)
				{
					verify(rope, only()).findRope(user, forest);
				}
				else
				{
					verify(rope, never()).findRope(user, forest);
				}
			}
			else
				verify(rope, never()).findRope(user, forest);

			if (randomValue < locationProperties.findTruncheonOrSpearProbability())
			{
				if (!hasTruncheon && !hasSpear)
				{
					if (truncheonInsteadOfSpear)
						verify(truncheon, only()).findTruncheon(user, forest);
					else
						verify(spear, only()).findSpear(user, forest);
				}
				else
				{
					verify(truncheon, never()).findTruncheon(user, forest);
					verify(spear, never()).findSpear(user, forest);
				}
			}
			else
			{
				verify(truncheon, never()).findTruncheon(user, forest);
				verify(spear, never()).findSpear(user, forest);
			}

			if (randomValue < locationProperties.findSpringProbability())
			{
				verify(spring, only()).findSpring(forest);
			}
			else
				verify(spring, never()).findSpring(forest);

			if (randomValue < locationProperties.findFruitsProbability())
			{
				verify(fruits, only()).findFruits(forest);
			}
			else
				verify(fruits, never()).findFruits(forest);

			if (randomValue < locationProperties.snakeBiteProbability())
				verify(snake, only()).attack(user, forest);
			else
				verify(snake, never()).attack(user, forest);

			if (randomValue < locationProperties.predatorAttackProbability())
			{
				if (randomValue > 0.33)
					verify(jackal).attack(user, forest);
				else
					verify(tiger).attack(user, forest);
			}
			else
			{
				assertAll(() -> {
					verify(tiger, never()).attack(user, forest);
					verify(jackal, never()).attack(user, forest);
				});
			}
		});
	}

	@ParameterizedTest
	@ValueSource(doubles = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
	@ExtendWith(MockitoExtension.class)
	void thePredatorAttacked(double randomPredator) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		User user = Mockito.mock(User.class);
		doReturn(randomPredator).when(random).nextDouble();
		forest.setRandom(random);
		forest.user = user;

		Method thePredatorAttacked = Forest.class.getDeclaredMethod("thePredatorAttacked", double.class);
		thePredatorAttacked.setAccessible(true);
		thePredatorAttacked.invoke(forest, 1.0);
		thePredatorAttacked.setAccessible(false);

		assertAll(() -> {
			if (randomPredator > 0.33)
				verify(jackal, only()).attack(user, forest);
			else
				verify(tiger, only()).attack(user, forest);
		});
	}
}