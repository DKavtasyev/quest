package com.javarush.quest.kavtasyev.entity.predators;

import com.javarush.quest.kavtasyev.abstraction.PredatorProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Jungle;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class SnakeTest
{

	@ParameterizedTest
	@ValueSource(ints = {20, 80, 100})
	@SuppressWarnings("all")
	void attack(int startHealth)
	{
		User user = new User();
		user.setHealth(startHealth);
		Snake snake = new Snake();
		Location location = new Jungle();
		PredatorProperties properties = snake.properties;
		location.getHtmlActionButtons().append("1234567890");

		String resultAlerts = new StringBuilder().append(ALARM_OPEN_DIV_TAG)
				.append(THE_SNAKE_BIT_YOU)
				.append(ALARM_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();

		int newHealth = Math.max(0, user.getHealth() - properties.damage());

		snake.attack(user, location);

		assertAll(() -> {
			assertTrue(location.getHtmlActionButtons().isEmpty());
			assertEquals(resultAlerts, location.getHtmlAlerts().toString());
			assertEquals(newHealth, user.getHealth());
		});
	}
}