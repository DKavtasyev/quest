package com.javarush.quest.kavtasyev.entity.food;

import com.javarush.quest.kavtasyev.entity.locations.Location;
import com.javarush.quest.kavtasyev.entity.locations.River;
import org.junit.jupiter.api.Test;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class SpringTest
{

	@Test
	@SuppressWarnings("all")
	void findSpring()
	{
		Spring spring = new Spring();
		Location location = new River();
		String actionButtons = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_DRINK_WATER_FROM_SPRING, BUTTON_DRINK_WATER_FROM_SPRING)).toString();
		String alerts = new StringBuilder().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_SPRING)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();

		assertAll(() -> {
			assertTrue(location.getHtmlActionButtons().isEmpty());
			assertTrue(location.getHtmlAlerts().isEmpty());

			spring.findSpring(location);

			assertEquals(actionButtons, location.getHtmlActionButtons().toString());
			assertEquals(alerts, location.getHtmlAlerts().toString());
		});
	}
}