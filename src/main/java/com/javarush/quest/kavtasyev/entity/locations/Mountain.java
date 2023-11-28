package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@LocationProperties(findOutThePlaneProbability = 0.5)
public class Mountain extends Location
{
	@Override
	public String executeEvents(User user)
	{
		this.user = user;
		user.setLocation(this);

		formHtml();
		setHealth();

		return generateHtml();
	}

	@Override
	protected void formHtml()
	{
		htmlLocationText.append(TEXT_MOUNTAIN);
		htmlLocationButtons.append(MOUNTAIN_LOCATION_BUTTONS);
		htmlActionButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);

		user.setWasOnTheMountain(true);
		findOutThePlane(properties.findOutThePlaneProbability());

		addActionButtons();

		htmlActionButtons.append(CLOSE_DIV_TAG);

		//actions notifications
		htmlAlerts.append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
		htmlAlerts.append(CLOSE_DIV_TAG);
	}

	@Override
	protected void addActionButtons()
	{
		addActionButtonTurnOnTheBeacon();
	}
}
