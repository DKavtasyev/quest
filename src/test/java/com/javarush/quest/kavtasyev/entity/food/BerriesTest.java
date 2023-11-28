package com.javarush.quest.kavtasyev.entity.food;

import com.javarush.quest.kavtasyev.entity.locations.Location;
import com.javarush.quest.kavtasyev.entity.locations.Plain;
import org.junit.jupiter.api.Test;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class BerriesTest
{
	@Test
	@SuppressWarnings("all")
	void findBerries()
	{
		Berries berries = new Berries();
		Location location = new Plain();
		String actionButtons = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_EAT_FRUITS, BUTTON_EAT_FRUITS)).toString();
		String alerts = new StringBuilder().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_BERRIES)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();
		assertAll(() -> {
			assertTrue(location.getHtmlActionButtons().isEmpty());
			assertTrue(location.getHtmlAlerts().isEmpty());

			berries.findBerries(location);

			assertEquals(actionButtons, location.getHtmlActionButtons().toString());
			assertEquals(alerts, location.getHtmlAlerts().toString());
		});
	}
}