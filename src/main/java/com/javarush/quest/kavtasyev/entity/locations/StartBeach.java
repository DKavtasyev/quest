package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.entity.app.User;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.START_BEACH_LOCATION_BUTTONS;
import static com.javarush.quest.kavtasyev.constants.LocationHtml.TEXT_START_BEACH;

public class StartBeach extends Location
{
	@Override
	@SuppressWarnings("all")
	public String executeEvents(User user)
	{
		return htmlLocationText.append(TEXT_START_BEACH).append(START_BEACH_LOCATION_BUTTONS).toString();
	}

	protected void formHtml()
	{

	}

	@Override
	protected void addActionButtons()
	{
	}
}
