package com.javarush.quest.kavtasyev.repository.yamldb;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class YAMLDBAssemblerTest
{

	@Test
	@DisplayName("Тестирование метода getInstance() класса YAMLDBAssembler")
	void getInstance() throws IOException
	{
		YAMLDBAssembler yamlDBAssembler = YAMLDBAssembler.getInstance();
		assertSame(yamlDBAssembler, YAMLDBAssembler.getInstance());
	}

	@Test
	@DisplayName("Тестирование метода getYamlDB класса YAMLDBAssembler")
	void whenGetYamlDBThenReturnYamlDBEntity() throws IOException
	{
		YAMLDBAssembler assembler = YAMLDBAssembler.getInstance();
		YAMLDB yamlDB = assembler.getYamlDB();
		assertNotNull(yamlDB);
	}
}