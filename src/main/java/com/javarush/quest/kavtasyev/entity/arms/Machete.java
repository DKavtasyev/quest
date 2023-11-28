package com.javarush.quest.kavtasyev.entity.arms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javarush.quest.kavtasyev.abstraction.ArmProperties;
import com.javarush.quest.kavtasyev.entity.locations.Settlement;
import lombok.Getter;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@Getter
@ArmProperties(power = 35)
public class Machete implements Arms
{
	@JsonIgnore
	private final String[] names = {"мачете", "мачете", "мачете", "мачете", "мачете", "мачете"};
	@JsonIgnore
	private final ArmProperties armProperties = this.getClass().getAnnotation(ArmProperties.class);

	public void findMachete(Settlement settlement)
	{
		settlement.getHtmlActionButtons().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_STEAL_THE_MACHETE, BUTTON_STEAL_THE_MACHETE));
		settlement.getHtmlAlerts().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_FOUND_THE_MACHETE)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG);
	}
}
