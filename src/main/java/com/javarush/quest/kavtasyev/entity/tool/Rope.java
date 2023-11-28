package com.javarush.quest.kavtasyev.entity.tool;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;

import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

public class Rope implements Tool
{
	protected final ThreadLocalRandom random = ThreadLocalRandom.current();
	public void findRope(User user, Location location)
	{
		user.getTools().add(this);

		location.getHtmlAlerts().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_ROPE)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
	}
}
