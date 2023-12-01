package com.javarush.quest.kavtasyev.entity.tool;

import com.javarush.quest.kavtasyev.entity.locations.Settlement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;
import static org.junit.jupiter.api.Assertions.*;

class CarBatteryTest
{

	@Test
	@SuppressWarnings("all")
	@DisplayName("Тестирование метода findCarBattery(Settlement settlement) класса CarBattery")
	void findCarBatteryAndAddHTMLText()
	{
		CarBattery carBattery = new CarBattery();
		Settlement settlement = new Settlement();
		String actionButtons = new StringBuilder().append(String.format(ACTION_BUTTON, ACTION_PARAMETER_STEAL_THE_CAR_BATTERY, BUTTON_STEAL_THE_CAR_BATTERY)).toString();
		String alerts = new StringBuilder().append(NOTIFICATION_OPEN_DIV_TAG)
				.append(YOU_FOUND_THE_FIT_BATTERY)
				.append(NOTIFICATION_CLOSE_BUTTON)
				.append(CLOSE_DIV_TAG).toString();

		assertAll(() -> {
			assertTrue(settlement.getHtmlActionButtons().isEmpty());
			assertTrue(settlement.getHtmlAlerts().isEmpty());

			carBattery.findCarBattery(settlement);

			assertEquals(actionButtons, settlement.getHtmlActionButtons().toString());
			assertEquals(alerts, settlement.getHtmlAlerts().toString());
		});
	}
}