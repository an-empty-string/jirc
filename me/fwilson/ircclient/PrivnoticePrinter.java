package me.fwilson.ircclient;

import me.fwilson.jirc.entities.User;
import me.fwilson.jirc.events.Event;
import me.fwilson.jirc.events.EventDispatcher;
import me.fwilson.jirc.events.EventHandler;

public class PrivnoticePrinter implements EventHandler {
	public void handle(EventDispatcher d, Event e) {
		User sender = (User)e.getInfo("from");
		String text = (String)e.getInfo("text");
		System.out.printf("*** | %s whispers: %s\n", sender.getNickname(), text);
	}
}
