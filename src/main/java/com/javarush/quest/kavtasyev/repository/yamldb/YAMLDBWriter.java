package com.javarush.quest.kavtasyev.repository.yamldb;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.quest.kavtasyev.entity.app.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class YAMLDBWriter
{
	private final String database;
	private final YAMLMapper mapper;

	public YAMLDBWriter(String database, YAMLMapper mapper)
	{
		this.database = database;
		this.mapper = mapper;
	}

	public void writeUser(User user) throws IOException
	{
		String login = user.getLogin().toLowerCase();
		String yamlContent = mapper.writeValueAsString(user);
		writeYAMLContent(yamlContent, login);
	}

	@SuppressWarnings("all")
	private void writeYAMLContent(String yamlContent, String login) throws IOException
	{
		StringBuilder sb = new StringBuilder(database);
		String path = sb.append(login).append(".yaml").toString();
		try(PrintWriter printWriter = new PrintWriter(new FileWriter(path)))
		{
			printWriter.print(yamlContent);
		}
	}
}
