package com.javarush.quest.kavtasyev.entity.app;

import com.javarush.quest.kavtasyev.entity.arms.*;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.food.Food;
import com.javarush.quest.kavtasyev.entity.food.Fowl;
import com.javarush.quest.kavtasyev.entity.locations.*;
import com.javarush.quest.kavtasyev.entity.tool.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("all")
class UserTest
{
	User user;

	@BeforeEach
	void createUser()
	{
		user = new User();
	}

	@Test
	@DisplayName("Тестирование метода shoot() класса User")
	void shoot()
	{
		FlareGun flareGun = new FlareGun(2);
		user.getArms().add(flareGun);
		assertAll(() -> {
			assertTrue(user.isHasTheArm(FlareGun.class));
			user.shoot(flareGun);
			assertTrue(user.isHasTheArm(FlareGun.class));
			assertEquals(1, flareGun.getFlares());
			user.shoot(flareGun);
			assertEquals(0, flareGun.getFlares());
			assertFalse(user.isHasTheArm(FlareGun.class));
		});
	}

	@Test
	@DisplayName("Тестирование метода getFlareGun() класса User")
	void getFlareGun()
	{
		FlareGun flareGun = new FlareGun(5);
		user.getArms().add(flareGun);
		assertSame(flareGun, user.getFlareGun());
	}

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@CsvSource({
			"false, false, false, false",
			"false, false, false, true ",
			"false, false, true , false",
			"false, false, true , true ",
			"false, true , false, false",
			"false, true , false, true ",
			"false, true , true , false",
			"false, true , true , true ",
			"true , false, false, false",
			"true , false, false, true ",
			"true , false, true , false",
			"true , false, true , true ",
			"true , true , false, false",
			"true , true , false, true ",
			"true , true , true , false",
			"true , true , true , true "
	})
	@DisplayName("Тестирование метода chooseArm() класса User")
	void chooseArm(boolean hasFlareGun,	boolean hasMachete,
				   boolean hasSpear,		boolean hasTruncheon)
	{
		Arms[] arms = new Arms[4];
		arms[0] = new FlareGun(2);
		arms[1] = new Machete();
		arms[2] = new Spear();
		arms[3] = new Truncheon();

		if (hasFlareGun)
			user.getArms().add(arms[0]);
		if (hasMachete)
			user.getArms().add(arms[1]);
		if (hasSpear)
			user.getArms().add(arms[2]);
		if (hasTruncheon)
			user.getArms().add(arms[3]);

		Arrays.sort(arms, (a1, a2) -> a2.getArmProperties().power() - a1.getArmProperties().power());

		Arms arm = user.chooseArm();
		assertAll(() -> {
			if (user.isHasTheArm(arms[0].getClass()))
				assertSame(arms[0], arm);
			else if (user.isHasTheArm(arms[1].getClass()))
				assertSame(arms[1], arm);
			else if (user.isHasTheArm(arms[2].getClass()))
				assertSame(arms[2], arm);
			else if (user.isHasTheArm(arms[3].getClass()))
				assertSame(arms[3], arm);
			else
				assertTrue(user.getArms().isEmpty());
		});

	}

	@ParameterizedTest
	@ValueSource(classes = {FlareGun.class, Truncheon.class, Machete.class, Spear.class})
	@DisplayName("Тестирование метода isHasTheArm(Class tClass) класса User")
	<T extends Arms> void isHasTheArm(Class<T> tClass)
	{
		Collections.addAll(user.getArms(), new FlareGun(5), new Truncheon(), new Machete());
		assertAll("Проверка всех оружий:", () -> {
			if (tClass != Spear.class)
				assertTrue(user.isHasTheArm(tClass));
			else
				assertFalse(user.isHasTheArm(tClass));
		});
	}

	@ParameterizedTest
	@ValueSource(classes = {Compass.class, Lighter.class, Beacon.class, Rope.class, CarBattery.class})
	@DisplayName("Тестирование метода isHasTheTool(Class tClass) класса User")
	<T extends Tool> void isHasTheTool(Class<T> tClass)
	{
		Collections.addAll(user.getTools(), new Compass(), new Lighter(), new Beacon(), new Rope());
		assertAll("Проверка всех инструментов:", () -> {
			if (tClass != CarBattery.class)
				assertTrue(user.isHasTheTool(tClass));
			else
				assertFalse(user.isHasTheTool(tClass));
		});
	}

	@Test
	@DisplayName("Тестирование метода isHasTheFood(Class tClass) класса User")
	void isHasTheFood()
	{
		user.getFoods().add(new Fowl());
		assertAll("Проверка еды", () -> {
			assertTrue(user.isHasTheFood(Fowl.class));
			assertFalse(user.isHasTheFood(Fish.class));
		});
	}

	@ParameterizedTest
	@ValueSource(strings = {"Vasya", "DlsAL1$mkVflxJw9bVHrt4F*1no*hipD", "Вася"})
	@DisplayName("Тестирование метода getName() класса User")
	void getName(String argument)
	{
		User user = new User(argument, "login", "password");
		assertEquals(argument, user.getName());
	}

	@ParameterizedTest
	@ValueSource(strings = {"Vasya", "DlsAL1$mkVflxJw9bVHrt4F*1no*hipD", "Вася"})
	@DisplayName("Тестирование метода getLogin() класса User")
	void getLogin(String argument)
	{
		User user = new User("name", argument, "password");
		assertEquals(argument, user.getLogin());
	}

	@ParameterizedTest
	@ValueSource(strings = {"1234Password", "DlsAL1$mkVflxJw9bVHrt4F*1no*hipD", "Вася"})
	@DisplayName("Тестирование метода getPassword() класса User")
	void getPassword(String argument)
	{
		User user = new User("name", "login", argument);
		assertEquals(argument, user.getPassword());
	}

	@Test
	@DisplayName("Тестирование метода getTools() класса User")
	void getTools()
	{
		Compass compass = new Compass();
		Lighter lighter = new Lighter();
		Beacon beacon = new Beacon();
		Rope rope = new Rope();
		Collections.addAll(user.getTools(), compass, lighter, beacon, rope);
		Set<Tool> tools = user.getTools();
		assertAll(() -> {
			assertTrue(tools.contains(compass));
			assertTrue(tools.contains(lighter));
			assertTrue(tools.contains(beacon));
			assertTrue(tools.contains(rope));
			assertFalse(tools.contains(new CarBattery()));
			assertThat(tools, hasSize(4));
			assertEquals(tools, user.getTools());
		});
	}

	@Test
	@DisplayName("Тестирование метода getArms() класса User")
	void getArms()
	{
		FlareGun flareGun = new FlareGun(5);
		Truncheon truncheon = new Truncheon();
		Machete machete = new Machete();
		Collections.addAll(user.getArms(), flareGun, truncheon, machete);
		Set<Arms> arms = user.getArms();
		assertAll(() -> {
			assertTrue(arms.contains(flareGun));
			assertTrue(arms.contains(truncheon));
			assertTrue(arms.contains(machete));
			assertFalse(arms.contains(new Spear()));
			assertThat(arms, hasSize(3));
			assertEquals(arms, user.getArms());
		});
	}

	@Test
	@DisplayName("Тестирование метода getFoods() класса User")
	void getFoods()
	{
		Fowl fowl = new Fowl();
		Fish fish = new Fish();
		user.getFoods().add(fish);
		Set<Food> foods = user.getFoods();
		assertAll(() -> {
			assertTrue(foods.contains(fish));
			assertFalse(foods.contains(fowl));
			assertThat(foods, hasSize(1));
			assertEquals(foods, user.getFoods());
		});
	}

	@ParameterizedTest
	@MethodSource("locationsWithSnareProvidedFactory")
	@DisplayName("Тестирование методов setLocationWithSnare(), getLocationWithSnare() класса User")
	void getLocationWithSnare(Location location)
	{
		assertAll(() -> {
			assertNull(user.getLocationWithSnare());
			user.setLocationWithSnare(location);
			assertEquals(location, user.getLocationWithSnare());
			user.setLocationWithSnare(null);
			assertNull(user.getLocationWithSnare());
		});
	}

	static Stream<Location> locationsWithSnareProvidedFactory()
	{
		return Stream.of(new River(),new Forest(), new Plain());
	}

	@ParameterizedTest
	@MethodSource("locationsProvidedFactory")
	@DisplayName("Тестирование методов setLocation(), getLocation() класса User")
	void getLocation(Location location)
	{
		user.setLocation(location);
		assertEquals(location, user.getLocation());
		assertAll(() -> {
			assertNull(user.getLocationWithSnare());
			user.setLocation(location);
			assertEquals(location, user.getLocation());
			user.setLocation(null);
			assertNull(user.getLocationWithSnare());
		});
	}

	static Stream<Location> locationsProvidedFactory()
	{
		return Stream.of(new StartBeach(), new Beach(), new Forest(), new River(), new Jungle(), new FarBeach(), new Plain(), new Cave(), new Mountain(), new Settlement());
	}

	@Test
	@DisplayName("Тестирование методов setHealth(), getHealth() класса User")
	void getHealth()
	{
		assertAll(() -> {
			assertEquals(100, user.getHealth());
			user.setHealth(Math.max(user.getHealth() - 45, 0));
			assertEquals(55, user.getHealth());
			user.setHealth(Math.min(user.getHealth() + 30, 100));
			assertEquals(85, user.getHealth());
			user.setHealth(Math.min(user.getHealth() + 100, 100));
			assertEquals(100, user.getHealth());
		});
	}

	@Test
	@DisplayName("Тестирование методов setWasOnTheMountain, isWasOnTheMountain() класса User")
	void isWasOnTheMountain()
	{
		assertAll(() -> {
			assertFalse(user.isWasOnTheMountain());
			user.setWasOnTheMountain(true);
			assertTrue(user.isWasOnTheMountain());
			user.setWasOnTheMountain(false);
			assertFalse(user.isWasOnTheMountain());
		});
	}

	@Test
	@DisplayName("Тестирование методов setFoundBeacon isFoundBeacon() класса User")
	void isFoundBeacon()
	{
		assertAll(() -> {
			assertFalse(user.isFoundBeacon());
			user.setFoundBeacon(true);
			assertTrue(user.isFoundBeacon());
			user.setFoundBeacon(false);
			assertFalse(user.isFoundBeacon());
		});
	}

	@Test
	@DisplayName("Тестирование методов setTheBeaconIsOn, isTheBeaconIsOn() класса User")
	void isTheBeaconIsOn()
	{
		assertAll(() -> {
			assertFalse(user.isTheBeaconIsOn());
			user.setTheBeaconIsOn(true);
			assertTrue(user.isTheBeaconIsOn());
			user.setTheBeaconIsOn(false);
			assertFalse(user.isTheBeaconIsOn());
		});
	}

	@Test
	@DisplayName("Тестирование методов setThePlaneIsFoundOut, isThePlaneIsFoundOut() класса User")
	void isThePlaneIsFoundOut()
	{
		assertAll(() -> {
			assertFalse(user.isThePlaneIsFoundOut());
			user.setThePlaneIsFoundOut(true);
			assertTrue(user.isThePlaneIsFoundOut());
			user.setThePlaneIsFoundOut(false);
			assertFalse(user.isThePlaneIsFoundOut());
		});
	}

	@Test
	@DisplayName("Тестирование методов setGotLost, isGotLost() класса User")
	void isGotLost()
	{
		assertAll(() -> {
			assertFalse(user.isGotLost());
			user.setGotLost(true);
			assertTrue(user.isGotLost());
			user.setGotLost(false);
			assertFalse(user.isGotLost());
		});
	}

	@Test
	@DisplayName("Тестирование методов setHasShootAFlareGun, isHasShootAFlareGun() класса User")
	void isHasShootAFlareGun()
	{
		assertAll(() -> {
			assertFalse(user.isHasShootAFlareGun());
			user.setHasShootAFlareGun(true);
			assertTrue(user.isHasShootAFlareGun());
			user.setHasShootAFlareGun(false);
			assertFalse(user.isHasShootAFlareGun());
		});
	}

	@ParameterizedTest
	@DisplayName("Тестирование метода setName() класса User")
	@ValueSource(strings = {"1234Password", "DlsAL1$mkVflxJw9bVHrt4F*1no*hipD", "Вася"})
	void setName(String argument)
	{
		user.setName(argument);
		assertEquals(argument, user.getName());
	}

	@ParameterizedTest
	@DisplayName("Тестирование метода setLogin() класса User")
	@ValueSource(strings = {"1234Password", "DlsAL1$mkVflxJw9bVHrt4F*1no*hipD", "Вася"})
	void setLogin(String argument)
	{
		user.setLogin(argument);
		assertEquals(argument, user.getLogin());
	}

	@ParameterizedTest
	@DisplayName("Тестирование метода setPassword() класса User")
	@ValueSource(strings = {"1234Password", "DlsAL1$mkVflxJw9bVHrt4F*1no*hipD", "Вася"})
	void setPassword(String argument)
	{
		user.setPassword(argument);
		assertEquals(argument, user.getPassword());
	}
}