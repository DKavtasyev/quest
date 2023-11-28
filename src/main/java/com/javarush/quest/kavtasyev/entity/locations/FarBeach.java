package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.tool.Beacon;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@LocationProperties(
		findBeaconProbability = 0.5,
		findOutThePlaneProbability = 0.2
)
public class FarBeach extends Location
{
	Beacon beacon = new Beacon();

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
		htmlLocationText.append(TEXT_FAR_BEACH);
		htmlLocationButtons.append(FAR_BEACH_LOCATION_BUTTONS);
		htmlActionButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);

		findBeacon(properties.findBeaconProbability());
		findOutThePlane(properties.findOutThePlaneProbability());

		addActionButtons();

		htmlActionButtons.append(CLOSE_DIV_TAG);

		//actions notifications
		htmlAlerts.append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
		htmlAlerts.append(CLOSE_DIV_TAG);
	}

	private void findBeacon(double probability)
	{
		if (random.nextDouble() < probability && !user.isHasTheTool(Beacon.class) && !user.isFoundBeacon())
		{
			beacon.findBeacon(user, this);
		}
	}

	@Override
	protected void addActionButtons()
	{
		addActionButtonFryTheFowl();
		addActionButtonFryTheFish();
	}
}
