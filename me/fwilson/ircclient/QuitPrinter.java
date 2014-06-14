package me.fwilson.ircclient;

import me.fwilson.jirc.entities.User;
import me.fwilson.jirc.events.Event;
import me.fwilson.jirc.events.EventDispatcher;
import me.fwilson.jirc.events.EventHandler;

public class QuitPrinter implements EventHandler {
	public void handle(EventDispatcher d, Event e) {
		User user = (User)e.getInfo("user");
		String reason = (String)e.getInfo("reason");
		System.out.printf("<-- | %s (%s@%s) has quit (%s)\n",
			   user.getNickname(), user.getUsername(), user.getHostname(),
			   reason);
	}
}