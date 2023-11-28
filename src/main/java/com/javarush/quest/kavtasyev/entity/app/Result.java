package com.javarush.quest.kavtasyev.entity.app;

import lombok.Getter;

@Getter
public class Result
{
	private String html;
	private Exception exception;

	public Result(String html)
	{
		this.html = html;
	}

	public Result(Exception exception)
	{
		this.exception = exception;
	}

	public Result(String html, Exception exception)
	{
		this.html = html;
		this.exception = exception;
	}
}
