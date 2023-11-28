package com.javarush.quest.kavtasyev.service;

import com.javarush.quest.kavtasyev.entity.actions.Action;
import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.util.ActionContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoActionTest
{

	@Mock
	CustomData customData;
	@Mock
	Action action;
	@Mock
	ActionContainer container;
	@Mock
	User user;

	@Test
	void execute()
	{
		try(MockedStatic<ActionContainer> util = mockStatic(ActionContainer.class))
		{
			DoAction doAction = new DoAction();
			String resultString = "result";
			String actionParameter = "string";
			doReturn(actionParameter).when(customData).getValue();
			util.when(ActionContainer::getInstance).thenReturn(container);
			doReturn(action).when(container).getAction(anyString());
			doReturn(resultString).when(action).executeAction(any(User.class));
			Result result = doAction.execute(user, customData);

			assertAll(() -> {
				assertEquals(resultString, result.getHtml()	);
				verify(action).executeAction(user);
				verify(container).getAction(actionParameter);
			});
		}
		catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
			   IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
}