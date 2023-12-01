package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Fowl;
import com.javarush.quest.kavtasyev.entity.locations.Forest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class CheckTheSnareTest
{
	@Mock
	ThreadLocalRandom random;

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@ValueSource(doubles = {0, 0.49, 0.5, 0.51, 0.99})
	@DisplayName("Тестирование метода executeAction(User user) класса CheckTheSnare")
	void checksTheSnareAndReturnsHTMLTextAfterThat(double randomValue)
	{
		User user = new User();
		Forest forest = new Forest();
		LocationProperties locationProperties = forest.getProperties();
		user.setLocation(forest);
		user.setLocationWithSnare(forest);

		Mockito.doReturn(randomValue).when(random).nextDouble();
		Action action = new CheckTheSnare();
		action.setRandom(random);

		String res;

		if (randomValue < locationProperties.catchFowlProbability())
		{
			res = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>В ловушке оказалась довольно крупная птица. Хорошо бы было её зажарить.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div><script>setHealth(\"100\");</script>";
			assertAll(() -> {
				assertNotNull(user.getLocationWithSnare());
				assertFalse(user.isHasTheFood(Fowl.class));
				assertEquals(res, action.executeAction(user));
				assertTrue(user.isHasTheFood(Fowl.class));
				assertNull(user.getLocationWithSnare());
			});
		}
		else
		{
			res = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Ловушка оказалась пустой.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div><script>setHealth(\"100\");</script>";
			assertAll(() -> {
				assertNotNull(user.getLocationWithSnare());
				assertFalse(user.isHasTheFood(Fowl.class));
				assertEquals(res, action.executeAction(user));
				assertNull(user.getLocationWithSnare());
				assertFalse(user.isHasTheFood(Fowl.class));
			});
		}
	}
}