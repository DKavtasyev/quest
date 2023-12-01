package com.javarush.quest.kavtasyev.service;

import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

public class ServiceFunction implements Function
{
	@Override
	@SuppressWarnings("all")
	public Result execute(User user, CustomData customData)
	{
		StringBuilder info = new StringBuilder();
		info.append(LIST_OPEN_UL_TAG)
				.append(String.format(LOGIN_PARAMETER, user.getLogin()))
				.append(String.format(HEALTH_PARAMETER, user.getHealth()))
				.append(String.format(WAS_ON_MOUNTAIN_PARAMETER, user.isWasOnTheMountain()))
				.append(String.format(FOUND_BEACON_PARAMETER, user.isFoundBeacon()))
				.append(String.format(THE_BEACON_IS_ON_PARAMETER, user.isTheBeaconIsOn()))
				.append(String.format(THE_PLANE_IS_FOUND_OUT_PARAMETER, user.isThePlaneIsFoundOut()))
				.append(String.format(IS_GOT_LOST_PARAMETER, user.isGotLost()))
				.append(String.format(HAS_SHOOT_A_FLAREGUN_PARAMETER, user.isHasShootAFlareGun()));


		info.append(String.format(CURRENT_LOCATION_PARAMETER, user.getLocation() != null ? user.getLocation().getClass().getSimpleName() : null));
		info.append(String.format(LOCATION_WITH_SNARE, user.getLocationWithSnare() != null ? user.getLocationWithSnare().getClass().getSimpleName() : null));


		info.append(LIST_OPEN_LI_TAG).append("Tools:").append(LIST_OPEN_UL_TAG);
		user.getTools().stream().map(t -> t.getClass().getSimpleName()).forEach(clazz -> info.append(LIST_OPEN_LI_TAG).append(clazz).append(LIST_CLOSE_LI_TAG));
		info.append(LIST_CLOSE_UL_TAG).append(LIST_CLOSE_LI_TAG);

		info.append(LIST_OPEN_LI_TAG).append("Arms:<ul>");
		user.getArms().stream().map(t -> t.getClass().getSimpleName()).forEach(clazz -> info.append(LIST_OPEN_LI_TAG).append(clazz).append(LIST_CLOSE_LI_TAG));
		info.append(LIST_CLOSE_UL_TAG).append(LIST_CLOSE_LI_TAG);

		info.append(LIST_OPEN_LI_TAG).append("Foods:<ul>");
		user.getFoods().stream().map(t -> t.getClass().getSimpleName()).forEach(clazz -> info.append(LIST_OPEN_LI_TAG).append(clazz).append(LIST_CLOSE_LI_TAG));
		info.append(LIST_CLOSE_UL_TAG).append(LIST_CLOSE_LI_TAG);

		info.append(LIST_CLOSE_UL_TAG);
		return new Result(info.toString());
	}
}
