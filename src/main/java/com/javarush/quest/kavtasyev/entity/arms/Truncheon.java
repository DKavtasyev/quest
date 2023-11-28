package com.javarush.quest.kavtasyev.entity.arms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javarush.quest.kavtasyev.abstraction.ArmProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import lombok.Getter;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@Getter
@ArmProperties(power = 15)
public class Truncheon implements Arms
{
	@JsonIgnore
	private final String[] names = {"дубинка", "дубинку", "дубинке", "дубинку", "дубинкой", "дубинке"};
	@JsonIgnore
	private final ArmProperties armProperties = this.getClass().getAnnotation(ArmProperties.class);

	public void findTruncheon(User user, Location location)
	{
		user.getArms().add(this);
		location.getHtmlAlerts().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_TRUNCHEON)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
	}
}
