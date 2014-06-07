package me.fwilson.jirc;

import java.util.Map;
import java.util.HashMap;

public class Event {
	private String type;
	private Map<String, Object> info;
	
	public Event(String type) {
		this.type = type;
		this.info = new HashMap<String, Object>();
	}
	
	public String getType() {
		return this.type;
	}
	
	public void addInfo(String s, Object o) {
		this.info.put(s, o);
	}
	
	public Object getInfo(String s) {
		return this.info.get(s);
	}
	
	public String toString() {
		return "<Event (" + this.type + "): " + this.info + ">";
	}
	
	public Map<String, Object> infoMap() {
		return this.info;
	}
}
