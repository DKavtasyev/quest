package com.javarush.quest.kavtasyev.util;

import com.javarush.quest.kavtasyev.entity.actions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class ActionContainerTest
{

	@Test
	@DisplayName("Тестирование метода getInstance() класса ActionContainer")
	void getInstanceOfActionContainer()
	{
		ActionContainer actionContainer = ActionContainer.getInstance();
		assertSame(actionContainer, ActionContainer.getInstance());
	}

	@ParameterizedTest
	@MethodSource("actionsProvidedFactory")
	@DisplayName("Тестирование метода getAction(String actionParameter) класса ActionContainer")
	void getInstanceOfActionForActionParameter(String actionParameter) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
	{
		Action action = ActionContainer.getInstance().getAction(actionParameter);
		if (action instanceof CheckTheSnare)
			assertEquals(ACTION_PARAMETER_CHECK_THE_SNARE, action.getClass().getSimpleName());
		else if (action instanceof DrinkWaterFromRiver)
			assertEquals(ACTION_PARAMETER_DRINK_WATER_FROM_RIVER, action.getClass().getSimpleName());
		else if (action instanceof DrinkWaterFromSpring)
			assertEquals(ACTION_PARAMETER_DRINK_WATER_FROM_SPRING, action.getClass().getSimpleName());
		else if (action instanceof Fishing)
			assertEquals(ACTION_PARAMETER_FISHING, action.getClass().getSimpleName());
		else if (action instanceof FryTheFish)
			assertEquals(ACTION_PARAMETER_FRY_THE_FISH, action.getClass().getSimpleName());
		else if (action instanceof FryTheFowl)
			assertEquals(ACTION_PARAMETER_FRY_THE_FOWL, action.getClass().getSimpleName());
		else if (action instanceof LightAFire)
			assertEquals(ACTION_PARAMETER_LIGHT_A_FIRE, action.getClass().getSimpleName());
		else if (action instanceof SetASnare)
			assertEquals(ACTION_PARAMETER_SET_A_SNARE, action.getClass().getSimpleName());
		else if (action instanceof ShootAFlareGun)
			assertEquals(ACTION_PARAMETER_SHOOT_A_FLARE_GUN, action.getClass().getSimpleName());
		else if (action instanceof StealTheCarBattery)
			assertEquals(ACTION_PARAMETER_STEAL_THE_CAR_BATTERY, action.getClass().getSimpleName());
		else if (action instanceof StealTheMachete)
			assertEquals(ACTION_PARAMETER_STEAL_THE_MACHETE, action.getClass().getSimpleName());
		else if (action instanceof TurnOnTheBeacon)
			assertEquals(ACTION_PARAMETER_TURN_ON_THE_BEACON, action.getClass().getSimpleName());
		else
			fail();
	}

	static Stream<String> actionsProvidedFactory()
	{
		return Stream.of(ACTION_PARAMETER_CHECK_THE_SNARE, ACTION_PARAMETER_DRINK_WATER_FROM_RIVER,
				ACTION_PARAMETER_DRINK_WATER_FROM_SPRING, ACTION_PARAMETER_FISHING, ACTION_PARAMETER_FRY_THE_FISH,
				ACTION_PARAMETER_FRY_THE_FOWL, ACTION_PARAMETER_LIGHT_A_FIRE, ACTION_PARAMETER_SET_A_SNARE,
				ACTION_PARAMETER_SHOOT_A_FLARE_GUN, ACTION_PARAMETER_STEAL_THE_CAR_BATTERY,
				ACTION_PARAMETER_STEAL_THE_MACHETE, ACTION_PARAMETER_TURN_ON_THE_BEACON);
	}
}