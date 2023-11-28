package com.javarush.quest.kavtasyev.entity.app;

import lombok.Getter;

@Getter
@SuppressWarnings("all")
public class CustomData
{
	private final String parameter;
	private final String value;

	public CustomData(String parameter, String value)
	{
		this.parameter = parameter;
		this.value = value;
	}
}
