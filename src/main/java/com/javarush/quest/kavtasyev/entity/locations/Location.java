package com.javarush.quest.kavtasyev.entity.locations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.javarush.quest.kavtasyev.abstraction.LocationProperties;
import com.javarush.quest.kavtasyev.entity.app.User;
import com.javarush.quest.kavtasyev.entity.arms.FlareGun;
import com.javarush.quest.kavtasyev.entity.arms.Spear;
import com.javarush.quest.kavtasyev.entity.food.Fish;
import com.javarush.quest.kavtasyev.entity.food.Fowl;
import com.javarush.quest.kavtasyev.entity.tool.*;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.*;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = StartBeach.class, name = "StartBeach"),
		@JsonSubTypes.Type(value = Beach.class, name = "Beach"),
		@JsonSubTypes.Type(value = Forest.class, name = "Forest"),
		@JsonSubTypes.Type(value = River.class, name = "River"),
		@JsonSubTypes.Type(value = Jungle.class, name = "Jungle"),
		@JsonSubTypes.Type(value = FarBeach.class, name = "FarBeach"),
		@JsonSubTypes.Type(value = Plain.class, name = "Plain"),
		@JsonSubTypes.Type(value = Cave.class, name = "Cave"),
		@JsonSubTypes.Type(value = Mountain.class, name = "Mountain"),
		@JsonSubTypes.Type(value = Settlement.class, name = "Settlement"),
		@JsonSubTypes.Type(value = LocationProperties.class, name = "LocationProperties")
})
public abstract class Location
{
	@JsonIgnore
	protected User user;

	@JsonIgnore
	protected ThreadLocalRandom random;
	@JsonIgnore
	protected final LocationProperties properties = this.getClass().getAnnotation(LocationProperties.class);

	protected StringBuilder htmlLocationText = new StringBuilder();
	protected StringBuilder htmlLocationButtons = new StringBuilder();
	protected StringBuilder htmlActionButtons = new StringBuilder();
	protected StringBuilder htmlAlerts = new StringBuilder();
	protected StringBuilder htmlScripts = new StringBuilder();

	protected boolean theSnareIsSet = false;

	public abstract String executeEvents(User user);
	protected abstract void formHtml();
	protected abstract void addActionButtons();

	protected void getLost(Double probability)
	{
		if (random.nextDouble() < probability && !user.isGotLost())
		{
			if (user.isHasTheTool(Compass.class))
			{
				htmlAlerts.append(ALARM_OPEN_DIV_TAG)
						.append(YOU_GOT_LOST_BUT_COMPASS_BROUGHT_YOU_OUT)
						.append(ALARM_CLOSE_BUTTON)
						.append(CLOSE_DIV_TAG);
			}
			else
			{
				user.setGotLost(true);
				htmlLocationButtons.delete(0, htmlLocationButtons.length());
				htmlLocationButtons.append(LOCATION_AND_ACTION_BUTTON_BAR_OPEN_DIV_TAG)
						.append(String.format(LOCATION_BUTTON, LOCATION_PARAMETER_LOOK_FOR_A_WAY_OUT, BUTTON_LOOK_FOR_A_WAY_OUT))
						.append(CLOSE_DIV_TAG);
				htmlAlerts.append(ALARM_OPEN_DIV_TAG)
						.append(YOU_GOT_LOST)
						.append(ALARM_CLOSE_BUTTON)
						.append(CLOSE_DIV_TAG);
			}
		}
	}

	protected void findOutThePlane(double probability)
	{
		if (random.nextDouble() < probability && user.isTheBeaconIsOn() && !user.isThePlaneIsFoundOut())
		{
			user.setThePlaneIsFoundOut(true);
			htmlAlerts.append(NOTIFICATION_OPEN_DIV_TAG)
					.append(YOU_FOUND_OUT_THE_PLANE)
					.append(NOTIFICATION_CLOSE_BUTTON)
					.append(CLOSE_DIV_TAG);

			if (user.isHasTheArm(FlareGun.class))
			{
				htmlActionButtons.append(String.format(ACTION_BUTTON, ACTION_PARAMETER_SHOOT_A_FLARE_GUN, BUTTON_SHOOT_A_FLARE_GUN));
			}
		}

	}

	public void clearHtmlTexts()
	{
		htmlLocationText.delete(0, htmlLocationText.length());
		htmlLocationButtons.delete(0, htmlLocationButtons.length());
		htmlActionButtons.delete(0, htmlActionButtons.length());
		htmlAlerts.delete(0, htmlAlerts.length());
		htmlScripts.delete(0, htmlScripts.length());
	}

	protected void setHealth()
	{
		htmlScripts.append(String.format(SET_HEALTH_SCRIPT, user.getHealth()));
	}

	public String generateHtml()
	{
		return htmlLocationText.append(htmlLocationButtons).append(htmlActionButtons).append(htmlAlerts).append(htmlScripts).toString();
	}

	protected void addActionButtonSetTheSnare()
	{
		if (!theSnareIsSet && user.isHasTheTool(Rope.class) && user.getLocationWithSnare() == null && !user.isHasTheFood(Fowl.class))
			htmlActionButtons.append(String.format(ACTION_BUTTON, ACTION_PARAMETER_SET_A_SNARE, BUTTON_SET_A_SNARE));
	}

	protected void addActionButtonCheckTheSnare()
	{
		if (theSnareIsSet && Objects.equals(user.getLocationWithSnare(), this))
		{
			htmlActionButtons.append(String.format(ACTION_BUTTON, ACTION_PARAMETER_CHECK_THE_SNARE, BUTTON_CHECK_THE_SNARE));
		}
	}

	protected void addActionButtonDrinkWaterFromRiver()
	{
		htmlActionButtons.append(String.format(ACTION_BUTTON, ACTION_PARAMETER_DRINK_WATER_FROM_RIVER, BUTTON_DRINK_WATER_FROM_RIVER));
	}

	protected void addActionButtonFryTheFowl()
	{
		if (user.isHasTheTool(Lighter.class) && user.isHasTheFood(Fowl.class))
			htmlActionButtons.append(String.format(ACTION_BUTTON, ACTION_PARAMETER_FRY_THE_FOWL, BUTTON_FRY_THE_FOWL));
	}

	protected void addActionButtonTurnOnTheBeacon()
	{
		if (user.isHasTheTool(Beacon.class) && user.isHasTheTool(CarBattery.class) && !user.isTheBeaconIsOn())
			htmlActionButtons.append(String.format(ACTION_BUTTON, ACTION_PARAMETER_TURN_ON_THE_BEACON, BUTTON_TURN_ON_THE_BEACON));
	}

	protected void addActionButtonFishing()
	{
		if (user.isHasTheArm(Spear.class) && !user.isHasTheFood(Fish.class))
			htmlActionButtons.append(String.format(ACTION_BUTTON, ACTION_PARAMETER_FISHING, BUTTON_FISHING));
	}

	protected void addActionButtonFryTheFish()
	{
		if (user.isHasTheTool(Lighter.class) && user.isHasTheFood(Fish.class))
			htmlActionButtons.append(String.format(ACTION_BUTTON, ACTION_PARAMETER_FRY_THE_FISH, BUTTON_FRY_THE_FISH));
	}

	protected void addActionButtonLightAFire()
	{
		if (user.isHasTheTool(Lighter.class) && user.isTheBeaconIsOn())
			htmlActionButtons.append(String.format(ACTION_BUTTON, ACTION_PARAMETER_LIGHT_A_FIRE, BUTTON_LIGHT_A_FIRE));
	}

	public LocationProperties getProperties()
	{
		return properties;
	}

	public StringBuilder getHtmlAlerts()
	{
		return htmlAlerts;
	}

	public StringBuilder getHtmlActionButtons()
	{
		return htmlActionButtons;
	}

	public void setSnareIsSet(boolean b)
	{
		theSnareIsSet = b;
	}

	public boolean isSnareIsSet()
	{
		return theSnareIsSet;
	}

	public void setRandom(ThreadLocalRandom random)
	{
		this.random = random;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;
		if(o == null)
			return false;
		if(o.getClass() != (this.getClass()))
			return false;
		Location other = (Location) o;

		return other.theSnareIsSet == this.theSnareIsSet;
	}
}
