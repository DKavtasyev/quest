package com.javarush.quest.kavtasyev.entity.tool;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.javarush.quest.kavtasyev.entity.Item;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Compass.class, name = "Compass"),
		@JsonSubTypes.Type(value = Lighter.class, name = "Lighter"),
		@JsonSubTypes.Type(value = Beacon.class, name = "Beacon"),
		@JsonSubTypes.Type(value = Rope.class, name = "Rope"),
		@JsonSubTypes.Type(value = CarBattery.class, name = "CarBattery")
})
public interface Tool extends Item
{
}
