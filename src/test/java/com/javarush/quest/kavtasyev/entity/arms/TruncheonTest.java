package com.javarush.quest.kavtasyev.entity.arms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TruncheonTest
{

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5})
	@DisplayName("Тестирование метода getNames() класса Truncheon")
	void returnsTheMassiveOfTheTruncheonNames(int i)
	{
		Truncheon truncheon = new Truncheon();
		String[] names = {"дубинка", "дубинку", "дубинке", "дубинку", "дубинкой", "дубинке"};
		assertEquals(names[i], truncheon.getNames()[i]);
	}
}