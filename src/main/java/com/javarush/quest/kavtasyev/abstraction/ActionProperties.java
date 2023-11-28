package com.javarush.quest.kavtasyev.abstraction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface ActionProperties
{
	int healthRestore() default 0;
	int healthDamage() default 0;
}
