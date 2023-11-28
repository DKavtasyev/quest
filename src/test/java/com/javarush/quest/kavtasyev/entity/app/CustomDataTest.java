package com.javarush.quest.kavtasyev.entity.app;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomDataTest
{
	@ParameterizedTest
	@ValueSource(strings = {"location", "action", "service"})
	void getParameter(String parameter)
	{
		CustomData customData = new CustomData(parameter, "value");
		assertEquals(parameter, customData.getParameter());
	}

	@ParameterizedTest
	@ValueSource(strings = {"StartBeach", "Beach", "Forest", "River", "Fishing"})
	void getValue(String value)
	{
		CustomData customData = new CustomData("location", value);
		assertEquals(value, customData.getValue());
	}
}