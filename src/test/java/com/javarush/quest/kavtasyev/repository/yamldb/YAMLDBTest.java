package com.javarush.quest.kavtasyev.repository.yamldb;

import com.javarush.quest.kavtasyev.entity.app.User;
import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("Тестирование метода writeUser(User user) класса YAMLDB")
	void writeUserThenTheCallSameMethodInYAMLDBWriterInstance() throws IOException
	{
		yamlDB.writeUser(user);
		Mockito.verify(yamlDBWriter, Mockito.atMostOnce()).writeUser(user);
	}

	@Test
	@DisplayName("Тестирование метода readUser(String login) класса YAMLDB")
	void readUserThenCallTheSameMethodInYAMLDBReaderInstance() throws IOException
	{
		String login = "login";
		yamlDB.readUser(login);
		Mockito.verify(yamlDBReader, Mockito.atMostOnce()).readUser(login);
	}

	@Test
	@DisplayName("Тестирование метода getAllLogins() класса YAMLDB")
	void getAllLoginsThenCallTheSameMethodInYAMLDBReaderInstance() throws IOException
	{
		yamlDB.getAllLogins();
		Mockito.verify(yamlDBReader, Mockito.atMostOnce()).getAllLogins();
	}
}