package com.javarush.quest.kavtasyev.constants;

import com.javarush.quest.kavtasyev.service.ChangeLocation;
import com.javarush.quest.kavtasyev.service.DoAction;
import com.javarush.quest.kavtasyev.service.Function;
import com.javarush.quest.kavtasyev.service.ServiceFunction;

public enum FunctionType
{
	CHANGE_LOCATION(new ChangeLocation()),
	DO_ACTION(new DoAction()),
	SERVICE_FUNCTION(new ServiceFunction());


	private final Function function;

	FunctionType(Function function)
	{
		this.function = function;
	}

	public Function getFunction()
	{
		return function;
	}

}
