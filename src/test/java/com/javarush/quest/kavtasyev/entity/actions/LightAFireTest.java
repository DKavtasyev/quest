package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Beach;
import com.javarush.quest.kavtasyev.entity.tool.Lighter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class LightAFireTest
{
	@Mock
	ThreadLocalRandom random;

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@CsvSource({
			"0.2, false",
			"0.4, false",
			"0.6, false",
			"0.6, true",
			"0.8, true",
			"0.9, true"
	})
	void executeAction(double randomValue, boolean hasShootAFlareGun)
	{
		User user = new User();
		Beach beach = new Beach();
		user.setLocation(beach);
		user.getTools().add(new Lighter());
		user.setTheBeaconIsOn(true);
		user.setHasShootAFlareGun(hasShootAFlareGun);
		LocationProperties locationProperties = beach.getProperties();

		Mockito.doReturn(randomValue).when(random).nextDouble();
		Action action = new LightAFire();
		action.setRandom(random);

		String res;
		double defaultProbability = locationProperties.youHaveFoundAShipProbability();
		double probability = hasShootAFlareGun ? defaultProbability * 2 : defaultProbability;

		if (randomValue < probability)
		{
			res = String.format("<script>youWinMessage(\"%s\");</script><div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\">Ты зажёг костёр. Теперь остаётся надеяться, что его заметят.<span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div>", "Тебя обнаружили и вскоре тебя забрали с острова.");
			assertAll(() -> {
				assertTrue(user.isTheBeaconIsOn());
				assertTrue(user.isHasTheTool(Lighter.class));
				assertEquals(res, action.executeAction(user));
			});
		}
		else
		{
			res = String.format("<script>gameOverMessage(\"%s\");</script><div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\">Ты зажёг костёр. Теперь остаётся надеяться, что его заметят.<span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div>", "В результате поисково-спасательной операции тебя так и не нашли.");
			assertAll(() -> {
				assertTrue(user.isTheBeaconIsOn());
				assertTrue(user.isHasTheTool(Lighter.class));
				assertEquals(res, action.executeAction(user));
			});
		}
	}
}