package com.javarush.quest.kavtasyev.entity.tool;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.Forest;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class RopeTest
{

	@Test
	@SuppressWarnings("all")
	@DisplayName("Тестирование метода findRope(User user, Location location) класса Rope")
	void findRopeAndAddHTMLText()
	{
		Rope rope = new Rope();
		User user = new User();
		Location location = new Forest();

		String alerts = new StringBuilder().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_ROPE)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();

		assertAll(() -> {
			assertFalse(user.isHasTheTool(Rope.class));

			rope.findRope(user, location);

			assertTrue(user.isHasTheTool(Rope.class));
			assertEquals(alerts, location.getHtmlAlerts().toString());
		});
	}
}