package com.javarush.quest.kavtasyev.app;

import com.javarush.quest.kavtasyev.service.ChangeLocation;
import com.javarush.quest.kavtasyev.service.DoAction;
import com.javarush.quest.kavtasyev.service.Function;
import com.javarush.quest.kavtasyev.service.ServiceFunction;
import com.javarush.quest.kavtasyev.controllers.QuestServlet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.javarush.quest.kavtasyev.constants.Operation.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest
{
	@Mock
	QuestServlet controller;

	@ParameterizedTest
	@ValueSource(strings = {CHANGE_LOCATION, DO_ACTION, SERVICE_FUNCTION, "unknown_parameter"})
	@DisplayName("Метод getFunction(String operation) класса Application должен вернуть объект функции в зависимости от параметра")
	void returnFunctionDependingOnParameter(String operation) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{
		Application application = new Application(controller);

		Method getFunctionMethod = Application.class.getDeclaredMethod("getFunction", String.class);
		getFunctionMethod.setAccessible(true);
		Function function = (Function) getFunctionMethod.invoke(application, operation);
		getFunctionMethod.setAccessible(false);

		switch (operation)
		{
			case CHANGE_LOCATION -> assertTrue(function instanceof ChangeLocation);
			case DO_ACTION -> assertTrue(function instanceof DoAction);
			case SERVICE_FUNCTION -> assertTrue(function instanceof ServiceFunction);
			default -> assertNull(function);
		}
	}
}


//	@Mock
//	CustomData customData;
//	@Mock
//	User user;
//	@Mock
//	Function function;
//	@Mock
//	Result result;
//
//	@Test
//	@ExtendWith(MockitoExtension.class)
//	void run()
//	{
//		doReturn(customData).when(controller).getCustomData();
//		doReturn(user).when(controller).getUser();
//		doReturn("location").when(customData).getParameter();
//
//
//		try (MockedStatic<FunctionType> util = Mockito.mockStatic(FunctionType.class))
//		{
//			util.when(FunctionType::getFunction).thenReturn(function);
//
//		}
//
//
//
//		FunctionType mockServiceFunction = mock(FunctionType.class);
//		when(mockServiceFunction.getFunction()).thenReturn(function);
//
//
//		doReturn(result).when(function).execute(user, customData);
//		Application application = new Application(controller);
//
//
//		Result testResult = spyApplication.run();
//		assertAll(() -> {
//			assertSame(result, testResult);
//			verify(controller, atMostOnce()).getCustomData();
//			verify(controller, atMostOnce()).getUser();
//			verify(customData).getParameter();
//			verify(function).execute(user, customData);
//			verify(controller).updateUser(user);
//		});
//
//	}
