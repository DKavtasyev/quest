package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.tool.Beacon;
import com.javarush.quest.kavtasyev.entity.tool.CarBattery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnOnTheBeaconTest
{

	@Test
	void executeAction()
	{
		User user = new User();
		user.getTools().add(new Beacon());
		user.getTools().add(new CarBattery());

		Action action = new TurnOnTheBeacon();

		String res;

		res = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Ты включил маяк.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div>";
		assertAll(() -> {
			assertFalse(user.isTheBeaconIsOn());
			assertTrue(user.isHasTheTool(Beacon.class));
			assertTrue(user.isHasTheTool(CarBattery.class));
			assertEquals(res, action.executeAction(user));
			assertFalse(user.isHasTheTool(Beacon.class));
			assertFalse(user.isHasTheTool(CarBattery.class));
		});
	}
}