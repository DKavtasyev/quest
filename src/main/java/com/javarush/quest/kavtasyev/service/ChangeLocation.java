package com.javarush.quest.kavtasyev.service;

import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import com.javarush.quest.kavtasyev.util.LocationContainer;

import java.lang.reflect.InvocationTargetException;

public class ChangeLocation implements Function
{
	@Override
	public Result execute(User user, CustomData customData)
	{
		try
		{
			String newLocationParameter = customData.getValue();
			LocationContainer container = LocationContainer.getInstance();
			Location location = container.getLocation(newLocationParameter);
			String html = location.executeEvents(user);
			return new Result(html);
		}
		catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
			   IllegalAccessException e)
		{
			e.printStackTrace();
			return new Result(e);
		}
	}
}
