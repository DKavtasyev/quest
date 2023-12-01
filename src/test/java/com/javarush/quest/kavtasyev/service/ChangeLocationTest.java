package com.javarush.quest.kavtasyev.service;

import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Forest;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import com.javarush.quest.kavtasyev.util.LocationContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangeLocationTest
{
	@Mock
	CustomData customData;
	@Mock
	LocationContainer container;
	@Spy
	Location location = new Forest();
	@Mock
	User user;

	@Test
	@DisplayName("Тестирование метода execute(User user, CustomData customData) класса ChangeLocation")
	void whenExecuteThenGetLocationAndExecuteEventsOnIt()
	{
		try(MockedStatic<LocationContainer> util = mockStatic(LocationContainer.class))
		{
			ChangeLocation changeLocation = new ChangeLocation();
			String resultString = "result";
			String parameter = "string";
			doReturn(parameter).when(customData).getValue();
			util.when(LocationContainer::getInstance).thenReturn(container);
			doReturn(location).when(container).getLocation(anyString());
			doReturn(resultString).when(location).executeEvents(any(User.class));
			Result result = changeLocation.execute(user, customData);

			assertAll(() -> {
				assertEquals(resultString, result.getHtml());
				verify(location).executeEvents(user);
				verify(container).getLocation(parameter);
			});
		}
		catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
			   IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
}