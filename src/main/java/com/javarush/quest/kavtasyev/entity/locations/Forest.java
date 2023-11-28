package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.Spear;
import com.javarush.quest.kavtasyev.entity.arms.Truncheon;
import com.javarush.quest.kavtasyev.entity.food.Fruits;
import com.javarush.quest.kavtasyev.entity.food.Spring;
import com.javarush.quest.kavtasyev.entity.predators.Jackal;
import com.javarush.quest.kavtasyev.entity.predators.Predator;
import com.javarush.quest.kavtasyev.entity.predators.Snake;
import com.javarush.quest.kavtasyev.entity.predators.Tiger;
import com.javarush.quest.kavtasyev.entity.tool.Rope;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@LocationProperties(
		findRopeProbability = 0.5,
		findTruncheonOrSpearProbability = 0.4,
		findSpringProbability = 0.3,
		findFruitsProbability = 0.5,
		catchFowlProbability = 0.5,
		predatorAttackProbability = 0.25,
		snakeBiteProbability = 0.1,
		getLostProbability = 0.25,
		findTheWayOutProbability = 0.4,
		findOutThePlaneProbability = 0.3
)
public class Forest extends Location
{
	Rope rope = new Rope();
	Truncheon truncheon = new Truncheon();
	Spear spear = new Spear();
	Spring spring = new Spring();
	Fruits fruits = new Fruits();
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
	@SuppressWarnings("all")
	protected void formHtml()
	{
		htmlLocationText.append(TEXT_FOREST);
		htmlLocationButtons.append(FOREST_LOCATION_BUTTONS);
		htmlActionButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);

		findRope(properties.findRopeProbability());
		findTruncheonOrSpear(properties.findTruncheonOrSpearProbability());
		findTheSpring(properties.findSpringProbability());
		findFruits(properties.findFruitsProbability());
		getLost(properties.getLostProbability());
		findOutThePlane(properties.findOutThePlaneProbability());

		addActionButtons();

		htmlActionButtons.append(CLOSE_DIV_TAG);

		thePredatorAttacked(properties.predatorAttackProbability());
		theSnakeBit(properties.snakeBiteProbability());

		//actions notifications
		htmlAlerts.append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
		htmlAlerts.append(CLOSE_DIV_TAG);
	}

	private void findRope(double probability)
	{
		if (random.nextDouble() < probability && !user.isHasTheTool(Rope.class))
		{
			rope.findRope(user, this);
		}
	}

	private void findTruncheonOrSpear(double probability)
	{
		if(random.nextDouble() < probability && !user.isHasTheArm(Truncheon.class) && !user.isHasTheArm(Spear.class))
		{
			if (random.nextBoolean())
			{
				truncheon.findTruncheon(user, this);
			}
			else
			{
				spear.findSpear(user, this);
			}
		}
	}

	private void findTheSpring(double probability)
	{
		if(random.nextDouble() < probability)
		{
			spring.findSpring(this);
		}

	}

	private void findFruits(double probability)
	{
		if(random.nextDouble() < probability)
		{
			fruits.findFruits(this);
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
		addActionButtonFryTheFowl();
		addActionButtonFryTheFish();
	}
}
