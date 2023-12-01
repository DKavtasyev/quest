package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.Spear;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.locations.River;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class FishingTest
{

	@Mock
	ThreadLocalRandom random;

	@ParameterizedTest
	@ExtendWith(MockitoExtension.class)
	@ValueSource(doubles = {0, 0.69, 0.7, 0.71, 0.99})
	@DisplayName("Тестирование метода executeAction(User user) класса Fishing")
	void fishingAndReturnsHTMLText(double randomValue)
	{
		User user = new User();
		user.getArms().add(new Spear());
		River river = new River();
		LocationProperties locationProperties = river.getProperties();
		user.setLocation(river);

		Mockito.doReturn(randomValue).when(random).nextDouble();
		Action action = new Fishing();
		action.setRandom(random);

		String res;

		if (randomValue < locationProperties.catchFishProbability())
		{
			res = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Тебе удалось поймать рыбу. Теперь её нужно приготовить на костре.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div>";
			assertAll(() -> {
				assertFalse(user.isHasTheFood(Fish.class));
				assertTrue(user.isHasTheArm(Spear.class));
				assertSame(user.getLocation(), river);
				assertEquals(res, action.executeAction(user));
				assertTrue(user.isHasTheFood(Fish.class));
			});
		}
		else
		{
			res = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Не так то просто оказалось поразить рыбу копьём. Ты ничего не поймал, попробуй в другой раз.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div>";
			assertAll(() -> {
				assertFalse(user.isHasTheFood(Fish.class));
				assertTrue(user.isHasTheArm(Spear.class));
				assertSame(user.getLocation(), river);
				assertEquals(res, action.executeAction(user));
				assertFalse(user.isHasTheFood(Fish.class));
			});
		}
	}
}