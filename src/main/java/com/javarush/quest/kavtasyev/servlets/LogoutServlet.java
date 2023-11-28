package com.javarush.quest.kavtasyev.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		System.out.println("LogoutServlet: doGet()");
		HttpSession session = req.getSession();
		session.setAttribute("attempt", 0);
		Cookie cookie = new Cookie("login", "");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);

		resp.sendRedirect("/");
	}
}
