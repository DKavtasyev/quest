package com.javarush.quest.kavtasyev.entity.tool;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Beach;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class LighterTest
{

	@Test
	@SuppressWarnings("all")
	@DisplayName("Тестирование метода findLighter(User user, Location location) класса Lighter")
	void findLighterAndAddHTMLText()
	{
		Lighter lighter = new Lighter();
		User user = new User();
		Location location = new Beach();

		String alerts = new StringBuilder().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_LIGHTER)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();

		assertAll(() -> {
			assertFalse(user.isHasTheTool(Lighter.class));

			lighter.findLighter(user, location);

			assertTrue(user.isHasTheTool(Lighter.class));
			assertEquals(alerts, location.getHtmlAlerts().toString());
		});
	}
}