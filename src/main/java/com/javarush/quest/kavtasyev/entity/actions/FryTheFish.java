package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.food.Food;

import java.util.Set;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@ActionProperties(healthRestore = 30)
@SuppressWarnings("all")
public class FryTheFish extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		int health = user.getHealth() + actionProperties.healthRestore();
		user.setHealth(Math.min(health, 100));
		htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_FRIED_AND_ATE_THE_FISH)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);

		Set<Food> foods = user.getFoods();
		foods.removeIf(food -> food instanceof Fish);

		setHealth();
		return htmlActionsNotifications.toString();
	}
}
