package com.javarush.quest.kavtasyev.util;

import com.javarush.quest.kavtasyev.entity.locations.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class LocationContainerTest
{

	@Test
	@DisplayName("Тестирование метода getInstance() класса LocationContainer")
	void getInstanceOfLocationContainer()
	{
		LocationContainer locationContainer = LocationContainer.getInstance();
		assertSame(locationContainer, LocationContainer.getInstance());
	}

	@ParameterizedTest
	@MethodSource("locationsProvidedFactory")
	@DisplayName("Тестирование метода getLocation(String newLocationParameter) класса LocationContainer")
	void getLocationInstanceForLocationName(String locationParameter) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
	{
		Location location = LocationContainer.getInstance().getLocation(locationParameter);
		if (location instanceof Beach)
			assertEquals(LOCATION_PARAMETER_BEACH, location.getClass().getSimpleName());
		else if (location instanceof Forest)
			assertEquals(LOCATION_PARAMETER_FOREST, location.getClass().getSimpleName());
		else if (location instanceof River)
			assertEquals(LOCATION_PARAMETER_RIVER, location.getClass().getSimpleName());
		else if (location instanceof Jungle)
			assertEquals(LOCATION_PARAMETER_JUNGLE, location.getClass().getSimpleName());
		else if (location instanceof FarBeach)
			assertEquals(LOCATION_PARAMETER_FAR_BEACH, location.getClass().getSimpleName());
		else if (location instanceof Plain)
			assertEquals(LOCATION_PARAMETER_PLAIN, location.getClass().getSimpleName());
		else if (location instanceof Cave)
			assertEquals(LOCATION_PARAMETER_CAVE, location.getClass().getSimpleName());
		else if (location instanceof Mountain)
			assertEquals(LOCATION_PARAMETER_MOUNTAIN, location.getClass().getSimpleName());
		else if (location instanceof Settlement)
			assertEquals(LOCATION_PARAMETER_SETTLEMENT, location.getClass().getSimpleName());
		else if (location instanceof LookForAWayOut)
			assertEquals(LOCATION_PARAMETER_LOOK_FOR_A_WAY_OUT, location.getClass().getSimpleName());
		else
			fail();
	}

	static Stream<String> locationsProvidedFactory()
	{
		return Stream.of(LOCATION_PARAMETER_BEACH, LOCATION_PARAMETER_FOREST, LOCATION_PARAMETER_RIVER,
				LOCATION_PARAMETER_JUNGLE, LOCATION_PARAMETER_FAR_BEACH, LOCATION_PARAMETER_PLAIN,
				LOCATION_PARAMETER_CAVE, LOCATION_PARAMETER_MOUNTAIN, LOCATION_PARAMETER_SETTLEMENT,
				LOCATION_PARAMETER_LOOK_FOR_A_WAY_OUT);
	}
}