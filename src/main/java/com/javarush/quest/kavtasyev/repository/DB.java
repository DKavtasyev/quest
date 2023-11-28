package com.javarush.quest.kavtasyev.repository;

import com.javarush.quest.kavtasyev.entity.app.User;

import java.io.IOException;
import java.util.Set;

public interface DB
{
	void writeUser(User user) throws IOException;

	User readUser(String login) throws IOException;

	Set<String> getAllLogins() throws IOException;
}
