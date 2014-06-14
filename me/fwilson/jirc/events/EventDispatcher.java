package me.fwilson.jirc.events;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class EventDispatcher {
	private List<EventHandler> handlers;
	
	public EventDispatcher() {
		this.handlers = new LinkedList<EventHandler>();
	}
	
	public void attachHandler(EventHandler e) {
		this.handlers.add(e);
	}
	
	public void dispatchEvent(Event e) {
		for(int i = 0; i < this.handlers.size(); i++) {
			this.handlers.get(i).handle(this, e);
		}
	}
	
	public void dispatchAs(Event e, String s) {
		Event e2 = new Event(s);
		Map<String, Object> m = e.infoMap();
		for(String key : m.keySet()) {
			e2.addInfo(key, m.get(key));
		}
		this.dispatchEvent(e2);
	}
}