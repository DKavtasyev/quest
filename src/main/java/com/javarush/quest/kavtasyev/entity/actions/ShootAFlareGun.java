package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.entity.app.User;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

public class ShootAFlareGun extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		this.user.setHasShootAFlareGun(true);

		user.shoot(user.getFlareGun());

		htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_SHOT_A_FLARE_GUN)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
		return htmlActionsNotifications.toString();
	}
}
