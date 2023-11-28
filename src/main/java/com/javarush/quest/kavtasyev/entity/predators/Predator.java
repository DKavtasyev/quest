package com.javarush.quest.kavtasyev.entity.predators;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Location;

public interface Predator
{
	void attack(User user, Location location);
}
