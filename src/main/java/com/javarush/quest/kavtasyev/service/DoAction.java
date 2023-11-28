package com.javarush.quest.kavtasyev.service;

import com.javarush.quest.kavtasyev.entity.actions.Action;
import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.util.ActionContainer;

import java.lang.reflect.InvocationTargetException;

public class DoAction implements Function
{
	@Override
	public Result execute(User user, CustomData customData)
	{
		try
		{
			String actionParameter = customData.getValue();
			Action action = ActionContainer.getInstance().getAction(actionParameter);

			String html = action.executeAction(user);
			return new Result(html);
		}
		catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
			   IllegalAccessException e)
		{
			e.printStackTrace();
			return new Result(e);
		}
	}
}
