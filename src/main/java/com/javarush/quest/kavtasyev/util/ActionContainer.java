package com.javarush.quest.kavtasyev.util;

import com.javarush.quest.kavtasyev.constants.ServiceTexts;
import com.javarush.quest.kavtasyev.entity.actions.Action;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ActionContainer
{
	private volatile static ActionContainer instance;
	private final Set<Action> actions = new HashSet<>();

	private ActionContainer()
	{
	}

	public static ActionContainer getInstance()
	{
		ActionContainer localInstance = instance;

		if(localInstance == null)
		{
			synchronized (ActionContainer.class)
			{
				localInstance = instance;
				if (localInstance == null)
				{
					instance = new ActionContainer();
				}
			}
		}
		return instance;
	}

	public Action getAction(String actionParameter) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
	{
		@SuppressWarnings("all")
		String className = new StringBuilder(ServiceTexts.PATH_TO_ACTION_CLASSES).append(actionParameter).toString();
		Class<?> clazz = Class.forName(className);
		if (instantiated(clazz))
		{
			for(Action action: actions)
			{
				if (action.getClass().equals(clazz))
				{
					action.clearHtmlTexts();
					return action;
				}
			}
		}
		Constructor<?> constructor = clazz.getConstructor();
		Action action = (Action) constructor.newInstance();
		action.setRandom(ThreadLocalRandom.current());
		actions.add(action);
		return action;
	}

	private <T> boolean instantiated(Class<T> tClass)
	{
		return actions.stream().anyMatch(l -> l.getClass().equals(tClass));
	}
}
