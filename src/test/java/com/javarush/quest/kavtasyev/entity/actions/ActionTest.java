package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.SET_HEALTH_SCRIPT;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ActionTest
{
	@Spy
	StringBuilder htmlActionNotifications = new StringBuilder();
	@Mock
	User user;
	@Mock
	ThreadLocalRandom random;

	@Test
	void clearHtmlTexts()
	{
		Action action = Mockito.spy(new TurnOnTheBeacon());
		htmlActionNotifications.append("1234567890");
		action.htmlActionsNotifications = htmlActionNotifications;
		action.clearHtmlTexts();
		assertAll(() -> {
			Mockito.verify(htmlActionNotifications).delete(0, htmlActionNotifications.length());
			assertTrue(htmlActionNotifications.isEmpty());
		});
	}

	@Test
	void setHealth()
	{
		Mockito.doReturn(70).when(user).getHealth();
		Action action = Mockito.spy(new TurnOnTheBeacon());
		action.user = user;
		action.htmlActionsNotifications = htmlActionNotifications;
		String result = String.format(SET_HEALTH_SCRIPT, user.getHealth());
		action.setHealth();
		assertEquals(result, action.htmlActionsNotifications.toString());
	}

	@Test
	void setRandom()
	{
		Action action = new TurnOnTheBeacon();
		action.setRandom(random);
		assertSame(random, action.random);
	}

	@Test
	void getActionProperties()
	{
		Action action = new TurnOnTheBeacon();
		ActionProperties properties = action.getActionProperties();
		assertSame(properties, action.actionProperties);
	}
}