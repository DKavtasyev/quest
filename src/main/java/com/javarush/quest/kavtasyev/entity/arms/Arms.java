package com.javarush.quest.kavtasyev.entity.arms;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.javarush.quest.kavtasyev.abstraction.ArmProperties;
import com.javarush.quest.kavtasyev.entity.Item;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = FlareGun.class, name = "FlareGun"),
		@JsonSubTypes.Type(value = Machete.class, name = "Machete"),
		@JsonSubTypes.Type(value = Spear.class, name = "Spear"),
		@JsonSubTypes.Type(value = Truncheon.class, name = "Truncheon")
})
public interface Arms extends Item
{
	String[] getNames();

	ArmProperties getArmProperties();
}
