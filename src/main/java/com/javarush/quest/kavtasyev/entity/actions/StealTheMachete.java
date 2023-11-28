package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.Machete;
import com.javarush.quest.kavtasyev.entity.locations.Location;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@SuppressWarnings("all")
public class StealTheMachete extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		Location location = user.getLocation();
		LocationProperties properties = location.getProperties();

		if(stealMachete(properties.stealMacheteProbability()))
		{
			htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
					.append(YOU_STOLE_THE_MACHETE)
					.append(NOTIFICATION_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
			user.getArms().add(new Machete());
		}
		else
		{
			htmlActionsNotifications.append(String.format(GAME_OVER_SCRIPT, YOU_HAVE_BEEN_CAPTURED));
		}
		return htmlActionsNotifications.toString();
	}

	private boolean stealMachete(double probability)
	{
		return random.nextDouble() < probability;
	}
}
