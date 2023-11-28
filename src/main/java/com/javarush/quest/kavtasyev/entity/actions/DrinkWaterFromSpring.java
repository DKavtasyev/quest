package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.entity.app.User;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@SuppressWarnings("all")
@ActionProperties(healthRestore = 20)
public class DrinkWaterFromSpring extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_HAVE_DRUNK_THE_WATER_FROM_THE_SPRING)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
		int health = user.getHealth() + actionProperties.healthRestore();
		user.setHealth(Math.min(100, health));
		setHealth();
		return htmlActionsNotifications.toString();
	}
}
