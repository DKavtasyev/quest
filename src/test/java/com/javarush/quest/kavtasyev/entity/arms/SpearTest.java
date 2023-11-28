package com.javarush.quest.kavtasyev.entity.arms;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpearTest
{

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5})
	void getNames(int i)
	{
		Spear spear = new Spear();
		String[] names = {"копьё", "копья", "копью", "копьё", "копьём", "копье"};
		assertEquals(names[i], spear.getNames()[i]);
	}
}