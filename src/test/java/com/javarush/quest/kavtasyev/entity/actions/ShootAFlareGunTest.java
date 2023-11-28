package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.FlareGun;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShootAFlareGunTest
{

	@Test
	void executeAction()
	{
		User user = new User();
		user.getArms().add(new FlareGun(2));

		Action action = new ShootAFlareGun();

		String res = "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-blue w3-border-blue w3-animate-opacity\"><p>Ты выстрелил сигнальной ракетой. Это резко увеличит шансы, что тебя заметят и спасут.</p><span onclick=\"this.parentElement.style.display='none'\" class=\"w3-button w3-display-topright w3-hover-blue\">×</span></div>";

		assertAll(() -> {
			assertTrue(user.isHasTheArm(FlareGun.class));
			assertFalse(user.isHasShootAFlareGun());
			assertEquals(res, action.executeAction(user));
			assertTrue(user.isHasShootAFlareGun());
		});
	}
}