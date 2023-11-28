package com.javarush.quest.kavtasyev.service;

import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;

public interface Function
{
	Result execute(User user, CustomData customData);
}
