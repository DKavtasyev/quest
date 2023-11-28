package com.javarush.quest.kavtasyev.entity.tool;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.locations.FarBeach;
import com.javarush.quest.kavtasyev.entity.locations.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class BeaconTest
{

	@Test
	@ExtendWith(MockitoExtension.class)
	@SuppressWarnings("all")
	void findBeacon()
	{
		Beacon beacon = new Beacon();
		Location location = new FarBeach();
		User user = new User();

		String alerts = new StringBuilder().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(FIND_BEACON)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();

		assertAll(() -> {
			assertFalse(user.isFoundBeacon());
			assertFalse(user.isHasTheTool(Beacon.class));

			beacon.findBeacon(user, location);

			assertTrue(user.isFoundBeacon());
			assertTrue(user.isHasTheTool(Beacon.class));
			assertEquals(alerts, location.getHtmlAlerts().toString());
		});
	}
}