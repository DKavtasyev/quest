package com.javarush.quest.kavtasyev.servlets;

import com.javarush.quest.kavtasyev.app.Application;
import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet(name = "QuestServlet", value = "/quest/do-operation")
public class QuestServlet extends HttpServlet
{
	private CustomData customData;
	private HttpSession session;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		System.out.println("QuestServlet: doGet()");
		session = req.getSession();
		customData = receiveCustomData(req);
		Application application = new Application(this);
		Result result = application.run();
		PrintWriter out = resp.getWriter();
		out.println(result.getHtml());
	}

	private CustomData receiveCustomData(HttpServletRequest req)
	{
		Enumeration<String> params = req.getParameterNames();
		Iterator<String> it = params.asIterator();
		String param = it.next();
		return new CustomData(param, req.getParameter(param));
	}

	public CustomData getCustomData()
	{
		return customData;
	}

	public User getUser()
	{
		return (User) session.getAttribute("user");
	}

	public void updateUser(User user)
	{
		session.setAttribute("user", user);
	}
}


