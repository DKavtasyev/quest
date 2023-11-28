package com.javarush.quest.kavtasyev.entity.actions;

import com.javarush.quest.kavtasyev.abstraction.ActionProperties;
import com.javarush.quest.kavtasyev.entity.app.User;

import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.quest.kavtasyev.constants.LocationHtml.SET_HEALTH_SCRIPT;

public abstract class Action
{
	protected User user;

	protected ThreadLocalRandom random;
	protected final ActionProperties actionProperties = this.getClass().getAnnotation(ActionProperties.class);

	protected StringBuilder htmlActionsNotifications = new StringBuilder();
	public abstract String executeAction(User user);

	public void clearHtmlTexts()
	{
		htmlActionsNotifications.delete(0, htmlActionsNotifications.length());
	}

	protected void setHealth()
	{
		htmlActionsNotifications.append(String.format(SET_HEALTH_SCRIPT, user.getHealth()));
	}

	public void setRandom(ThreadLocalRandom random)
	{
		this.random = random;
	}

	public ActionProperties getActionProperties()
	{
		return actionProperties;
	}
}
