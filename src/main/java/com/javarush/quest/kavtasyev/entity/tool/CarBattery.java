package com.javarush.quest.kavtasyev.entity.tool;

import com.javarush.quest.kavtasyev.entity.locations.Settlement;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

public class CarBattery implements Tool
{
	public void findCarBattery(Settlement settlement)
	{
		settlement.getHtmlActionButtons().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_STEAL_THE_CAR_BATTERY, BUTTON_STEAL_THE_CAR_BATTERY));
		settlement.getHtmlAlerts().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_FOUND_THE_FIT_BATTERY)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
	}
}
