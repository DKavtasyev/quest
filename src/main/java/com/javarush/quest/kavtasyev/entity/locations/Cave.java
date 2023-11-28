package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Spring;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@LocationProperties(
		findSpringProbability = 0.5,
		getLostProbability = 0.3,
		findTheWayOutProbability = 0.3
)
public class Cave extends Location
{
	Spring spring = new Spring();

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
		htmlLocationText.append(TEXT_CAVE);
		htmlLocationButtons.append(CAVE_LOCATION_BUTTONS);
		htmlActionButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);

		findTheSpring(properties.findSpringProbability());
		getLost(properties.getLostProbability());

		addActionButtons();

		htmlActionButtons.append(CLOSE_DIV_TAG);

		//actions notifications
		htmlAlerts.append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
		htmlAlerts.append(CLOSE_DIV_TAG);
	}

	private void findTheSpring(double probability)
	{
		if (random.nextDouble() < probability)
		{
			spring.findSpring(this);
		}
	}

	@Override
	protected void addActionButtons()
	{
		addActionButtonFryTheFowl();
		addActionButtonFryTheFish();
	}
}
