package com.javarush.quest.kavtasyev.repository.yamldb;

import com.javarush.quest.kavtasyev.entity.app.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class YAMLDBTest
{
	@Mock
	YAMLDBReader yamlDBReader;// = new YAMLDBReader(database, mapper);
	@Mock
	YAMLDBWriter yamlDBWriter;// = new YAMLDBWriter(database, mapper);
	@Mock
	User user = new User();
	@Mock
	YAMLDB yamlDB;

	@Test
	void writeUser() throws IOException
	{
		yamlDB.writeUser(user);
		Mockito.verify(yamlDBWriter, Mockito.atMostOnce()).writeUser(user);
	}

	@Test
	void readUser() throws IOException
	{
		String login = "login";
		yamlDB.readUser(login);
		Mockito.verify(yamlDBReader, Mockito.atMostOnce()).readUser(login);
	}

	@Test
	void getAllLogins() throws IOException
	{
		yamlDB.getAllLogins();
		Mockito.verify(yamlDBReader, Mockito.atMostOnce()).getAllLogins();
	}
}