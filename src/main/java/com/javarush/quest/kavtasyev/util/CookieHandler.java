package com.javarush.quest.kavtasyev.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieHandler
{
	private volatile static CookieHandler instance;

	private CookieHandler()
	{
	}

	public static CookieHandler getInstance()
	{
		CookieHandler localInstance = instance;

		if (localInstance == null)
		{
			synchronized (CookieHandler.class)
			{
				localInstance = instance;
				if (localInstance == null)
				{
					instance = new CookieHandler();
				}
			}
		}
		return instance;
	}

	public Cookie getLoginCookie(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		Cookie loginCookie = null;
		for(Cookie c : cookies)
		{
			if (c.getName().equals("login"))
				loginCookie = c;
		}
		return loginCookie;
	}
}
