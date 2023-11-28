package com.javarush.quest.kavtasyev.service;

import com.javarush.quest.kavtasyev.entity.app.CustomData;
import com.javarush.quest.kavtasyev.entity.app.Result;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.Spear;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.locations.Forest;
import com.javarush.quest.kavtasyev.entity.locations.River;
import com.javarush.quest.kavtasyev.entity.tool.Lighter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServiceFunctionTest
{
	@Mock
	CustomData customData;

	@Test
	@SuppressWarnings("all")
	void execute()
	{
		ServiceFunction serviceFunction = new ServiceFunction();
		User user = new User("name", "login", "password");
		user.getArms().add(new Spear());
		user.getTools().add(new Lighter());
		user.getFoods().add(new Fish());
		user.setLocation(new Forest());
		user.setLocationWithSnare(new River());
		String resultString = new StringBuilder().append("<ul>").append("<li>Login: ").append(user.getLogin()).append("</li>")
				.append("<li>Health: ").append(user.getHealth()).append("</li>")
				.append("<li>Was on the mountain: ").append(user.isWasOnTheMountain()).append("</li>")
				.append("<li>Found beacon: ").append(user.isFoundBeacon()).append("</li>")
				.append("<li>The beacon is on: ").append(user.isTheBeaconIsOn()).append("</li>")
				.append("<li>The plane is found out: ").append(user.isThePlaneIsFoundOut()).append("</li>")
				.append("<li>Is got lost: ").append(user.isGotLost()).append("</li>")
				.append("<li>Has shoot a flare gun: ").append(user.isHasShootAFlareGun()).append("</li>")
				.append("<li>Current location: ").append(user.getLocation().getClass().getSimpleName()).append("</li>")
				.append("<li>Location with snare: ").append(user.getLocationWithSnare().getClass().getSimpleName()).append("</li>")
				.append("<li>Tools:<ul>").append("<li>").append("Lighter").append("</li>").append("</ul></li>")
				.append("<li>Arms:<ul>").append("<li>").append("Spear").append("</li>").append("</ul></li>")
				.append("<li>Foods:<ul>").append("<li>").append("Fish").append("</li>").append("</ul></li>")
				.append("</ul>").toString();

		Result result = serviceFunction.execute(user, customData);

		assertEquals(resultString, result.getHtml());
	}
}