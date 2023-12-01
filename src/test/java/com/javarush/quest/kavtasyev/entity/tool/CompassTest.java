package com.javarush.quest.kavtasyev.entity.tool;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Beach;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class CompassTest
{

	@Test
	@SuppressWarnings("all")
	@DisplayName("Тестирование метода findCompass(User user, Location location) класса Compass")
	void findCompassAndAddHTMLText()
	{
		Compass compass = new Compass();
		User user = new User();
		Location location = new Beach();

		String alerts = new StringBuilder().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_COMPASS)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();

		assertAll(() -> {
			assertFalse(user.isHasTheTool(Compass.class));

			compass.findCompass(user, location);

			assertTrue(user.isHasTheTool(Compass.class));
			assertEquals(alerts, location.getHtmlAlerts().toString());
		});
	}
}