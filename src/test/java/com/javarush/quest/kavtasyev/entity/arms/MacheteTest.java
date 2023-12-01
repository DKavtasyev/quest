package com.javarush.quest.kavtasyev.entity.arms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MacheteTest
{
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5})
	@DisplayName("Тестирование метода getNames() класса Machete")
	void returnsTheMassiveOfMacheteNames(int i)
	{
		Machete machete = new Machete();
		String name = "мачете";
		assertEquals(name, machete.getNames()[i]);
	}
}