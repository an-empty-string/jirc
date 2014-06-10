package me.fwilson.jirc;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.fwilson.jirc.networking.EventedTCPClient;

public class RedispatchingEventedTCPClient {
	protected EventedTCPClient client;
	protected EventDispatcher master;
	
	/**
	 * the String is the type of the events that should be forwarded
	 */
	private Map<String, List<EventHandler>> handlers;
	
	public RedispatchingEventedTCPClient(String hostname, int port) throws UnknownHostException, IOException {
		this.client = new EventedTCPClient(hostname, port);
		this.init();
	}
	
	protected void init() {
		this.master = this.client.getDispatcher();
		this.handlers = new HashMap<String, List<EventHandler>>();
		this.master.attachHandler(new EventHandler() {
			public void handle(EventDispatcher d, Event e) {
				if(handlers.keySet().contains(e.getType())) {
					for(EventHandler h : handlers.get(e.getType())) {
						h.handle(d, e);
					}
				}
			}
		});
	}
	
	public EventDispatcher getMaster() {
		return this.master;
	}
	
	public void on(String eventType, EventHandler callback) {
		if(!this.handlers.keySet().contains(eventType)) {
			this.handlers.put(eventType, new LinkedList<EventHandler>());
		}
		this.handlers.get(eventType).add(callback);
	}
	
	public void writeln(String l) {
		this.client.writeln(l);
	}
}
