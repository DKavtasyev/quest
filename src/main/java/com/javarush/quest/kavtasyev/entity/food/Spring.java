package com.javarush.quest.kavtasyev.entity.food;

import com.javarush.quest.kavtasyev.entity.locations.Location;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

public class Spring implements Food
{
	public void findSpring(Location location)
	{
		location.getHtmlActionButtons().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_DRINK_WATER_FROM_SPRING, BUTTON_DRINK_WATER_FROM_SPRING));
		location.getHtmlAlerts().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_SPRING)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
	}
}
