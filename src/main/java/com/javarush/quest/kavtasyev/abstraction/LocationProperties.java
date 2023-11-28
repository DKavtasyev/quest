package com.javarush.quest.kavtasyev.abstraction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface LocationProperties
{
	double findCompassProbability() default 0.0;
	double findLighterProbability() default 0.0;
	double findBeaconProbability() default 0.0;
	double findRopeProbability() default 0.0;
	double findCarBatteryProbability() default 0.0;
	double stealCarBatteryProbability() default 0.0;
	double findFlareGunProbability() default 0.0;
	double findTruncheonOrSpearProbability() default 0.0;
	double findMacheteProbability() default 0.0;
	double stealMacheteProbability() default 0.0;
	double findSpringProbability() default 0.0;
	double findBerriesProbability() default 0.0;
	double findFruitsProbability() default 0.0;
	double catchFowlProbability() default 0.0;
	double catchFishProbability() default 0.0;
	double predatorAttackProbability() default 0.0;
	double getLostProbability() default 0.0;
	double getInfectionProbability() default 0.0;
	double snakeBiteProbability() default 0.0;
	double beCapturedProbability() default 0.0;
	double findTheWayOutProbability() default 0.0;
	double findOutThePlaneProbability() default 0.0;
	double youHaveFoundAShipProbability() default 0.0;
}
