package com.javarush.quest.kavtasyev.filters;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.repository.DB;
import com.javarush.quest.kavtasyev.repository.yamldb.YAMLDBAssembler;
import com.javarush.quest.kavtasyev.util.CookieHandler;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.SneakyThrows;

import java.io.IOException;

@SuppressWarnings("all")
/**
 * Проводит аутентификацию пользователя после отправки им логина и пароля. Если успешно, то управление передаётся
 * сервлету StartServlet и пользователь допускается к квесту. Если аутентификация не успешная, тогда пользователь
 * перенаправляется обратно на страницу авторизации с параметром authfail = true, ему выдаётся сообщение о непройденной
 * аутентификации.
 * @Before login.jsp
 * @Name AuthorizationFilter
 * @servletNames StartServlet
 * @URLpattern /quest
 */
public class AuthenticationFilter extends HttpFilter
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
		System.out.println("AuthenticationFilter");

		Cookie loginCookie = CookieHandler.getInstance().getLoginCookie(req);											// Если loginCookie != null
		if (loginCookie != null)
		{
			System.out.println("AuthenticationFilter: loginCookie проверяется позже. Пропускаем дальше.");
			chain.doFilter(req, res);																					// то тогда это REDIRECT авторизованного пользователя, loginCookie уже проверены
			return;
		}

		String login = req.getParameter("login").toLowerCase();
		String password = req.getParameter("password");

		User user = checkLogin(login);
		if(user == null)
		{
			System.out.println("AuthenticationFilter: пользователь не найден.");
			sendAuthorizationError(req, res);
			return;
		}

		if (user.getPassword().equals(password))
		{
			addSession(req, user);
			addCookie(res, login);
			System.out.println("AuthenticationFilter: пользователь прошёл аутентификацию.");
			chain.doFilter(req, res);
		}
		else
		{
			System.out.println("AuthenticationFilter: пароль введён неверно.");
			sendAuthorizationError(req, res);
		}
	}

	private static void addCookie(HttpServletResponse res, String login)
	{
		Cookie cookie = new Cookie("login", login);
		cookie.setMaxAge(30 * 24 * 60 * 60);
		res.addCookie(cookie);
	}

	private static void addSession(HttpServletRequest req, User user)
	{
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		req.setAttribute("authentication", "success");
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

	public void sendAuthorizationError(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		System.out.println("AuthenticationFilter: authfail");
		req.setAttribute("authfail", "true");
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(req, res);
	}
}
