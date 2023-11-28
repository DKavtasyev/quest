package com.javarush.quest.kavtasyev.repository.yamldb;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.repository.DB;

import java.io.IOException;
import java.util.Set;

public class YAMLDB implements DB
{
	private final YAMLDBReader yamlDBReader;
	private final YAMLDBWriter yamldbWriter;

	public YAMLDB(YAMLDBReader yamlDBReader, YAMLDBWriter yamldbWriter)
	{
		this.yamlDBReader = yamlDBReader;
		this.yamldbWriter = yamldbWriter;
	}

	public void writeUser(User user) throws IOException
	{
		yamldbWriter.writeUser(user);
	}
	public User readUser(String login) throws IOException
	{
		return yamlDBReader.readUser(login);
	}

	public Set<String> getAllLogins() throws IOException
	{
		return yamlDBReader.getAllLogins();
	}
}
