package com.javarush.quest.kavtasyev.entity.tool;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

public class Beacon implements Tool
{
	public void findBeacon(User user, Location location)
	{
		user.getTools().add(this);
		user.setFoundBeacon(true);
		location.getHtmlAlerts().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_BEACON)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
	}
}
