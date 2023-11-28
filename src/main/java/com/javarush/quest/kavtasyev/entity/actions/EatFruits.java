package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.entity.app.User;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@ActionProperties(healthRestore = 20)
@SuppressWarnings("all")
public class EatFruits extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		int health = user.getHealth() + actionProperties.healthRestore();
		user.setHealth(Math.min(health, 100));
		htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_HAVE_EATEN_FRUITS)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);

		setHealth();
		return htmlActionsNotifications.toString();
	}
}
