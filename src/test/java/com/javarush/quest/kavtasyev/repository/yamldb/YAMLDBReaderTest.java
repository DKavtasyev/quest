package com.javarush.quest.kavtasyev.repository.yamldb;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.FlareGun;
import com.javarush.quest.kavtasyev.entity.arms.Machete;
import com.javarush.quest.kavtasyev.entity.arms.Spear;
import com.javarush.quest.kavtasyev.entity.arms.Truncheon;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.food.Fowl;
import com.javarush.quest.kavtasyev.entity.tool.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class YAMLDBReaderTest
{

	@Test
	void readUser() throws IOException
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
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("login.yaml")))
		{
			writer.write(userString);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		YAMLMapper mapper = new YAMLMapper();

		YAMLDBReader reader = new YAMLDBReader("", mapper);
		User user = reader.readUser("login");
		Files.delete(Path.of("login.yaml"));

		assertAll(() -> {
			assertEquals("name", user.getName());
			assertEquals("login", user.getLogin());
			assertEquals("password", user.getPassword());
			assertEquals("Forest", user.getLocation().getClass().getSimpleName());
			assertEquals("River", user.getLocationWithSnare().getClass().getSimpleName());
			assertEquals(75, user.getHealth());
			assertEquals(10, user.getSatiety());
			assertEquals(80, user.getWaterSaturation());
			assertFalse(user.isWasOnTheMountain());
			assertTrue(user.isFoundFlareGun());
			assertFalse(user.isTheBeaconIsOn());
			assertFalse(user.isThePlaneIsFoundOut());
			assertFalse(user.isHasShootAFlareGun());
			assertFalse(user.isGotLost());
			assertTrue(user.isFoundBeacon());

			assertTrue(user.isHasTheTool(Lighter.class));
			assertTrue(user.isHasTheTool(Rope.class));
			assertTrue(user.isHasTheTool(CarBattery.class));
			assertTrue(user.isHasTheTool(Compass.class));
			assertTrue(user.isHasTheTool(Beacon.class));

			assertTrue(user.isHasTheArm(Machete.class));
			assertTrue(user.isHasTheArm(Truncheon.class));
			assertTrue(user.isHasTheArm(Spear.class));
			assertTrue(user.isHasTheArm(FlareGun.class));
			assertEquals(5, user.getFlareGun().getFlares());

			assertTrue(user.isHasTheFood(Fish.class));
			assertTrue(user.isHasTheFood(Fowl.class));
		});
	}

	@Test
	void getAllLogins() throws IOException
	{
		String folderString = String.valueOf(Objects.requireNonNull(this.getClass().getResource(""))).substring(6) + "tempDir/";

		Path folder = Path.of(folderString);
		Path file1 = Path.of(folderString + "/login1.yaml");
		Path file2 = Path.of(folderString + "/login2.yaml");
		Path file3 = Path.of(folderString + "/login3.yaml");

		if (!Files.exists(folder))
			Files.createDirectory(folder);
		if (!Files.exists(file1))
			Files.createFile(file1);
		if (!Files.exists(file2))
			Files.createFile(file2);
		if (!Files.exists(file3))
			Files.createFile(file3);

		YAMLMapper mapper = new YAMLMapper();
		YAMLDBReader yamlDBReader = new YAMLDBReader(folderString, mapper);

		Set<String> allLogins = yamlDBReader.getAllLogins();

		Files.deleteIfExists(file1);
		Files.deleteIfExists(file2);
		Files.deleteIfExists(file3);
		Files.deleteIfExists(folder);

		assertAll(() -> {
			assertEquals(3, allLogins.size());
			assertTrue(allLogins.contains("login1"));
			assertTrue(allLogins.contains("login2"));
			assertTrue(allLogins.contains("login3"));
		});
	}
}