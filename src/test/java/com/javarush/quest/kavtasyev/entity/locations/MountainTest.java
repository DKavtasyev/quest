package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;

class MountainTest
{
	@Spy
	Mountain spyMountain;

	@Mock
	ThreadLocalRandom random;

	@Test
	@ExtendWith(MockitoExtension.class)
	@DisplayName("Тестирование метода executeEvents(User user) класса Mountain на вызов функций")
	void executeEventsInTheMountainAndSetHTMLTexts_FunctionTest()
	{
		User user = new User();
		spyMountain.setRandom(random);
		LocationProperties properties = spyMountain.getProperties();

		StringBuilder spyHtmlLocationText = Mockito.spy(spyMountain.htmlLocationText);
		StringBuilder spyHtmlLocationButtons = Mockito.spy(spyMountain.htmlLocationButtons);
		StringBuilder spyHtmlActionButtons = Mockito.spy(spyMountain.htmlActionButtons);
		StringBuilder spyHtmlAlerts = Mockito.spy(spyMountain.htmlAlerts);
		spyMountain.htmlLocationText = spyHtmlLocationText;
		spyMountain.htmlLocationButtons = spyHtmlLocationButtons;
		spyMountain.htmlActionButtons = spyHtmlActionButtons;
		spyMountain.htmlAlerts = spyHtmlAlerts;

		spyMountain.executeEvents(user);

		assertAll(() -> {
			assertSame(spyMountain, user.getLocation());
			Mockito.verify(spyMountain).formHtml();
			Mockito.verify(spyMountain).setHealth();
			Mockito.verify(spyMountain).generateHtml();
			Mockito.verify(spyHtmlLocationText).append(TEXT_MOUNTAIN);
			Mockito.verify(spyHtmlLocationButtons).append(MOUNTAIN_LOCATION_BUTTONS);
			Mockito.verify(spyHtmlActionButtons).append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);
			Mockito.verify(spyMountain).findOutThePlane(properties.findOutThePlaneProbability());
			Mockito.verify(spyMountain).addActionButtonTurnOnTheBeacon();
			Mockito.verify(spyHtmlActionButtons).append(CLOSE_DIV_TAG);
			Mockito.verify(spyHtmlAlerts).append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
			Mockito.verify(spyHtmlAlerts, atLeast(1)).append(CLOSE_DIV_TAG);
		});
	}
}