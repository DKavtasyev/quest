package com.javarush.quest.kavtasyev.filters;

import com.javarush.quest.kavtasyev.repository.DB;
import com.javarush.quest.kavtasyev.repository.yamldb.YAMLDBAssembler;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;
import java.util.Set;

/**
 * Проверяет, имеется ли в базе данных логин, введённый пользователем. Наличие двух одинаковых логинов недопустимо,
 * поэтому фильтр не даёт пользователю зарегистрироваться с дублирующим логином.
 * @Before AuthorizationFilter
 * @After StartServlet
 * @servletNames StartServlet
 * @URLpattern /quest
 */
public class TakenLoginFilter extends HttpFilter
{
	@Override
	@SuppressWarnings("all")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		System.out.println("TakenLoginFilter");
		String login = req.getParameter("login");

		if (login == null)
		{
			System.out.println("TakenLoginFilter: если пользователь пока не вводил логин при регистрации, то тогда просто проходим дальше.");
			chain.doFilter(req, res);
			return;
		}
		login = login.toLowerCase();

		DB yamlDB = YAMLDBAssembler.getInstance().getYamlDB();
		Set<String> logins = yamlDB.getAllLogins();

		if (logins.contains(login))
		{
			System.out.println("TakenLoginFilter: такой логин уже существует.");
			req.setAttribute("login", login);
			req.setAttribute("loginistaken", "true");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("register.jsp");
			requestDispatcher.forward(req, res);
		}
		else
		{
			System.out.println("TakenLoginFilter: логин свободный, пропускаем дальше.");
			chain.doFilter(req, res);
		}
	}
}
