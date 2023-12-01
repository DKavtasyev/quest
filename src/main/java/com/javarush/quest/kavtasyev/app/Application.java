package com.javarush.quest.kavtasyev.app;

import com.javarush.quest.kavtasyev.constants.FunctionType;
import com.javarush.quest.kavtasyev.constants.Operation;
import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.service.Function;
import com.javarush.quest.kavtasyev.controllers.QuestServlet;

public class Application
{
	private final QuestServlet controller;

	public Application(QuestServlet controller)
	{
		this.controller = controller;
	}

	public Result run()
	{
		CustomData customData = controller.getCustomData();
		User user = controller.getUser();

		String operation = customData.getParameter();
		Function function = getFunction(operation);
		Result result = function.execute(user, customData);
		controller.updateUser(user);
		return result;
	}

	private Function getFunction(String operation)
	{
		return switch (operation)
		{
			case Operation.CHANGE_LOCATION -> FunctionType.CHANGE_LOCATION.getFunction();
			case Operation.DO_ACTION -> FunctionType.DO_ACTION.getFunction();
			case Operation.SERVICE_FUNCTION -> FunctionType.SERVICE_FUNCTION.getFunction();
			default -> null;
		};
	}


}
