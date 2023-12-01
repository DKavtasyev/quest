package com.javarush.quest.kavtasyev.entity.arms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlareGunTest
{
	FlareGun flareGun;

	@BeforeEach
	void createFlareGun()
	{
		flareGun = new FlareGun(5);
	}

	@Test
	@DisplayName("Тестирование метода takeAShot класса FlareGun")
	void whenTakeAShotThenDecrementNumberFoFlares()
	{
		int flares = flareGun.takeAShot();
		assertAll(() -> {
			assertEquals(4, flares);
			assertEquals(flares, flareGun.getFlares());
		});
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5})
	@DisplayName("Тестирование метода getNames() класса FlareGun")
	void getNames(int i)
	{
		String[] names = {"сигнальная ракетница", "сигнальную ракетницу", "сигнальной ракетнице", "сигнальную ракетницу", "сигнальной ракетницей", "сигнальной ракетнице"};
		assertEquals(names[i], flareGun.getNames()[i]);
	}

	@Test
	@DisplayName("Тестирование метода getFlares() класса FlareGun")
	void getFlares()
	{
		assertEquals(5, flareGun.getFlares());
	}

	@Test
	@DisplayName("Тестирование метода setFlares(int flares) класса FlareGun")
	void setFlares()
	{
		flareGun.setFlares(10);
		assertEquals(10, flareGun.getFlares());
	}
}