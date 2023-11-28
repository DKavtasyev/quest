package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import com.javarush.quest.kavtasyev.entity.tool.Rope;
import com.javarush.quest.kavtasyev.entity.tool.Tool;

import java.util.Set;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@SuppressWarnings("all")
public class SetASnare extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		Location location = user.getLocation();
		location.setSnareIsSet(true);
		user.setLocationWithSnare(location);
		Set<Tool> tools = user.getTools();

		tools.removeIf(tool -> tool instanceof Rope);

		htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
				.append(THE_SNARE_WAS_SET)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
		return htmlActionsNotifications.toString();
	}
}
