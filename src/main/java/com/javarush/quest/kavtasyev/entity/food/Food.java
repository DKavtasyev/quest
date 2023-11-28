package com.javarush.quest.kavtasyev.entity.food;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.javarush.quest.kavtasyev.entity.Item;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Fish.class, name = "Fish"),
		@JsonSubTypes.Type(value = Fowl.class, name = "Fowl")
})
public interface Food extends Item
{
}
