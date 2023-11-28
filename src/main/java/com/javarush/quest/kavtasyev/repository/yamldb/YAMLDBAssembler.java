package com.javarush.quest.kavtasyev.repository.yamldb;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class YAMLDBAssembler
{
	private volatile static YAMLDBAssembler instance;
	private final YAMLDB yamlDB;

	private YAMLDBAssembler() throws IOException
	{
		YAMLMapper mapper = new YAMLMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String database = readPathToDB();
		YAMLDBReader yamlDBReader = new YAMLDBReader(database, mapper);
		YAMLDBWriter yamlDBWriter = new YAMLDBWriter(database, mapper);
		yamlDB = new YAMLDB(yamlDBReader, yamlDBWriter);
	}

	public static YAMLDBAssembler getInstance() throws IOException
	{
		YAMLDBAssembler localInstance = instance;
		if (localInstance == null)
		{
			synchronized (YAMLDBAssembler.class)
			{
				localInstance = instance;
				if (localInstance == null)
				{
					instance = new YAMLDBAssembler();
				}
			}
		}
		return instance;
	}

	@SuppressWarnings("all")
	private String readPathToDB() throws IOException
	{
		String path = String.valueOf(Objects.requireNonNull(this.getClass().getResource(""))).substring(6);
		String database = new StringBuilder(path).append("database/").toString();

		Path dataBasePath = Path.of(database);
		if (!Files.exists(dataBasePath))
			Files.createDirectory(dataBasePath);
		return database;
	}

	public YAMLDB getYamlDB()
	{
		return yamlDB;
	}

}
