package me.fwilson.jirc.events.handlers;

import me.fwilson.jirc.events.Event;
import me.fwilson.jirc.events.EventDispatcher;
import me.fwilson.jirc.events.EventHandler;

public class PrintingEventHandler implements EventHandler {
	public void handle(EventDispatcher d, Event e) {
		System.out.println(e);
	}
}