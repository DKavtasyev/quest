package com.javarush.quest.kavtasyev.controllers;

import com.javarush.quest.kavtasyev.util.LocationContainer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "StartServlet", value = "/quest")
public class StartServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("StartServlet: doGet()");

		LocationContainer locationContainer = LocationContainer.getInstance();
		locationContainer.clearLocations();

		setAttemptValue(req);

		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/quest.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("StartServlet: doPost()");

		setAttemptValue(req);

		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/quest.jsp");
		dispatcher.forward(req, resp);
	}

	private void setAttemptValue(HttpServletRequest req)
	{
		HttpSession session = req.getSession();

		if (session.getAttribute("attempt") == null || (int)session.getAttribute("attempt") == 0)
		{
			session.setAttribute("attempt", 1);
		}
		else
			session.setAttribute("attempt", (int)session.getAttribute("attempt") + 1);
	}
}
