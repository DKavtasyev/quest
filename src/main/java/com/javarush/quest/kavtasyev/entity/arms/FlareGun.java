package com.javarush.quest.kavtasyev.entity.arms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javarush.quest.kavtasyev.abstraction.ArmProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import lombok.Getter;
import lombok.Setter;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@Getter
@Setter
@ArmProperties(power = 70)
public class FlareGun implements Arms
{
	private int flares;
	@JsonIgnore
	private final String[] names = {"сигнальная ракетница", "сигнальную ракетницу", "сигнальной ракетнице", "сигнальную ракетницу", "сигнальной ракетницей", "сигнальной ракетнице"};
	@JsonIgnore
	private final ArmProperties armProperties = this.getClass().getAnnotation(ArmProperties.class);

	public FlareGun()
	{
	}

	public FlareGun(int flares)
	{
		this.flares = flares;
	}

	public void findFlareGun(User user, Location location)
	{
		user.getArms().add(this);
		user.setFoundFlareGun(true);
		location.getHtmlAlerts().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_FLARE_GUN)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
	}

	public int takeAShot()
	{
		return --flares;
	}
}
