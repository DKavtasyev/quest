package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.FlareGun;
import com.javarush.quest.kavtasyev.entity.arms.Spear;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.food.Fowl;
import com.javarush.quest.kavtasyev.entity.tool.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("all")
@ExtendWith(MockitoExtension.class)
class LocationTest
{
	@Mock
	ThreadLocalRandom random;

	@Spy
	User user = new User();

	@ParameterizedTest
	@CsvSource({
			"0.0, false, false", "0.0, true , false",
			"0.0, false, true ", "0.0, true , true ",

			"0.1, false, false",
			"0.2, false, false", "0.3, false, false",
			"0.4, false, false", "0.5, false, false",
			"0.6, false, false", "0.7, false, false",
			"0.8, false, false", "0.9, false, false",

			"1.0, false, false", "1.0, true , false",
			"1.0, false, true ", "1.0, true , true "})
	@SuppressWarnings("all")
	void getLost(double randomValue, boolean hasCompass, boolean gotLost)
	{
		if (hasCompass)
			user.getTools().add(new Compass());
		user.setGotLost(gotLost);
		Location location = new Jungle();
		user.setLocation(location);
		location.setRandom(random);
		location.user = user;
		location.htmlLocationButtons = spy(location.htmlLocationButtons);
		location.htmlAlerts = spy(location.htmlAlerts);

		doReturn(randomValue).when(random).nextDouble();
		LocationProperties properties = location.getProperties();

		String foundOutTheWayAlert = new StringBuilder().append(ALARM_OPEN_DIV_TAG)
				.append(YOU_GOT_LOST_BUT_COMPASS_BROUGHT_YOU_OUT)
				.append(ALARM_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();

		String gotLostAlert = new StringBuilder().append(ALARM_OPEN_DIV_TAG)
				.append(YOU_GOT_LOST)
				.append(ALARM_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();
		String gotLostButtons = new StringBuilder().append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
						.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_LOOK_FOR_A_WAY_OUT, BUTTON_LOOK_FOR_A_WAY_OUT))
						.append(CLOSE_DIV_TAG).toString();

		location.getLost(properties.getLostProbability());

		assertAll(() -> {
			if (randomValue < properties.getLostProbability() && !gotLost)
			{
				if (hasCompass)
				{
					assertEquals(foundOutTheWayAlert, location.getHtmlAlerts().toString());
					assertFalse(user.isGotLost());
					verifyNoInteractions(location.htmlLocationButtons);
				}
				else
				{
					assertEquals(gotLostAlert, location.getHtmlAlerts().toString());
					assertTrue(user.isGotLost());
					assertEquals(gotLostButtons, location.htmlLocationButtons.toString());
				}
			}
			else
			{
				verifyNoInteractions(location.htmlAlerts);
				verifyNoInteractions(location.htmlLocationButtons);
			}
		});


	}

	@ParameterizedTest
	@CsvSource({
			"0.0, false, false, false",
			"0.0, true , false, false",
			"0.0, false, true , false",
			"0.0, true , true , false",
			"0.0, true , true , true",

			"0.1, true , true , false",
			"0.2, true , true , false",
			"0.3, true , true , false",
			"0.4, true , true , false",
			"0.5, true , true , false",
			"0.6, true , true , false",
			"0.7, true , true , false",
			"0.8, true , true , false",
			"0.9, true , true , false",

			"1.0, false, false, false",
			"1.0, true , false, false",
			"1.0, false, true , false",
			"1.0, true , true , false",
			"1.0, true , true , true"
	})
	void findOutThePlane(double randomValue, boolean beaconIsOn, boolean planeIsNotFoundOut, boolean hasFlareGun)
	{
		user.setTheBeaconIsOn(beaconIsOn);
		user.setThePlaneIsFoundOut(!planeIsNotFoundOut);
		if (hasFlareGun)
			user.getArms().add(new FlareGun(5));

		Location location = new Plain();
		user.setLocation(location);
		doReturn(randomValue).when(random).nextDouble();
		location.setRandom(random);
		location.user = user;
		LocationProperties properties = location.getProperties();

		location.htmlAlerts = spy(location.htmlAlerts);
		location.htmlActionButtons = spy(location.htmlActionButtons);

		String youFoundThePlaneAlert = new StringBuilder().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_FOUND_OUT_THE_PLANE)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();
		String shootAFlareGunButton = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_SHOOT_A_FLARE_GUN, BUTTON_SHOOT_A_FLARE_GUN)).toString();

		location.findOutThePlane(properties.findOutThePlaneProbability());

		assertAll(() -> {
			if (randomValue < properties.findOutThePlaneProbability() && beaconIsOn && planeIsNotFoundOut)
			{
				assertEquals(youFoundThePlaneAlert, location.htmlAlerts.toString());
				if (hasFlareGun)
					assertTrue(location.htmlActionButtons.toString().contains(shootAFlareGunButton));
				else
					verifyNoInteractions(location.htmlActionButtons);
			}
			else
			{
				verifyNoInteractions(location.htmlActionButtons);
				verifyNoInteractions(location.htmlAlerts);
			}
		});
	}

	@Test
	void clearHtmlTexts()
	{
		Location location = new Beach();
		location.htmlLocationText.append("1234567890");
		location.htmlLocationButtons.append("1234567890");
		location.htmlActionButtons.append("1234567890");
		location.htmlAlerts.append("1234567890");
		location.htmlScripts.append("1234567890");

		location.clearHtmlTexts();

		assertAll(() -> {
			assertTrue(location.htmlLocationText.isEmpty());
			assertTrue(location.htmlLocationButtons.isEmpty());
			assertTrue(location.htmlActionButtons.isEmpty());
			assertTrue(location.htmlAlerts.isEmpty());
			assertTrue(location.htmlScripts.isEmpty());
		});
	}

	@Test
	void setHealth()
	{
		Location location = new Beach();
		location.user = user;

		String s = new StringBuilder().append(String.format(SET_HEALTH_SCRIPT, user.getHealth())).toString();

		location.setHealth();

		assertEquals(s, location.htmlScripts.toString());
	}

	@Test
	void generateHtml()
	{
		Location location = new Beach();
		location.htmlLocationText.append("htmlLocationText");
		location.htmlLocationButtons.append("htmlLocationButtons");
		location.htmlActionButtons.append("htmlActionButtons");
		location.htmlAlerts.append("htmlAlerts");
		location.htmlScripts.append("htmlScripts");

		String result = new StringBuilder(location.htmlLocationText)
				.append(location.htmlLocationButtons)
				.append(location.htmlActionButtons)
				.append(location.htmlAlerts)
				.append(location.htmlScripts).toString();

		String generated = location.generateHtml();

		assertEquals(result, generated);
	}

	@ParameterizedTest
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
	void addActionButtonSetTheSnare(boolean snareIsNotSet, boolean userHasNotSnare, boolean hasRope, boolean hasNotFowl)
	{
		Location location = new Forest();
		Location locationWithSnare = new River();
		location.user = user;
		user.setLocation(location);
		location.htmlActionButtons = spy(location.htmlActionButtons);

		location.setSnareIsSet(!snareIsNotSet);
		if (!snareIsNotSet)
			user.setLocationWithSnare(location);
		if (!userHasNotSnare)
			user.setLocationWithSnare(locationWithSnare);
		if (hasRope)
			user.getTools().add(new Rope());
		if (!hasNotFowl)
			user.getFoods().add(new Fowl());

		String result = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_SET_A_SNARE, BUTTON_SET_A_SNARE)).toString();

		location.addActionButtonSetTheSnare();

		if (snareIsNotSet && userHasNotSnare && hasRope && hasNotFowl )
		{
			assertAll(() -> {
				assertNull(user.getLocationWithSnare());
				assertEquals(result, location.htmlActionButtons.toString());
			});
		}
		else
		{
			verifyNoInteractions(location.htmlActionButtons);
		}
	}

	@ParameterizedTest
	@CsvSource({
			"false, false, false",
			"false, false, true ",
			"false, true , false",
			"false, true , true ",
			"true , false, false",
			"true , false, true ",
			"true , true , false",
			"true , true , true "
	})
	void addActionButtonCheckTheSnare(boolean snareIsSet, boolean userHasSnareThis, boolean userHasNotSnareThat)
	{
		Location location = new Forest();
		Location locationWithSnare = new River();
		location.user = user;
		location.htmlActionButtons = spy(location.htmlActionButtons);

		location.setSnareIsSet(snareIsSet);
		if (userHasSnareThis)
			user.setLocationWithSnare(location);
		if (!userHasNotSnareThat)
			user.setLocationWithSnare(locationWithSnare);

		String result = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_CHECK_THE_SNARE, BUTTON_CHECK_THE_SNARE)).toString();

		location.addActionButtonCheckTheSnare();

		assertAll(() -> {
			if (snareIsSet && userHasSnareThis && userHasNotSnareThat)
			{
				assertSame(location, user.getLocationWithSnare());
				assertEquals(result, location.htmlActionButtons.toString());
			}
			else
			{
				verifyNoInteractions(location.htmlActionButtons);
			}
		});
	}

	@Test
	void addActionButtonDrinkWaterFromRiver()
	{
		Location location = new River();
		user.setLocation(location);
		location.user = user;

		String result = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_DRINK_WATER_FROM_RIVER, BUTTON_DRINK_WATER_FROM_RIVER)).toString();

		location.addActionButtonDrinkWaterFromRiver();

		assertEquals(result, location.htmlActionButtons.toString());
	}

	@ParameterizedTest
	@CsvSource({
			"false, false",
			"false, true ",
			"true , false",
			"true , true "
	})
	void addActionButtonFryTheFowl(boolean hasLighter, boolean hasFowl)
	{
		Location location = new Beach();
		location.user = user;
		user.setLocation(location);

		if (hasLighter)
			user.getTools().add(new Lighter());
		if (hasFowl)
			user.getFoods().add(new Fowl());
		location.htmlActionButtons = spy(location.htmlActionButtons);

		String result = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_FRY_THE_FOWL, BUTTON_FRY_THE_FOWL)).toString();

		location.addActionButtonFryTheFowl();

		if (hasLighter && hasFowl)
			assertEquals(result, location.htmlActionButtons.toString());
		else
			verifyNoInteractions(location.htmlActionButtons);
	}

	@ParameterizedTest
	@CsvSource({
			"false, false, false",
			"false, false, true ",
			"false, true , false",
			"false, true , true ",
			"true , false, false",
			"true , false, true ",
			"true , true , false",
			"true , true , true "
	})
	void addActionButtonTurnOnTheBeacon(boolean hasBeacon, boolean hasCarBattery, boolean beaconIsOff)
	{
		Location location = new Mountain();
		location.user = user;
		user.setLocation(location);
		location.htmlActionButtons = spy(location.htmlActionButtons);

		if (hasBeacon)
			user.getTools().add(new Beacon());
		if (hasCarBattery)
			user.getTools().add(new CarBattery());
		user.setTheBeaconIsOn(!beaconIsOff);

		String result = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_TURN_ON_THE_BEACON, BUTTON_TURN_ON_THE_BEACON)).toString();

		location.addActionButtonTurnOnTheBeacon();

		if (hasBeacon && hasCarBattery && beaconIsOff)
			assertEquals(result, location.htmlActionButtons.toString());
		else
			verifyNoInteractions(location.htmlActionButtons);
	}

	@ParameterizedTest
	@CsvSource({
			"false, false",
			"false, true ",
			"true , false",
			"true , true "
	})
	void addActionButtonFishing(boolean hasSpear, boolean hasNotFish)
	{
		Location location = new River();
		location.user = user;
		user.setLocation(location);
		location.htmlActionButtons = spy(location.htmlActionButtons);

		if (hasSpear)
			user.getArms().add(new Spear());
		if (!hasNotFish)
			user.getFoods().add(new Fish());

		String result = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_FISHING, BUTTON_FISHING)).toString();

		location.addActionButtonFishing();

		if (hasSpear && hasNotFish)
			assertEquals(result, location.htmlActionButtons.toString());
		else
			verifyNoInteractions(location.htmlActionButtons);
	}

	@ParameterizedTest
	@CsvSource({
			"false, false",
			"false, true ",
			"true , false",
			"true , true "
	})
	void addActionButtonFryTheFish(boolean hasLighter, boolean hasFish)
	{
		Location location = new Beach();
		location.user = user;
		user.setLocation(location);

		if (hasLighter)
			user.getTools().add(new Lighter());
		if (hasFish)
			user.getFoods().add(new Fish());
		location.htmlActionButtons = spy(location.htmlActionButtons);

		String result = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_FRY_THE_FISH, BUTTON_FRY_THE_FISH)).toString();

		location.addActionButtonFryTheFish();

		if (hasLighter && hasFish)
			assertEquals(result, location.htmlActionButtons.toString());
		else
			verifyNoInteractions(location.htmlActionButtons);
	}

	@ParameterizedTest
	@CsvSource({
			"false, false",
			"false, true ",
			"true , false",
			"true , true "
	})
	void addActionButtonLightAFire(boolean hasLighter, boolean beaconIsOn)
	{
		Location location = new Beach();
		location.user = user;
		user.setLocation(location);
		location.htmlActionButtons = spy(location.htmlActionButtons);

		if (hasLighter)
			user.getTools().add(new Lighter());
		user.setTheBeaconIsOn(beaconIsOn);

		String result = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_LIGHT_A_FIRE, BUTTON_LIGHT_A_FIRE)).toString();

		location.addActionButtonLightAFire();

		if (hasLighter && beaconIsOn)
			assertEquals(result, location.htmlActionButtons.toString());
		else
			verifyNoInteractions(location.htmlActionButtons);
	}

	@Test
	void getProperties()
	{
		Location location = new Beach();
		assertSame(location.properties, location.getProperties());
	}

	@Test
	void getHtmlAlerts()
	{
		Location location = new Beach();
		assertSame(location.htmlAlerts, location.getHtmlAlerts());
	}

	@Test
	void getHtmlActionButtons()
	{
		Location location = new Beach();
		assertSame(location.htmlActionButtons, location.getHtmlActionButtons());
	}

	@Test
	void setSnareIsSet()
	{
		Location location = new Beach();
		assertAll(() -> {
			assertFalse(location.theSnareIsSet);
			location.setSnareIsSet(true);
			assertTrue(location.theSnareIsSet);
			location.setSnareIsSet(false);
			assertFalse(location.theSnareIsSet);
		});
	}

	@Test
	void isSnareIsSet()
	{
		Location location = new Beach();
		assertAll(() -> {
			assertFalse(location.isSnareIsSet());
			location.setSnareIsSet(true);
			assertTrue(location.isSnareIsSet());
			location.setSnareIsSet(false);
			assertFalse(location.isSnareIsSet());
		});
	}

	@Test
	void setRandom()
	{
		Location location = new Beach();
		location.setRandom(random);
		assertSame(random, location.random);
	}

	@Test
	void testEquals()
	{
		Location location1 = new River();
		Location location2 = new Forest();
		Location location3 = new River();
		Location location4 = new River();
		Location location5 = null;
		location3.setSnareIsSet(true);
		assertAll(() -> {
			assertTrue(location1.equals(location1));
			assertFalse(location1.equals(location5));
			assertFalse(location1.equals(location2));
			assertFalse(location1.equals(location3));
			assertTrue(location1.equals(location4));
		});
	}
}