package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Settlement;
import com.javarush.quest.kavtasyev.entity.tool.CarBattery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class StealTheCarBatteryTest
{

	@Mock
	ThreadLocalRandom random;

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@ValueSource(doubles = {0, 0.74, 0.75, 0.99})
	@DisplayName("Тестирование метода executeAction(User user) класса StealTheCarBattery")
	void stealTheCarBatteryAndReturnHTMLText(double randomValue)
	{
		User user = new User();
		Settlement settlement = new Settlement();
		LocationProperties locationProperties = settlement.getProperties();
		user.setLocation(settlement);

		Mockito.doReturn(randomValue).when(random).nextDouble();
		Action action = new StealTheCarBattery();
		action.setRandom(random);

		String res;

		if (randomValue < locationProperties.stealCarBatteryProbability())
		{
			res = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Тебе удалось украсть аккумулятор со старого пикапа. Теперь ты сможешь включить маяк. Но лучше его включать в подходящем месте</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div>";
			assertAll(() -> {
				assertFalse(user.isHasTheTool(CarBattery.class));
				assertEquals(res, action.executeAction(user));
				assertTrue(user.isHasTheTool(CarBattery.class));
			});
		}
		else
		{
			res = String.format("<script>gameOverMessage(\"%s\");</script>", "Тебя захватили в плен местные бандиты.");
			assertAll(() -> {
				assertFalse(user.isHasTheTool(CarBattery.class));
				assertEquals(res, action.executeAction(user));
				assertFalse(user.isHasTheTool(CarBattery.class));
			});
		}
	}
}