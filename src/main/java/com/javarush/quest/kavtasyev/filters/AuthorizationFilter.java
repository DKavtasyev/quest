package com.javarush.quest.kavtasyev.filters;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.repository.DB;
import com.javarush.quest.kavtasyev.repository.yamldb.YAMLDBAssembler;
import com.javarush.quest.kavtasyev.util.CookieHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Objects;

public class AuthorizationFilter extends HttpFilter
{
	private DB yamlDB;

	@SneakyThrows
	@Override
	public void init()
	{
		super.init();
		yamlDB = YAMLDBAssembler.getInstance().getYamlDB();
	}

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		System.out.println("AuthorizationFilter");
		Cookie loginCookie = CookieHandler.getInstance().getLoginCookie(req);											// Если loginCookie != null


		try
		{
			Objects.requireNonNull(req.getAttribute("authentication"));
			if(!req.getAttribute("authentication").equals("success"))
				throw new NullPointerException();
			System.out.println("AuthorizationFilter: пропуск пользователя, прошедшего аутентификацию.");
			chain.doFilter(req, res);
		}
		catch(NullPointerException ignored)
		{
		}


		User user = checkLogin(loginCookie.getValue());
		if(user == null)																							// Если пользователь не найден, тогда loginCookie недействителен.
		{
			System.out.println("NonAuthorizedUserFilter: логин [" + loginCookie.getValue() + "] из пользовательских cookie не найден. Cookie будет удалён.");
			Cookie cookie = new Cookie("login", "");
			cookie.setMaxAge(0);
			res.addCookie(cookie);																					// удаляем loginCookie и отсылаем на начальную страницу
			res.sendRedirect("/");
		}
		else
		{
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			System.out.println("AuthorizationFilter: loginCookie проверен, пропускается дальше.");
			chain.doFilter(req, res);
		}
	}

	private User checkLogin(String login)
	{
		User user;
		try
		{
			user = yamlDB.readUser(login);
		}
		catch (IOException e)
		{
			return null;
		}
		return user;
	}
}
