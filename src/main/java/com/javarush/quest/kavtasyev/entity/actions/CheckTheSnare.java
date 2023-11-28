package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Fowl;
import com.javarush.quest.kavtasyev.entity.locations.Location;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@SuppressWarnings("all")
public class CheckTheSnare extends Action
{
	@Override
	public String executeAction(User user)
	{
		this.user = user;
		Location location = user.getLocation();
		LocationProperties properties = location.getProperties();
		location.setSnareIsSet(false);
		user.setLocationWithSnare(null);

		if(catchFowl(properties.catchFowlProbability()))
		{
			htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
					.append(YOU_CHECKED_THE_SNARE_AND_GOT_A_FOWL)
					.append(NOTIFICATION_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
			user.getFoods().add(new Fowl());
		}
		else
		{
			htmlActionsNotifications.append(NOTIFICATION_OPEN_DIV_TAG)
					.append(THE_SNARE_TURNED_OUT_TO_BE_EMPTY)
					.append(NOTIFICATION_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);
		}
		setHealth();
		return htmlActionsNotifications.toString();
	}

	private boolean catchFowl(double probability)
	{
		return random.nextDouble() < probability;
	}
}
