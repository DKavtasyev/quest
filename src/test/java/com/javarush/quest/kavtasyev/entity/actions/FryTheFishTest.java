package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.locations.Beach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FryTheFishTest
{

	@ParameterizedTest
	@ValueSource(ints = {50, 90})
	@DisplayName("Тестирование метода executeAction класса FryTheFish")
	void fryTheFishThenRecoveryHealthAndReturnHTMLText(int startHealth)
	{
		User user = new User();
		user.setHealth(startHealth);
		user.getFoods().add(new Fish());
		Beach beach = new Beach();
		user.setLocation(beach);

		Action action = new FryTheFish();
		ActionProperties actionProperties = action.getActionProperties();

		int finalHealth = Math.min(startHealth + actionProperties.healthRestore(), 100);
		String res = String.format("<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Ты плотно поел рыбой, пожаренной на костре. Это хорошо восстановило твои силы</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div><script>setHealth(\"%s\");</script>", finalHealth);

		assertAll(() -> {
			assertTrue(user.isHasTheFood(Fish.class));
			assertEquals(startHealth, user.getHealth());
			assertEquals(res, action.executeAction(user));
			assertFalse(user.isHasTheFood(Fish.class));
			assertEquals(finalHealth, user.getHealth());
		});
	}
}