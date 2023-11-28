package com.javarush.quest.kavtasyev.entity.predators;

import com.javarush.quest.kavtasyev.abstraction.PredatorProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@PredatorProperties(damage = 70)
public class Snake implements Predator
{
	PredatorProperties properties = this.getClass().getAnnotation(PredatorProperties.class);

	@Override
	public void attack(User user, Location location)
	{
		StringBuilder htmlActionButtons = location.getHtmlActionButtons();
		StringBuilder htmlAlerts = location.getHtmlAlerts();

		htmlActionButtons.delete(0, htmlActionButtons.length());
		htmlAlerts.append(ALARM_OPEN_DIV_TAG)
				.append(THE_SNAKE_BIT_YOU)
				.append(ALARM_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
		user.setHealth(Math.max(0, user.getHealth() - properties.damage()));
	}
}
