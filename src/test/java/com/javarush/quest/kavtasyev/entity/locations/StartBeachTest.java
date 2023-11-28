package com.javarush.quest.kavtasyev.entity.locations;

import com.javarush.quest.kavtasyev.entity.app.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class StartBeachTest
{
	@InjectMocks
	StartBeach startBeach;

	@Mock
	User user;

	@Test
	@ExtendWith(MockitoExtension.class)
	void executeEvents()
	{
		String text = "<p style=\"height: 150px\">Ты просыпаешься на песчаном лазурном берегу моря или океана. Над бескрайним океаном поднимается алое солнце. Перед тобой стоит нетронутый тропический лес, справа - неприступные скалы, слева - бесконечный песчаный берег. Придя в себя, понимаешь, что ты тут совершенно один. Последняя информация в базе данных твоей памяти - сильный шторм, шквальный ливень, затем стена воды и попытки удержаться на её поверхности. Тебе нужно решить, что делать и куда идти дальше чтобы выжить.</p><div class=\"w3-bar w3-padding-16\"><button class=\"w3-button w3-black w3-hover-blue\" style=\"width:200px; margin-right:8px; margin-bottom:8px\" onclick=\"changeLocation('Beach')\">Вдоль пляжа</button><button class=\"w3-button w3-black w3-hover-blue\" style=\"width:200px; margin-right:8px; margin-bottom:8px\" onclick=\"changeLocation('Forest')\">Лес</button></div>";
		assertEquals(text, startBeach.executeEvents(user));
	}
}