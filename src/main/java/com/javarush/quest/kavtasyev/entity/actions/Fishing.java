package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.locations.Location;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@SuppressWarnings("all")
public class Fishing extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		Location location = user.getLocation();
		LocationProperties properties = location.getProperties();

		if(fishing(properties.catchFishProbability()))
		{
			htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
					.append(YOU_CAUGHT_A_FISH)
					.append(NOTIFICATION_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
			user.getFoods().add(new Fish());
		}
		else
		{
			htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
					.append(YOU_DID_NOT_CATCH_A_FISH)
					.append(NOTIFICATION_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
		}
		return htmlActionsNotifications.toString();
	}

	private boolean fishing(double probability)
	{
		return random.nextDouble() < probability;
	}
}
