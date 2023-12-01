package com.javarush.quest.kavtasyev.repository.yamldb;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.FlareGun;
import com.javarush.quest.kavtasyev.entity.arms.Machete;
import com.javarush.quest.kavtasyev.entity.arms.Spear;
import com.javarush.quest.kavtasyev.entity.arms.Truncheon;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.food.Fowl;
import com.javarush.quest.kavtasyev.entity.locations.Forest;
import com.javarush.quest.kavtasyev.entity.locations.River;
import com.javarush.quest.kavtasyev.entity.tool.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YAMLDBWriterTest
{

	@Test
	@DisplayName("Тестирование метода writeUser(User user) класса YAMLDBWriter")
	void writeUserEntityToDataBase() throws IOException
	{
		String userString = """
				---
				name: "name"
				login: "login"
				password: "password"
				tools:
				- !<Lighter> {}
				- !<Rope> {}
				- !<CarBattery> {}
				- !<Compass> {}
				- !<Beacon> {}
				arms:
				- !<Machete> {}
				- !<Truncheon> {}
				- !<Spear> {}
				- !<FlareGun>
				  flares: 5
				foods:
				- !<Fish> {}
				- !<Fowl> {}
				locationWithSnare: !<River>
				  htmlActionButtons: ""
				  htmlAlerts: ""
				  snareIsSet: false
				location: !<Forest>
				  htmlActionButtons: ""
				  htmlAlerts: ""
				  snareIsSet: false
				health: 75
				satiety: 10
				waterSaturation: 80
				wasOnTheMountain: false
				foundFlareGun: true
				theBeaconIsOn: false
				thePlaneIsFoundOut: false
				hasShootAFlareGun: false
				gotLost: false
				foundBeacon: true
				""";
		List<String> userList = List.of(userString.split("\n"));

		YAMLMapper mapper = new YAMLMapper();

		YAMLDBWriter writer = new YAMLDBWriter("", mapper);
		User user = new User("name", "login", "password");
		user.getTools().add(new Lighter());
		user.getTools().add(new Beacon());
		user.getTools().add(new CarBattery());
		user.getTools().add(new Rope());
		user.getTools().add(new Compass());
		user.getArms().add(new Spear());
		user.getArms().add(new Truncheon());
		user.getArms().add(new FlareGun(5));
		user.getArms().add(new Machete());
		user.getFoods().add(new Fish());
		user.getFoods().add(new Fowl());
		user.setLocation(new Forest());
		user.setLocationWithSnare(new River());
		user.setHealth(75);
		user.setSatiety(10);
		user.setWaterSaturation(80);
		user.setFoundFlareGun(true);
		user.setFoundBeacon(true);
		writer.writeUser(user);

		String result;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader("login.yaml")))
		{
			while(reader.ready())
				sb.append(reader.readLine()).append("\n");
		}
		Files.delete(Path.of("login.yaml"));

		result = sb.toString();
		List<String> resultList = List.of(result.split("\n"));

		assertAll(() -> {
			assertEquals(userList.size(), resultList.size());
			for(String s : userList)
			{
				assertTrue(resultList.contains(s));
			}
		});
	}
}