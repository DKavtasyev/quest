package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.FlareGun;
import com.javarush.quest.kavtasyev.entity.tool.Beacon;
import com.javarush.quest.kavtasyev.entity.tool.Compass;
import com.javarush.quest.kavtasyev.entity.tool.Lighter;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@LocationProperties(
		findCompassProbability = 0.2,
		findLighterProbability = 0.4,
		findBeaconProbability = 0.1,
		findFlareGunProbability = 0.2,
		findOutThePlaneProbability = 0.5,
		youHaveFoundAShipProbability = 0.4
)
public class Beach extends Location
{
	Compass compass = new Compass();
	Lighter lighter = new Lighter();
	Beacon beacon = new Beacon();
	FlareGun flareGun = new FlareGun(5);

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
		htmlLocationText.append(TEXT_BEACH);
		htmlLocationButtons.append(BEACH_LOCATION_BUTTONS);
		htmlActionButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);

		//events
		findCompass(properties.findCompassProbability());
		findLighter(properties.findLighterProbability());
		findBeacon(properties.findBeaconProbability());
		findFlareGun(properties.findFlareGunProbability());
		findOutThePlane(properties.findOutThePlaneProbability());

		addActionButtons();

		htmlActionButtons.append(CLOSE_DIV_TAG);

		//actions notifications
		htmlAlerts.append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
		htmlAlerts.append(CLOSE_DIV_TAG);
	}

	private void findCompass(double probability)
	{
		if (!user.isHasTheTool(Compass.class) && random.nextDouble() < probability)
		{
			compass.findCompass(user, this);
		}
	}

	private void findLighter(double probability)
	{
		if (!user.isHasTheTool(Lighter.class) && random.nextDouble() < probability)
		{
			lighter.findLighter(user, this);
		}
	}

	private void findBeacon(double probability)
	{
		if (random.nextDouble() < probability && !user.isHasTheTool(Beacon.class) && user.isWasOnTheMountain() && !user.isFoundBeacon())
		{
			beacon.findBeacon(user, this);
		}
	}

	private void findFlareGun(double probability)
	{
		if (random.nextDouble() < probability && !user.isHasTheArm(FlareGun.class) && !user.isFoundFlareGun())
		{
			flareGun.findFlareGun(user, this);
		}
	}

	protected void addActionButtons()
	{
		addActionButtonFryTheFowl();
		addActionButtonFryTheFish();
		addActionButtonLightAFire();
	}
}
