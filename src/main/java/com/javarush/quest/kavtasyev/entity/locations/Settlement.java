package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.Machete;
import com.javarush.quest.kavtasyev.entity.tool.Beacon;
import com.javarush.quest.kavtasyev.entity.tool.CarBattery;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@LocationProperties(
		findCarBatteryProbability = 0.9,
		findMacheteProbability = 0.5,
		stealCarBatteryProbability = 0.75,
		stealMacheteProbability = 0.9,
		beCapturedProbability = 0.15
)
public class Settlement extends Location
{
	CarBattery carBattery = new CarBattery();
	Machete machete = new Machete();

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
		htmlLocationText.append(TEXT_SETTLEMENT);
		htmlLocationButtons.append(SETTLEMENT_LOCATION_BUTTONS);
		htmlActionButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);

		stealCarBattery(properties.findCarBatteryProbability());
		stealMachete(properties.findMacheteProbability());

		addActionButtons();
		htmlActionButtons.append(CLOSE_DIV_TAG);

		becomePrisoner(properties.beCapturedProbability());

		//actions notifications
		htmlAlerts.append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
		htmlAlerts.append(CLOSE_DIV_TAG);
	}

	private void stealCarBattery(double probability)
	{
		if(random.nextDouble() < probability && !user.isHasTheTool(CarBattery.class) && user.isHasTheTool(Beacon.class) && !user.isTheBeaconIsOn())
		{
			carBattery.findCarBattery(this);
		}
	}

	private void stealMachete(double probability)
	{
		if(random.nextDouble() < probability && !user.isHasTheArm(Machete.class))
		{
			machete.findMachete(this);
		}
	}

	private void becomePrisoner(double probability)
	{
		if(random.nextDouble() < probability)
		{
			htmlLocationButtons.delete(0, htmlLocationButtons.length());
			htmlActionButtons.delete(0, htmlActionButtons.length());
			htmlScripts.append(String.format(GAME_OVER_SCRIPT, YOU_HAVE_BEEN_CAPTURED));
		}
	}

	@Override
	protected void addActionButtons()
	{

	}
}
