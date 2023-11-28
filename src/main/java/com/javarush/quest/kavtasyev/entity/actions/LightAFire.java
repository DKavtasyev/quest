package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Beach;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

public class LightAFire extends Action
{

	@Override
	public String executeAction(User user)
	{
		this.user = user;

		LocationProperties properties = Beach.class.getAnnotation(LocationProperties.class);

		double probability = properties.youHaveFoundAShipProbability();
		if (user.isHasShootAFlareGun())
			probability *= 2;

		if (random.nextDouble() < probability)
		{
			htmlActionsNotifications.append(String.format(YOU_WIN_SCRIPT, YOU_WERE_SAVED));
		}
		else
		{
			htmlActionsNotifications.append(String.format(GAME_OVER_SCRIPT, YOU_WERE_NOT_FOUND));
		}


		htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_LIT_A_FIRE)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
		return htmlActionsNotifications.toString();
	}
}
