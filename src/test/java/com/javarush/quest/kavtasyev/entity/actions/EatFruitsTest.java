package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Plain;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EatFruitsTest
{

	@ParameterizedTest
	@ValueSource(ints = {1, 70, 90})
	void executeAction(int startHealth)
	{
		User user = new User();
		user.setHealth(startHealth);
		Plain plain = new Plain();
		user.setLocation(plain);

		Action action = new EatFruits();
		ActionProperties actionProperties = action.getActionProperties();

		int finalHealth = Math.min(startHealth + actionProperties.healthRestore(), 100);
		String res = String.format("<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Ты поел разных фруктов. Это восстановило твои силы.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div><script>setHealth(\"%s\");</script>", finalHealth);

		assertAll(() -> {
			assertEquals(startHealth, user.getHealth());
			assertEquals(res, action.executeAction(user));
			assertEquals(finalHealth, user.getHealth());
		});
	}
}