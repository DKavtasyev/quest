package com.javarush.quest.kavtasyev.service;

import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;

public class ServiceFunction implements Function
{
	@Override
	@SuppressWarnings("all")
	public Result execute(User user, CustomData customData)
	{
		StringBuilder info = new StringBuilder();
		info.append("<ul>");
		info.append("<li>Login: ").append(user.getLogin()).append("</li>");
		info.append("<li>Health: ").append(user.getHealth()).append("</li>");
		info.append("<li>Was on the mountain: ").append(user.isWasOnTheMountain()).append("</li>");
		info.append("<li>Found beacon: ").append(user.isFoundBeacon()).append("</li>");
		info.append("<li>The beacon is on: ").append(user.isTheBeaconIsOn()).append("</li>");
		info.append("<li>The plane is found out: ").append(user.isThePlaneIsFoundOut()).append("</li>");
		info.append("<li>Is got lost: ").append(user.isGotLost()).append("</li>");
		info.append("<li>Has shoot a flare gun: ").append(user.isHasShootAFlareGun()).append("</li>");

		info.append("<li>Current location: ");
		Location currentLocation = user.getLocation();
		if (currentLocation == null)
			info.append("null").append("</li>");
		else
			info.append(user.getLocation().getClass().getSimpleName()).append("</li>");

		info.append("<li>Location with snare: ");
		Location locationWithSnare = user.getLocationWithSnare();
		if (locationWithSnare == null)
			info.append("null").append("</li>");
		else
			info.append(user.getLocationWithSnare().getClass().getSimpleName()).append("</li>");

		info.append("<li>Tools:<ul>");
		user.getTools().stream().map(t -> t.getClass().getSimpleName()).forEach(clazz -> info.append("<li>").append(clazz).append("</li>"));
		info.append("</ul></li>");

		info.append("<li>Arms:<ul>");
		user.getArms().stream().map(t -> t.getClass().getSimpleName()).forEach(clazz -> info.append("<li>").append(clazz).append("</li>"));
		info.append("</ul></li>");

		info.append("<li>Foods:<ul>");
		user.getFoods().stream().map(t -> t.getClass().getSimpleName()).forEach(clazz -> info.append("<li>").append(clazz).append("</li>"));
		info.append("</ul></li>");

		info.append("</ul>");
		return new Result(info.toString());
	}
}
