package me.fwilson.jirc;

public class PrintingEventHandler implements EventHandler {
	public void handle(EventDispatcher d, Event e) {
		System.out.println(e);
	}
}