package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.tool.Beacon;
import org.junit.jupiter.api.DisplayName;
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

class FarBeachTest
{
	@Spy
	FarBeach spyFarBeach;

	@InjectMocks
	FarBeach farBeach;

	@Mock
	ThreadLocalRandom random;
	@Spy
	Beacon beacon;

	@Test
	@ExtendWith(MockitoExtension.class)
	@DisplayName("Тестирование метода executeEvents(User user) класса FarBeach на вызовы функций")
	void executeEventsOnTheFarBeachThenSetHTML_FunctionTest()
	{
		User user = new User();
		spyFarBeach.setRandom(random);
		StringBuilder spyHtmlLocationText = Mockito.spy(spyFarBeach.htmlLocationText);
		StringBuilder spyHtmlLocationButtons = Mockito.spy(spyFarBeach.htmlLocationButtons);
		StringBuilder spyHtmlActionButtons = Mockito.spy(spyFarBeach.htmlActionButtons);
		StringBuilder spyHtmlAlerts = Mockito.spy(spyFarBeach.htmlAlerts);
		spyFarBeach.htmlLocationText = spyHtmlLocationText;
		spyFarBeach.htmlLocationButtons = spyHtmlLocationButtons;
		spyFarBeach.htmlActionButtons = spyHtmlActionButtons;
		spyFarBeach.htmlAlerts = spyHtmlAlerts;

		spyFarBeach.executeEvents(user);

		assertAll(() -> {
			assertSame(spyFarBeach, user.getLocation());
			Mockito.verify(spyFarBeach).formHtml();
			Mockito.verify(spyFarBeach).setHealth();
			Mockito.verify(spyFarBeach).generateHtml();
			Mockito.verify(spyHtmlLocationText).append(TEXT_FAR_BEACH);
			Mockito.verify(spyHtmlLocationButtons).append(FAR_BEACH_LOCATION_BUTTONS);
			Mockito.verify(spyHtmlActionButtons).append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);
			Mockito.verify(spyFarBeach).addActionButtonFryTheFowl();
			Mockito.verify(spyFarBeach).addActionButtonFryTheFish();
			Mockito.verify(spyHtmlActionButtons).append(CLOSE_DIV_TAG);
			Mockito.verify(spyHtmlAlerts).append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
			Mockito.verify(spyHtmlAlerts, atLeast(1)).append(CLOSE_DIV_TAG);
		});
	}

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@CsvSource({
			"0.0, false, false",
			"0.1, false, false",
			"0.2, false, false",
			"0.3, false, false",
			"0.4, false, false",
			"0.5, false, false",
			"0.6, false, false",
			"0.7, false, false",
			"0.8, false, false",
			"0.9, false, false",
			"1.0, false, false",

			"0.0, true , true ",
			"0.0, true , false",
			"0.0, false, true ",
			"0.0, false, false"})
	@DisplayName("Тестирование метода executeEvents(User user) класса FarBeach подстановкой параметров")
	void executeEvents(double randomValue, boolean hasBeacon, boolean foundBeacon)
	{
		User user = new User();

		if (hasBeacon)
			user.getTools().add(new Beacon());

		user.setFoundBeacon(foundBeacon);

		doReturn(randomValue).when(random).nextDouble();
		LocationProperties locationProperties = farBeach.getProperties();

		farBeach.executeEvents(user);

		assertAll(() -> {

			if (randomValue < locationProperties.findBeaconProbability())
			{
				if (!hasBeacon && !foundBeacon)
				{
					verify(beacon, only()).findBeacon(user, farBeach);
				}
				else
				{
					verify(beacon, never()).findBeacon(user, farBeach);
				}
			}
			else
			{
				verify(beacon, never()).findBeacon(user, farBeach);
			}
		});
	}
}