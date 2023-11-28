package com.javarush.quest.kavtasyev.entity.predators;

import com.javarush.quest.kavtasyev.abstraction.ArmProperties;
import com.javarush.quest.kavtasyev.abstraction.PredatorProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.*;
import com.javarush.quest.kavtasyev.entity.locations.Location;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@PredatorProperties(damage = 65)
public class Tiger implements Predator
{
	private final PredatorProperties properties = this.getClass().getAnnotation(PredatorProperties.class);

	@SuppressWarnings("all")
	@Override
	public void attack(User user, Location location)
	{
		StringBuilder htmlActionButtons = location.getHtmlActionButtons();
		StringBuilder htmlAlerts = location.getHtmlAlerts();

		Arms arm = user.chooseArm();
		ArmProperties armProperties;
		int power = 0;
		if (arm != null)
			power = arm.getArmProperties().power() > properties.damage() * 0.4 ? arm.getArmProperties().power(): 0;

		int damage = Math.max(0, properties.damage() - power);
		user.setHealth(user.getHealth() - damage);

		String message;
		if (arm instanceof FlareGun)
		{
			message = YOU_HAVE_FLUSHED_OFF_A_HEAVY_PREDATOR;
			user.shoot((FlareGun) arm);
		}
		else if (arm instanceof Machete || arm instanceof Spear)
		{
			if (damage > 20)
				htmlActionButtons.delete(0, htmlActionButtons.length());
			message = String.format(YOU_HAVE_FOUGHT_OFF_A_HEAVY_PREDATOR, arm.getNames()[4]);
		}
		else
		{
			htmlActionButtons.delete(0, htmlActionButtons.length());
			message = YOU_HAVE_BEEN_ATTACKED_BY_A_HEAVY_PREDATOR;
		}

		htmlAlerts.append(ALARM_OPEN_DIV_TAG)
				.append(message)
				.append(ALARM_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
	}

	public PredatorProperties getProperties()
	{
		return properties;
	}
}
