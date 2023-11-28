package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Spring;
import com.javarush.quest.kavtasyev.entity.predators.Jackal;
import com.javarush.quest.kavtasyev.entity.predators.Predator;
import com.javarush.quest.kavtasyev.entity.predators.Snake;
import com.javarush.quest.kavtasyev.entity.predators.Tiger;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@LocationProperties(
		findSpringProbability = 0.2,
		predatorAttackProbability = 0.2,
		snakeBiteProbability = 0.05,
		catchFishProbability = 0.7,
		getInfectionProbability = 0.4,
		findOutThePlaneProbability = 0.2
)
public class River extends Location
{
	Spring spring = new Spring();
	Predator tiger = new Tiger();
	Predator jackal = new Jackal();
	Predator snake = new Snake();

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
		htmlLocationText.append(TEXT_RIVER);
		htmlLocationButtons.append(RIVER_LOCATION_BUTTONS);
		htmlActionButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);

		findTheSpring(properties.findSpringProbability());
		findOutThePlane(properties.findOutThePlaneProbability());

		addActionButtons();

		htmlActionButtons.append(CLOSE_DIV_TAG);

		thePredatorAttacked(properties.predatorAttackProbability());
		theSnakeBit(properties.snakeBiteProbability());

		//actions notifications
		htmlAlerts.append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
		htmlAlerts.append(CLOSE_DIV_TAG);
	}

	private void findTheSpring(double probability)
	{
		if(random.nextDouble() < probability)
		{
			spring.findSpring(this);
		}
	}

	private void thePredatorAttacked(double probability)
	{
		if(random.nextDouble() < probability)																					// 10 %, что нападёт хищник
		{
			double d = random.nextDouble();
			Predator predator = d > 0.33 ? jackal : tiger;
			predator.attack(user, this);
		}
	}

	private void theSnakeBit(double probability)
	{
		if(random.nextDouble() < probability)
		{
			snake.attack(user, this);
		}
	}

	@Override
	protected void addActionButtons()
	{
		addActionButtonSetTheSnare();
		addActionButtonCheckTheSnare();
		addActionButtonDrinkWaterFromRiver();
		addActionButtonFryTheFowl();
		addActionButtonFryTheFish();
		addActionButtonFishing();
	}
}
