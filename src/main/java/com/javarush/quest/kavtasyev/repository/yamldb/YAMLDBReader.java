package com.javarush.quest.kavtasyev.repository.yamldb;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.quest.kavtasyev.entity.app.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class YAMLDBReader
{

	private final String database;
	private final YAMLMapper mapper;

	public YAMLDBReader(String database, YAMLMapper mapper)
	{
		this.database = database;
		this.mapper = mapper;
	}

	public User readUser(String login) throws IOException
	{
		return mapper.readValue(readYAMLContent(login), User.class);
	}

	public Set<String> getAllLogins() throws IOException
	{
		DirectoryStream<Path> usersStorage = Files.newDirectoryStream(Path.of(database));
		return addAllLogins(usersStorage);
	}

	@SuppressWarnings("all")
	private String readYAMLContent(String login) throws IOException
	{
		StringBuilder yamlContent = new StringBuilder();
		String path = new StringBuilder(database).append(login).append(".yaml").toString();
		try(BufferedReader reader = new BufferedReader(new FileReader(path)))
		{
			while (reader.ready())
			{
				yamlContent.append(reader.readLine()).append("\n");
			}
		}
		return yamlContent.toString();
	}

	private Set<String> addAllLogins(DirectoryStream<Path> files)
	{
		Set<String> logins = new HashSet<>();
		for(Path p : files)
		{
			String s = p.toString();
			if(s.endsWith(".yaml"))
			{
				String s1 = s.substring(database.length(), s.length() - 5);
				logins.add(s1);
			}
		}
		return logins;
	}
}
