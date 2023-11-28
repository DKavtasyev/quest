package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.tool.Beacon;
import com.javarush.quest.kavtasyev.entity.tool.CarBattery;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@SuppressWarnings("all")
public class TurnOnTheBeacon extends Action
{

	@Override
	public String executeAction(User user)
	{
		this.user = user;
		user.setTheBeaconIsOn(true);
		user.getTools().removeIf(tool -> tool instanceof Beacon || tool instanceof CarBattery);
		htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_TURN_ON_THE_BEACON)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);

		return htmlActionsNotifications.toString();
	}
}
