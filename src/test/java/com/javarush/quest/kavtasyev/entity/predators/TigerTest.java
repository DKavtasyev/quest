package com.javarush.quest.kavtasyev.entity.predators;

import com.javarush.quest.kavtasyev.abstraction.ArmProperties;
import com.javarush.quest.kavtasyev.abstraction.PredatorProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.FlareGun;
import com.javarush.quest.kavtasyev.entity.arms.Machete;
import com.javarush.quest.kavtasyev.entity.arms.Spear;
import com.javarush.quest.kavtasyev.entity.arms.Truncheon;
import com.javarush.quest.kavtasyev.entity.locations.Forest;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class TigerTest
{
	@Spy
	User user;

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
	void attack(boolean hasFlareGun,	boolean hasMachete,
				boolean hasSpear,		boolean hasTruncheon)
	{
		Tiger tiger = new Tiger();
		Location location = new Forest();
		PredatorProperties properties = tiger.getProperties();

		FlareGun flareGun = new FlareGun(2);
		Machete machete = new Machete();
		Spear spear = new Spear();
		Truncheon truncheon = new Truncheon();

		if (hasFlareGun)
			user.getArms().add(flareGun);
		if (hasMachete)
			user.getArms().add(machete);
		if (hasSpear)
			user.getArms().add(spear);
		if (hasTruncheon)
			user.getArms().add(truncheon);

		String messageWithMachete = String.format(YOU_HAVE_FOUGHT_OFF_A_HEAVY_PREDATOR, machete.getNames()[4]);
		String messageWithSpear = String.format(YOU_HAVE_FOUGHT_OFF_A_HEAVY_PREDATOR, spear.getNames()[4]);

		tiger.attack(user, location);

		String result = location.getHtmlAlerts().toString();

		assertAll(() -> {
			if (hasFlareGun)
			{
				Mockito.verify(user, Mockito.times(1)).shoot(flareGun);
				ArmProperties armProperties = flareGun.getArmProperties();
				int damage = Math.max(0, properties.damage() - armProperties.power());
				if (damage > 20)
					assertTrue(location.getHtmlActionButtons().isEmpty());
				String ref = formAlert(YOU_HAVE_FLUSHED_OFF_A_HEAVY_PREDATOR);
				assertEquals(ref, result);
				assertEquals(100 - damage, user.getHealth());
			}
			else if (hasMachete)
			{
				ArmProperties armProperties = machete.getArmProperties();
				int damage = Math.max(0, properties.damage() - armProperties.power());
				if (damage > 20)
					assertTrue(location.getHtmlActionButtons().isEmpty());
				String ref = formAlert(messageWithMachete);
				assertEquals(ref, result);
				assertEquals(100 - damage, user.getHealth());
			}
			else if (hasSpear)
			{
				ArmProperties armProperties = spear.getArmProperties();
				int damage = Math.max(0, properties.damage() - armProperties.power());
				if (damage > 20)
					assertTrue(location.getHtmlActionButtons().isEmpty());
				String ref = formAlert(messageWithSpear);
				assertEquals(ref, result);
				assertEquals(100 - damage, user.getHealth());
			}
			else
			{
				String ref = formAlert(YOU_HAVE_BEEN_ATTACKED_BY_A_HEAVY_PREDATOR);
				assertEquals(ref, result);
				assertEquals(100 - properties.damage(), user.getHealth());
				assertTrue(location.getHtmlActionButtons().isEmpty());
			}
		});
	}

	@SuppressWarnings("all")
	private static String formAlert(String message)
	{
		return new StringBuilder().append(ALARM_OPEN_DIV_TAG)
				.append(message)
				.append(ALARM_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();
	}
}