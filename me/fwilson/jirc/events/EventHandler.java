package me.fwilson.jirc.events;


public interface EventHandler {
	void handle(EventDispatcher d, Event e);
}
