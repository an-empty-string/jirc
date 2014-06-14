package me.fwilson.ircclient;

import me.fwilson.jirc.entities.Channel;
import me.fwilson.jirc.entities.User;
import me.fwilson.jirc.events.Event;
import me.fwilson.jirc.events.EventDispatcher;
import me.fwilson.jirc.events.EventHandler;

public class JoinPrinter implements EventHandler {
	public void handle(EventDispatcher d, Event e) {
		User user = (User)e.getInfo("user");
		Channel channel = (Channel)e.getInfo("channel");
		System.out.printf("--> | %s (%s@%s) has joined %s\n",
			   user.getNickname(), user.getUsername(), user.getHostname(),
			   channel.getName());
	}
}
