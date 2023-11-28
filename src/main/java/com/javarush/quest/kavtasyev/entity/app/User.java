package com.javarush.quest.kavtasyev.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javarush.quest.kavtasyev.entity.arms.Arms;
import com.javarush.quest.kavtasyev.entity.arms.FlareGun;
import com.javarush.quest.kavtasyev.entity.food.Food;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import com.javarush.quest.kavtasyev.entity.locations.StartBeach;
import com.javarush.quest.kavtasyev.entity.tool.Tool;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User
{
	// Данные для авторизации
	private String name;
	private String login;
	private String password;

	private Set<Tool> tools = new HashSet<>();
	private Set<Arms> arms = new HashSet<>();
	private Set<Food> foods = new HashSet<>();
	private Location locationWithSnare;
	private Location location;

	// Игровые параметры
	private int health = 100;
	private int satiety = 100;
	private int waterSaturation = 100;
	private boolean wasOnTheMountain = false;
	private boolean isFoundBeacon = false;
	private boolean foundFlareGun = false;
	private boolean theBeaconIsOn = false;
	private boolean thePlaneIsFoundOut = false;
	private boolean isGotLost = false;
	private boolean hasShootAFlareGun = false;

	public User()
	{
	}

	public User(String name, String login, String password)
	{
		this.name = name;
		this.login = login;
		this.password = password;
		location = new StartBeach();
	}

	@JsonIgnore
	public FlareGun getFlareGun()
	{
		for(Arms arm : arms)
		{
			if (arm instanceof FlareGun)
			{
				return (FlareGun) arm;
			}
		}
		return null;
	}

	public void shoot(FlareGun flareGun)
	{
		int flares = flareGun.takeAShot();
		if (flares < 1)
			arms.remove(flareGun);
	}

	public Arms chooseArm()
	{
		return arms.stream().max(Comparator.comparingInt(a -> a.getArmProperties().power())).orElse(null);
	}

	public <T  extends Arms> boolean isHasTheArm(Class<T> tClass)
	{
		return arms.stream().anyMatch(l -> l.getClass().equals(tClass));
	}

	public <T extends Tool> boolean isHasTheTool(Class<T> tClass)
	{
		return tools.stream().anyMatch(l -> l.getClass().equals(tClass));
	}

	public <T extends Food> boolean isHasTheFood(Class<T> tClass)
	{
		return foods.stream().anyMatch(l -> l.getClass().equals(tClass));
	}
}