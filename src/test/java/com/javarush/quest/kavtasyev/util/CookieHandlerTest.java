package com.javarush.quest.kavtasyev.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertSame;

class CookieHandlerTest
{

	@Test
	@DisplayName("Тестирование метода getInstance() класса CookieHandler")
	void getInstanceOfCookieHandler()
	{
		CookieHandler cookieHandler = CookieHandler.getInstance();
		assertSame(cookieHandler, CookieHandler.getInstance());
	}

	@Mock
	HttpServletRequest request;

	@Test
	@ExtendWith(MockitoExtension.class)
	@DisplayName("Тестирование метода getLoginCookie(HttpServletRequest request) класса CookieHandler")
	void getLoginCookieFromHttpServletRequest()
	{
		Cookie[] cookies = new Cookie[1];
		cookies[0] = new Cookie("login", "value");
		Mockito.doReturn(cookies).when(request).getCookies();
		Cookie loginCookie = CookieHandler.getInstance().getLoginCookie(request);
		assertAll(() -> {
			Mockito.verify(request).getCookies();
			assertSame(cookies[0], loginCookie);
		});
	}
}