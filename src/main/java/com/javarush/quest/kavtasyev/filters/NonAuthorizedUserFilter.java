package com.javarush.quest.kavtasyev.filters;

import com.javarush.quest.kavtasyev.util.CookieHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Предотвращает доступ неавторизованным пользователям к квесту. Если пользователь не отправляет Cookie с параметром
 * login, и если нет параметра login в запросе, тогда пользователю отправляется REDIRECT на начальную страницу. Если
 * у пользователя есть хотя бы один параметр, то пользователь пропускается дальше.
 * @Before login.jsp
 * @After AuthorizationFilter
 * @Name NonAuthorizedUserFilter
 * @URLpattern /quest, /quest/do-operation
 */
public class NonAuthorizedUserFilter extends HttpFilter
{
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		System.out.println("NonAuthorizedUserFilter");
		Cookie loginCookie = CookieHandler.getInstance().getLoginCookie(req);											//TODO решить, что делать с теми, у кого нет в сессии объекта User
		String login = req.getParameter("login");

		if (login == null && loginCookie == null)
		{
			System.out.println("NonAuthorizedUserFilter: пользователь неавторизован.");
			res.sendRedirect("/");
		}
		else
		{
			System.out.println("NonAuthorizedUserFilter: пользователь пропускается дальше для аутентификации");
			chain.doFilter(req, res);
		}
	}
}
