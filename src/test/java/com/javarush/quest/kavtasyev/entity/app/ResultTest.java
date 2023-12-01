package com.javarush.quest.kavtasyev.entity.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ResultTest
{
	@ParameterizedTest
	@ValueSource(strings = {"<div id=\"winConfirmation\" class=\"w3-modal\">", "<div class=\"w3-panel w3-display-container w3-leftbar w3-pale-red w3-border-red w3-animate-opacity\">"})
	@DisplayName("Тестирование метода getHtml() класса ResultTest")
	void getHtml(String html)
	{
		Result result = new Result(html, new Exception());
		assertEquals(html, result.getHtml());
	}

	@ParameterizedTest
	@MethodSource("exceptionsProvidedFactory")
	@DisplayName("Тестирование метода getException() класса ResultTest")
	void getException(Exception e)
	{
		Result result = new Result(e);
		assertSame(e, result.getException());
	}

	static Stream<Exception> exceptionsProvidedFactory()
	{
		return Stream.of(new NullPointerException(), new IOException(), new ClassNotFoundException(), new NoSuchMethodException(), new InstantiationException(), new IllegalAccessException());
	}
}