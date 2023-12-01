package com.javarush.quest.kavtasyev.controllers;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.repository.DB;
import com.javarush.quest.kavtasyev.repository.yamldb.YAMLDBAssembler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("RegisterServlet: doGet()");
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/register.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("RegisterServlet: doPost()");
		String name = req.getParameter("name");
		String login = req.getParameter("login").toLowerCase();
		String password = req.getParameter("password");

		DB yamlDB = YAMLDBAssembler.getInstance().getYamlDB();
		User user = new User(name, login, password);
		yamlDB.writeUser(user);

		req.setAttribute("login", login);
		req.setAttribute("password", password);
		doGet(req, resp);
	}
}
