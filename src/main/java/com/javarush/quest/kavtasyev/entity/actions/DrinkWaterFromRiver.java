package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@ActionProperties(
		healthDamage = 30,
		healthRestore = 5
)
public class DrinkWaterFromRiver extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		Location location = user.getLocation();
		LocationProperties locationProperties = location.getProperties();

		if(getInfection(locationProperties.getInfectionProbability()))
		{
			htmlActionsNotifications.append(ALARM_OPEN_DIV_TAG)
					.append(YOU_GOT_AN_INFECTION)
					.append(ALARM_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
			int health = user.getHealth() - actionProperties.healthDamage();
			user.setHealth(Math.max(0, health));
		}
		else
		{
			htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
					.append(YOU_HAVE_DRUNK_THE_WATER_FROM_THE_RIVER)
					.append(NOTIFICATION_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
			int health = user.getHealth() + actionProperties.healthRestore();
			user.setHealth(Math.min(100, health));
		}
		setHealth();
		return htmlActionsNotifications.toString();
	}

	private boolean getInfection(double probability)
	{
		return random.nextDouble() < probability;
	}

}
