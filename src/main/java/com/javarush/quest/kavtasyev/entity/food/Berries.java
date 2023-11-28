package com.javarush.quest.kavtasyev.entity.food;

import com.javarush.quest.kavtasyev.entity.locations.Location;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

public class Berries implements Food
{
	public void findBerries(Location location)
	{
		location.getHtmlActionButtons().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_EAT_FRUITS, BUTTON_EAT_FRUITS));
		location.getHtmlAlerts().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_BERRIES)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
	}
}
