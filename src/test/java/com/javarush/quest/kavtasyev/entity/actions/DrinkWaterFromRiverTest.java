package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.River;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkWaterFromRiverTest
{
	@Mock
	ThreadLocalRandom random;

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@CsvSource({
			"0, 40",
			"0.39, 10",
			"0.4, 60",
			"0,41, 60",
			"0.99, 97"
	})
	@DisplayName("Тестирование метода executeAction(User user) класса DrinkWaterFromRiver")
	void drinksWaterFromRiverAndReturnsHTMLText(double randomValue, int startHealth)
	{
		User user = new User();
		user.setHealth(startHealth);
		River river = new River();
		LocationProperties locationProperties = river.getProperties();
		user.setLocation(river);

		Mockito.doReturn(randomValue).when(random).nextDouble();
		Action action = new DrinkWaterFromRiver();
		ActionProperties actionProperties = action.getActionProperties();
		action.setRandom(random);

		String res;
		int finalHealth;

		if (randomValue < locationProperties.getInfectionProbability())
		{
			finalHealth = Math.max(startHealth - actionProperties.healthDamage(), 0);
			res = String.format("<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-red w3-border-red w3-animate-opacity\"><p>Ты подхватил какую то инфекцию. Тебя лихорадит и совсем нет сил.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-red\">×</span></div><script>setHealth(\"%s\");</script>", finalHealth);
			assertAll(() -> {
				assertEquals(startHealth, user.getHealth());
				assertEquals(res, action.executeAction(user));
				assertEquals(finalHealth, user.getHealth());
			});
		}
		else
		{
			finalHealth = Math.min(startHealth + actionProperties.healthRestore(), 100);
			res = String.format("<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Ты попил воды. На вкус она не очень.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div><script>setHealth(\"%s\");</script>", finalHealth);
			assertAll(() -> {
				assertEquals(startHealth, user.getHealth());
				assertEquals(res, action.executeAction(user));
				assertEquals(finalHealth, user.getHealth());
			});
		}
	}
}