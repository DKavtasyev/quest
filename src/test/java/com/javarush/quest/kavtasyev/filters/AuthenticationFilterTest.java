package com.javarush.quest.kavtasyev.filters;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.repository.yamldb.YAMLDB;
import com.javarush.quest.kavtasyev.repository.yamldb.YAMLDBAssembler;
import com.javarush.quest.kavtasyev.util.CookieHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationFilterTest
{
	@Spy
	AuthenticationFilter filter;
	@Mock
	HttpServletRequest req;
	@Mock
	HttpServletResponse res;
	@Mock
	FilterChain chain;
	@Mock
	YAMLDBAssembler assembler;
	@Mock
	YAMLDB yamlDB;
	@Mock
	CookieHandler cookieHandler;
	@Mock
	Cookie receivedCookie;
	@Spy
	User user = new User("name", "rightLogin", "rightPassword");
	@Mock
	HttpSession session;
	@Mock
	RequestDispatcher requestDispatcher;

	@Test
	void init()
	{
		try (MockedStatic<YAMLDBAssembler> util = mockStatic(YAMLDBAssembler.class))
		{
			util.when(YAMLDBAssembler::getInstance).thenReturn(assembler);
			doReturn(yamlDB).when(assembler).getYamlDB();
			filter.init();
			assertAll(() -> {
				util.verify(YAMLDBAssembler::getInstance);
				verify(assembler).getYamlDB();
			});
		}
	}

	@ParameterizedTest
	@CsvSource({
			"rightLogin, rightPassword, true ",
			"rightLogin, wrongPassword, true ",
			"wrongLogin, rightPassword, true ",
			"wrongLogin, wrongPassword, true ",
			"rightLogin, rightPassword, false",
			"wrongLogin, wrongPassword, false"
	})
	@SuppressWarnings("all")
	void doFilter(String login, String password, boolean cookieIsNull) throws ServletException, IOException
	{
		login = login.toLowerCase();

		try (MockedStatic<YAMLDBAssembler> utilAssembler = mockStatic(YAMLDBAssembler.class);
			 MockedStatic<CookieHandler> utilCookieHandler = mockStatic(CookieHandler.class))
		{
			utilAssembler.when(YAMLDBAssembler::getInstance).thenReturn(assembler);
			doReturn(yamlDB).when(assembler).getYamlDB();
			filter.init();
			utilCookieHandler.when(CookieHandler::getInstance).thenReturn(cookieHandler);
			if (cookieIsNull)
				doReturn(null).when(cookieHandler).getLoginCookie(any(HttpServletRequest.class));
			else
				doReturn(receivedCookie).when(cookieHandler).getLoginCookie(any(HttpServletRequest.class));
			lenient().when(req.getParameter("login")).thenReturn(login);
			lenient().when(req.getParameter("password")).thenReturn(password);
			lenient().when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

			lenient().when(yamlDB.readUser("rightlogin")).thenReturn(user);
			lenient().when(yamlDB.readUser("wrongLogin")).thenReturn(null);

			lenient().when(req.getSession()).thenReturn(session);

			filter.doFilter(req, res, chain);

			String finalLogin = login;
			assertAll(() -> {
				if (!cookieIsNull)
				{
					verify(chain).doFilter(req, res);
				}
				else
				{
					if (finalLogin.equals("rightlogin") && password.equals("rightPassword"))
					{
						verify(session).setAttribute("user", user);
						verify(req).setAttribute("authentication", "success");
						verify(res).addCookie(any(Cookie.class));
						verify(chain).doFilter(req, res);
					}
					else
					{
						verify(filter).sendAuthorizationError(req, res);
					}
				}
			});
		}
	}

	@Test
	@SuppressWarnings("all")
	void sendAuthorizationError() throws ServletException, IOException
	{
		doReturn(requestDispatcher).when(req).getRequestDispatcher("login.jsp");
		filter.sendAuthorizationError(req, res);
		assertAll(() -> {
			verify(req).setAttribute("authfail", "true");
			verify(requestDispatcher).forward(req, res);
		});
	}
}