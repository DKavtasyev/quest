package com.javarush.quest.kavtasyev.filters;

import com.javarush.quest.kavtasyev.repository.DB;
import com.javarush.quest.kavtasyev.repository.yamldb.YAMLDBAssembler;
import com.javarush.quest.kavtasyev.util.CookieHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

/**
 * Если пользователь авторизован, т. е. запрос от пользователя содержит cookie с
 * действующим именем пользователя, то фильтр перенаправляет пользователя на
 * то место в квесте, где пользователь остановился в прошлый раз.
 * Если не авторизован, пропускает пользователя дальше к запрошенной странице авторизации
 * или регистрации.
 * @Before index.jsp
 * @After LoginServlet
 * @Name AuthorizedUserFilter
 * @URLpattern /login, /register, /
 */
public class AuthorizedUserFilter extends HttpFilter
{
	private DB yamlDB;

	@Override
	public void init() throws ServletException																			// Инициализация фильтра
	{
		super.init();
		try
		{
			yamlDB = YAMLDBAssembler.getInstance().getYamlDB();															// Получение объекта чтения из базы данных YAML
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		System.out.println("AuthorizedUserFilter");
		Cookie loginCookie = CookieHandler.getInstance().getLoginCookie(req);											// Чтение пользовательского Cookie с параметром login

		try
		{
			Objects.requireNonNull(loginCookie);
			Set<String> logins = yamlDB.getAllLogins();
			if(!logins.contains(loginCookie.getValue()))
				throw new IOException();
			System.out.println("AuthorizedUserFilter: Пользователь присутствует в базе данных. User cookie name: [" + loginCookie.getName() + "] cookie value: [" + loginCookie.getValue() + "]");
			res.sendRedirect("/quest");																				// Пользователь присутствует в базе данных, направляем его на авторизацию по его данным и в квест
		}
		catch (IOException e)
		{
			Cookie cookie = new Cookie("login", "");
			cookie.setMaxAge(0);
			res.addCookie(cookie);
			System.out.println("AuthorizedUserFilter: логин [" + loginCookie.getValue() + "] из пользовательских cookie не найден. Cookie будет удалён.");
			chain.doFilter(req, res);																					// Логин у пользователя недействителен, пропускаем его на страницу авторизации
		}
		catch (NullPointerException e)
		{
			System.out.println("AuthorizedUserFilter: пользователь не авторизован.");
			chain.doFilter(req, res);																					// Если cookie равно null, то пользователь неавторизован, пропускаем его на страницу авторизации
		}
	}
}
