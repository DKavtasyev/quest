package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import com.javarush.quest.kavtasyev.entity.tool.CarBattery;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@SuppressWarnings("all")
public class StealTheCarBattery extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		Location location = user.getLocation();
		LocationProperties properties = location.getProperties();

		if(stealCarBattery(properties.stealCarBatteryProbability()))
		{
			htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
					.append(YOU_STOLE_THE_CAR_BATTERY)
					.append(NOTIFICATION_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
			user.getTools().add(new CarBattery());
		}
		else
		{
			htmlActionsNotifications.append(String.format(GAME_OVER_SCRIPT, YOU_HAVE_BEEN_CAPTURED));
		}
		return htmlActionsNotifications.toString();
	}

	private boolean stealCarBattery(double probability)
	{
		return random.nextDouble() < probability;
	}
}
