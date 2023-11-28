package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

public class LookForAWayOut extends Location
{
	@Override
	public String executeEvents(User user)
	{
		this.user = user;
		Location location = user.getLocation();
		location.clearHtmlTexts();
		location.formHtml();
		LocationProperties properties = location.getProperties();

		htmlLocationText = location.htmlLocationText;
		htmlLocationButtons = location.htmlLocationButtons;
		htmlActionButtons = location.htmlActionButtons;
		htmlAlerts = location.htmlAlerts;
		htmlScripts = location.htmlScripts;

		lookForWayOut(properties.findTheWayOutProbability());
		setHealth();

		return generateHtml();
	}

	private void lookForWayOut(double probability)
	{
		if (random.nextDouble() < probability)
		{
			user.setGotLost(false);
			htmlAlerts.append(NOTIFICATION_OPEN_DIV_TAG)
					.append(YOU_FOUND_THE_WAY_OUT)
					.append(NOTIFICATION_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
		}
		else
		{
			htmlLocationButtons.delete(0, htmlLocationButtons.length());
			htmlLocationButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
					.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_LOOK_FOR_A_WAY_OUT, BUTTON_LOOK_FOR_A_WAY_OUT))
					.append(CLOSE_DIV_TAG);
			htmlAlerts.append(ALARM_OPEN_DIV_TAG)
					.append(YOU_ARE_STILL_LOST)
					.append(ALARM_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
		}
	}

	@Override
	protected void formHtml()
	{
	}

	@Override
	protected void addActionButtons()
	{
	}
}
