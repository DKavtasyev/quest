package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.Machete;
import com.javarush.quest.kavtasyev.entity.tool.Beacon;
import com.javarush.quest.kavtasyev.entity.tool.CarBattery;
import org.junit.jupiter.api.DisplayName;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SettlementTest
{
	@Spy
	Settlement spySettlement;

	@InjectMocks
	Settlement settlement;

	@Mock
	ThreadLocalRandom random;
	@Mock
	CarBattery carBattery;
	@Mock
	Machete machete;

	@Test
	@ExtendWith(MockitoExtension.class)
	@DisplayName("Тестирование метода executeEvents(User user) класса Settlement на вызовы функций")
	void executeEventsInTheSettlementAndAddHTMLTexts_FunctionTest()
	{
		User user = new User();
		spySettlement.setRandom(random);

		StringBuilder spyHtmlLocationText = Mockito.spy(spySettlement.htmlLocationText);
		StringBuilder spyHtmlLocationButtons = Mockito.spy(spySettlement.htmlLocationButtons);
		StringBuilder spyHtmlActionButtons = Mockito.spy(spySettlement.htmlActionButtons);
		StringBuilder spyHtmlAlerts = Mockito.spy(spySettlement.htmlAlerts);
		spySettlement.htmlLocationText = spyHtmlLocationText;
		spySettlement.htmlLocationButtons = spyHtmlLocationButtons;
		spySettlement.htmlActionButtons = spyHtmlActionButtons;
		spySettlement.htmlAlerts = spyHtmlAlerts;

		spySettlement.executeEvents(user);

		assertAll(() -> {
			assertSame(spySettlement, user.getLocation());
			Mockito.verify(spySettlement).formHtml();
			Mockito.verify(spySettlement).setHealth();
			Mockito.verify(spySettlement).generateHtml();
			Mockito.verify(spyHtmlLocationText).append(TEXT_SETTLEMENT);
			Mockito.verify(spyHtmlLocationButtons).append(SETTLEMENT_LOCATION_BUTTONS);
			Mockito.verify(spyHtmlActionButtons).append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);
			Mockito.verify(spyHtmlActionButtons).append(CLOSE_DIV_TAG);
			Mockito.verify(spyHtmlAlerts).append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
			Mockito.verify(spyHtmlAlerts, atLeast(1)).append(CLOSE_DIV_TAG);
		});
	}

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@CsvSource({
			"0.0, false, true , false, false",
			"0.1, false, true , false, false",
			"0.2, false, true , false, false",
			"0.3, false, true , false, false",
			"0.4, false, true , false, false",
			"0.5, false, true , false, false",
			"0.6, false, true , false, false",
			"0.7, false, true , false, false",
			"0.8, false, true , false, false",
			"0.9, false, true , false, false",
			"1.0, false, true , false, false",

			"0.0, true , true , true , true ",
			"0.0, true , true , false, false",
			"0.0, true , false, true , false",
			"0.0, true , false, false, false",
			"0.0, false, true , true , false",
			"0.0, false, true , false, false",
			"0.0, false, false, true , false",
			"0.0, false, false, false, false"
	})
	@DisplayName("Тестирование метода executeEvents(User user) класса Settlement подстановкой параметров")
	void executeEventsInTheSettlementAndAddHTMLTexts_ParameterTest(
			double randomValue,

			boolean hasCarBattery,	boolean hasBeacon,	boolean beaconIsOn,
			boolean hasMachete)
	{
		User user = new User();
		if (hasCarBattery)
			user.getTools().add(new CarBattery());
		if (hasBeacon)
			user.getTools().add(new Beacon());
		if (hasMachete)
			user.getArms().add(new Machete());
		user.setTheBeaconIsOn(beaconIsOn);

		doReturn(randomValue).when(random).nextDouble();
		LocationProperties locationProperties = settlement.getProperties();

		settlement.executeEvents(user);

		assertAll(() -> {

			if (randomValue < locationProperties.findCarBatteryProbability())
			{
				if (!hasCarBattery && hasBeacon && !beaconIsOn)
				{
					verify(carBattery, only()).findCarBattery(settlement);
				}
				else
				{
					verify(carBattery, never()).findCarBattery(settlement);
				}
			}
			else
				verify(carBattery, never()).findCarBattery(settlement);

			if (randomValue < locationProperties.findMacheteProbability())
			{
				if (!hasMachete)
				{
					verify(machete, only()).findMachete(settlement);
				}
				else
				{
					verify(machete, never()).findMachete(settlement);
				}
			}
			else
			{
				verify(machete, never()).findMachete(settlement);
			}
		});
	}

	@ParameterizedTest
	@ValueSource(doubles = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
	@ExtendWith(MockitoExtension.class)
	@DisplayName("Тестирование метода becomePrisoner(double probability) класса Settlement")
	void ifBecomePrisonerThenAddHTMLText(double randomBecomePrisoner) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		User user = mock(User.class);
		doReturn(randomBecomePrisoner).when(random).nextDouble();
		settlement.setRandom(random);
		settlement.user = user;
		LocationProperties properties = settlement.getProperties();

		Method becomePrisoner = Settlement.class.getDeclaredMethod("becomePrisoner", double.class);
		becomePrisoner.setAccessible(true);
		String s1 = "1234567890";
		String s2 = "abcdefghijklmnopqrstuvwxyz";
		settlement.htmlLocationButtons.append(s1);
		settlement.htmlActionButtons.append(s2);
		becomePrisoner.invoke(settlement, properties.beCapturedProbability());
		becomePrisoner.setAccessible(false);

		assertAll(() -> {
			if (randomBecomePrisoner < properties.beCapturedProbability())
			{
				assertTrue(settlement.htmlLocationButtons.isEmpty());
				assertTrue(settlement.htmlActionButtons.isEmpty());
				assertEquals("<script>gameOverMessage(\"Тебя захватили в плен местные бандиты.\");</script>", settlement.htmlScripts.toString());
			}
			else
			{
				assertEquals(s1, settlement.htmlLocationButtons.toString());
				assertEquals(s2, settlement.htmlActionButtons.toString());
				assertTrue(settlement.htmlScripts.isEmpty());
			}
		});
	}
}