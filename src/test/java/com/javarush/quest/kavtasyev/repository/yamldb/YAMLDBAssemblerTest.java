package com.javarush.quest.kavtasyev.repository.yamldb;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class YAMLDBAssemblerTest
{

	@Test
	void getInstance() throws IOException
	{
		YAMLDBAssembler yamlDBAssembler = YAMLDBAssembler.getInstance();
		assertSame(yamlDBAssembler, YAMLDBAssembler.getInstance());
	}

	@Test
	void getYamlDB() throws IOException
	{
		YAMLDBAssembler assembler = YAMLDBAssembler.getInstance();
		YAMLDB yamlDB = assembler.getYamlDB();
		assertNotNull(yamlDB);
	}
}