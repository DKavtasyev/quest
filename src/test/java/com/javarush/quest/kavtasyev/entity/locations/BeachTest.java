package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.FlareGun;
import com.javarush.quest.kavtasyev.entity.tool.Beacon;
import com.javarush.quest.kavtasyev.entity.tool.Compass;
import com.javarush.quest.kavtasyev.entity.tool.Lighter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BeachTest
{
	@Spy
	Beach spyBeach;


	@InjectMocks
	Beach beach;

	@Mock
	ThreadLocalRandom random;
	@Spy
	Compass compass;
	@Spy
	Lighter lighter;
	@Spy
	Beacon beacon;
	@Spy
	FlareGun flareGun = new FlareGun(5);

	@Test
	@ExtendWith(MockitoExtension.class)
	void executeEvents()
	{
		User user = new User();
		spyBeach.setRandom(random);

		StringBuilder spyHtmlLocationText = Mockito.spy(spyBeach.htmlLocationText);
		StringBuilder spyHtmlLocationButtons = Mockito.spy(spyBeach.htmlLocationButtons);
		StringBuilder spyHtmlActionButtons = Mockito.spy(spyBeach.htmlActionButtons);
		StringBuilder spyHtmlAlerts = Mockito.spy(spyBeach.htmlAlerts);
		spyBeach.htmlLocationText = spyHtmlLocationText;
		spyBeach.htmlLocationButtons = spyHtmlLocationButtons;
		spyBeach.htmlActionButtons = spyHtmlActionButtons;
		spyBeach.htmlAlerts = spyHtmlAlerts;

		spyBeach.executeEvents(user);

		assertAll(() -> {
			assertSame(spyBeach, user.getLocation());
			Mockito.verify(spyBeach).formHtml();
			Mockito.verify(spyBeach).setHealth();
			Mockito.verify(spyBeach).generateHtml();
			Mockito.verify(spyHtmlLocationText).append(TEXT_BEACH);
			Mockito.verify(spyHtmlLocationButtons).append(BEACH_LOCATION_BUTTONS);
			Mockito.verify(spyHtmlActionButtons).append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG);
			Mockito.verify(spyBeach).addActionButtonFryTheFowl();
			Mockito.verify(spyBeach).addActionButtonFryTheFish();
			Mockito.verify(spyBeach).addActionButtonLightAFire();
			Mockito.verify(spyHtmlActionButtons).append(CLOSE_DIV_TAG);
			Mockito.verify(spyHtmlAlerts).append(ACTION_NOTIFICATIONS_OPEN_DIV_TAG);
			Mockito.verify(spyHtmlAlerts, atLeast(1)).append(CLOSE_DIV_TAG);
		});
	}

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@CsvSource({
			"0.0, false, false, false, false, false, false, false",
			"0.1, false, false, false, false, false, false, false",
			"0.2, false, false, false, false, false, false, false",
			"0.3, false, false, false, false, false, false, false",
			"0.4, false, false, false, false, false, false, false",
			"0.5, false, false, false, false, false, false, false",
			"0.6, false, false, false, false, false, false, false",
			"0.7, false, false, false, false, false, false, false",
			"0.8, false, false, false, false, false, false, false",
			"0.9, false, false, false, false, false, false, false",

			"0.0, true,  true,  true,  true,  false, false, false",
			"0.0, false, false, false, false, true,  false, false",
			"0.0, false, false, false, false, false, true,  true ",
			"0.0, false, false, false, false, true,  true,  false",
			"1.0, false, false, false, false, false, false, false"
	})
	void executeEvents(
			double randomValue,

			boolean hasCompass,			boolean hasLighter,
			boolean hasBeacon,			boolean hasFlareGun,

			boolean wasNotOnMountain,	boolean foundBeacon,
			boolean foundFlareGun)
	{
		User user = new User();
		if (hasCompass)
			user.getTools().add(new Compass());
		if (hasLighter)
			user.getTools().add(new Lighter());
		if (hasBeacon)
			user.getTools().add(new Beacon());
		if (hasFlareGun)
			user.getArms().add(new FlareGun(5));

		user.setWasOnTheMountain(!wasNotOnMountain);
		user.setFoundBeacon(foundBeacon);
		user.setFoundFlareGun(foundFlareGun);

		Mockito.doReturn(randomValue).when(random).nextDouble();
		LocationProperties locationProperties = beach.getProperties();

		beach.executeEvents(user);

		assertAll(() -> {

			if (randomValue < locationProperties.findCompassProbability())
			{
				if (!hasCompass)
				{
					Mockito.verify(compass, only()).findCompass(user, beach);
				}
				else
				{
					Mockito.verify(compass, never()).findCompass(user, beach);
				}
			}

			if (randomValue < locationProperties.findLighterProbability())
			{
				if (!hasLighter)
				{
					Mockito.verify(lighter, only()).findLighter(user, beach);
				}
				else
				{
					Mockito.verify(lighter, never()).findLighter(user, beach);
				}
			}

			if (randomValue < locationProperties.findBeaconProbability())
			{
				if (!hasBeacon && !foundBeacon && !wasNotOnMountain)
				{
					Mockito.verify(beacon, only()).findBeacon(user, beach);
				}
				else
				{
					Mockito.verify(beacon, never()).findBeacon(user, beach);
				}
			}

			if (randomValue < locationProperties.findFlareGunProbability())
			{
				if (!hasFlareGun && !foundFlareGun)
				{
					Mockito.verify(flareGun, only()).findFlareGun(user, beach);
				}
				else
				{
					Mockito.verify(flareGun, never()).findFlareGun(user, beach);
				}
			}

			if (randomValue >= 1.0)
			{
				Mockito.verify(compass, never()).findCompass(user, beach);
				assertFalse(user.isHasTheTool(Compass.class));

				Mockito.verify(lighter, never()).findLighter(user, beach);
				assertFalse(user.isHasTheTool(Lighter.class));

				Mockito.verify(beacon, never()).findBeacon(user, beach);
				assertFalse(user.isHasTheTool(Beacon.class));

				Mockito.verify(flareGun, never()).findFlareGun(user, beach);
				assertFalse(user.isHasTheArm(FlareGun.class));
			}
		});
	}
}


//	@Spy
//	Beach spyBeach = new Beach();
//
//	@Mock
//	ThreadLocalRandom random;
//
//	@Test
//	@ExtendWith(MockitoExtension.class)
//	void executeEvents()
//	{
//		User user = new User();
//		Mockito.doReturn("generateHtml()").when(spyBeach).generateHtml();
//		Mockito.doReturn(0.0).when(random).nextDouble();
//		spyBeach.setRandom(random);
//		assertAll(() -> {
//			assertEquals("generateHtml()", spyBeach.executeEvents(user));
//			assertSame(spyBeach, user.getLocation());
//			Mockito.verify(spyBeach).formHtml();
//			Mockito.verify(spyBeach).setHealth();
//		});
//	}
//
//	@Mock
//	StringBuilder mockHtmlLocationText;
//
//	@Mock
//	StringBuilder mockHtmlLocationButtons;
//
//	@Test
//	@ExtendWith(MockitoExtension.class)
//	void formHtml() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
//	{
//		spyBeach.user = new User();
//		Mockito.doReturn(0.0).when(random).nextDouble();
//		spyBeach.setRandom(random);
//		LocationProperties properties = spyBeach.getProperties();
//		spyBeach.htmlLocationText = mockHtmlLocationText;
//		spyBeach.htmlLocationButtons = mockHtmlLocationButtons;
//
//		Method findCompass = Beach.class.getDeclaredMethod("findCompass", double.class);
//		findCompass.setAccessible(true);
//
//		spyBeach.formHtml();
//
//		assertAll(() -> {
//			Mockito.verify(mockHtmlLocationText).append(LocationHtml.TEXT_BEACH);
//			Mockito.verify(mockHtmlLocationButtons).append(LocationHtml.BEACH_LOCATION_BUTTONS);
//
////			Mockito.verify(findCompass).invoke(spyBeach, properties.findCompassProbability());
//		});
//		findCompass.setAccessible(false);
//	}
//
//	@Test
//	void addActionButtons()
//	{
//
//	}
//
//	@Test
//	void doAction()
//	{
//	}