package me.fwilson.ircclient;

import me.fwilson.jirc.events.Event;
import me.fwilson.jirc.events.EventDispatcher;
import me.fwilson.jirc.events.EventHandler;

public class NickPrinter implements EventHandler {
	public void handle(EventDispatcher d, Event e) {
		String oldnick = (String)e.getInfo("oldnick");
		String newnick = (String)e.getInfo("newnick");
		System.out.printf("--- | %s is now known as %s\n", oldnick, newnick);
	}
}
