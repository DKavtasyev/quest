package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.River;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkWaterFromSpringTest
{

	@ParameterizedTest
	@ValueSource(ints = {1, 50, 90})
	@DisplayName("Тестирование метода executeAction(User user) класса DrinkWaterFromSpring")
	void drinksWaterFromSpringAndReturnsHTMLText(int startHealth)
	{
		User user = new User();
		user.setHealth(startHealth);
		River river = new River();
		user.setLocation(river);

		Action action = new DrinkWaterFromSpring();
		ActionProperties actionProperties = action.getActionProperties();

		int finalHealth = Math.min(startHealth + actionProperties.healthRestore(), 100);
		String res = String.format("<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Ты попил воды из источника. На вкус она очень хорошая, такую воду можно встретить только в природных условиях.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div><script>setHealth(\"%s\");</script>", finalHealth);

		assertAll(() -> {
			assertEquals(startHealth, user.getHealth());
			assertEquals(res, action.executeAction(user));
			assertEquals(finalHealth, user.getHealth());
		});
	}
}