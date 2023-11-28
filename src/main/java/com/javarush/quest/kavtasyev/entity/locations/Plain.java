package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Berries;
import com.javarush.quest.kavtasyev.entity.predators.Jackal;
import com.javarush.quest.kavtasyev.entity.predators.Predator;
import com.javarush.quest.kavtasyev.entity.predators.Snake;
import com.javarush.quest.kavtasyev.entity.predators.Tiger;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@LocationProperties(
		findBerriesProbability = 0.5,
		predatorAttackProbability = 0.3,
		snakeBiteProbability = 0.05,
		findOutThePlaneProbability = 0.4
)
public class Plain extends Location
{
	Berries berries = new Berries();
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
		htmlLocationText.append(TEXT_PLAIN);
		htmlLocationButtons.append(PLAIN_LOCATION_BUTTONS);
		htmlActionButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);

		findBerries(properties.findBerriesProbability());
		findOutThePlane(properties.findOutThePlaneProbability());

		addActionButtons();

		htmlActionButtons.append(CLOSE_DIV_TAG);

		thePredatorAttacked(properties.predatorAttackProbability());
		theSnakeBit(properties.snakeBiteProbability());

		//actions notifications
		htmlAlerts.append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
		htmlAlerts.append(CLOSE_DIV_TAG);
	}

	private void findBerries(double probability)
	{
		if(random.nextDouble() < probability)
		{
			berries.findBerries(this);
		}
	}

	private void thePredatorAttacked(double probability)
	{
		if(random.nextDouble() < probability)
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
	@SuppressWarnings("all")
	protected void addActionButtons()
	{
		addActionButtonSetTheSnare();
		addActionButtonCheckTheSnare();
		addActionButtonFryTheFowl();
		addActionButtonFryTheFish();
	}
}
