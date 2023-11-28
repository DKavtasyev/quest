package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.food.Fowl;
import com.javarush.quest.kavtasyev.entity.locations.Plain;
import com.javarush.quest.kavtasyev.entity.tool.Rope;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetASnareTest
{

	@Test
	void executeAction()
	{
		User user = new User();
		user.getTools().add(new Rope());
		Plain plain = new Plain();
		user.setLocation(plain);

		Action action = new SetASnare();

		String res = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Ловушка была установлена.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div>";

		assertAll(() -> {
			assertFalse(plain.isSnareIsSet());
			assertTrue(user.isHasTheTool(Rope.class));
			assertNull(user.getLocationWithSnare());
			assertFalse(user.isHasTheFood(Fowl.class));
			assertEquals(res, action.executeAction(user));
			assertTrue(plain.isSnareIsSet());
			assertFalse(user.isHasTheTool(Rope.class));
			assertSame(plain, user.getLocationWithSnare());
		});
	}
}