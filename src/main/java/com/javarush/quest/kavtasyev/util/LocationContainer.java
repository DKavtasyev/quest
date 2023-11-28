package com.javarush.quest.kavtasyev.util;

import com.javarush.quest.kavtasyev.constants.ServiceTexts;
import com.javarush.quest.kavtasyev.entity.locations.Location;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class LocationContainer
{
	private volatile static LocationContainer instance;
	private final Set<Location> locations = new HashSet<>();

	private LocationContainer()
	{
	}

	public static LocationContainer getInstance()
	{
		LocationContainer localInstance = instance;

		if(localInstance == null)
		{
			synchronized (LocationContainer.class)
			{
				localInstance = instance;

				if(localInstance == null)
				{
					instance = new LocationContainer();
				}
			}
		}
		return instance;
	}

	public Location getLocation(String newLocationParameter) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
	{
		@SuppressWarnings("all")
		String className = new StringBuilder(ServiceTexts.PATH_TO_LOCATION_CLASSES).append(newLocationParameter).toString();
		Class<?> clazz = Class.forName(className);
		if (instantiated(clazz))
		{
			for(Location location: locations)
			{
				if (location.getClass().equals(clazz))
				{
					location.clearHtmlTexts();
					return location;
				}
			}
		}
		Constructor<?> constructor = clazz.getConstructor();
		Location location = (Location) constructor.newInstance();
		location.setRandom(ThreadLocalRandom.current());
		locations.add(location);
		return location;
	}

	private <T> boolean instantiated(Class<T> tClass)
	{
		return locations.stream().anyMatch(l -> l.getClass().equals(tClass));
	}

	public void clearLocations()
	{
		locations.clear();
	}
}
