package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.predators.Jackal;
import com.javarush.quest.kavtasyev.entity.predators.Predator;
import com.javarush.quest.kavtasyev.entity.predators.Snake;
import com.javarush.quest.kavtasyev.entity.predators.Tiger;
import com.javarush.quest.kavtasyev.entity.tool.Rope;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@LocationProperties(
		findRopeProbability = 0.7,
		predatorAttackProbability = 0.3,
		getLostProbability = 0.6,
		snakeBiteProbability = 0.3,
		findTheWayOutProbability = 0.15,
		findOutThePlaneProbability = 0.1
)
public class Jungle extends Location
{
	Rope rope = new Rope();
	Predator tiger = new Tiger();
	Predator jackal = new Jackal();
	Predator snake = new Snake();

	@Override
	@SuppressWarnings("all")
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
		htmlLocationText.append(TEXT_JUNGLE);
		htmlLocationButtons.append(JUNGLE_LOCATION_BUTTONS);
		htmlActionButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);

		findRope(properties.findRopeProbability());
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

	private void thePredatorAttacked(double probability)
	{
		if(random.nextDouble() < probability)																					// 30 %, что нападёт хищник
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
	}
}
