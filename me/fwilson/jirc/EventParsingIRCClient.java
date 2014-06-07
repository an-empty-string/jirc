package me.fwilson.jirc;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public class EventParsingIRCClient extends ErrorHandlingIRCClient {
	public EventParsingIRCClient(IRCClientConfiguration config) throws UnknownHostException, IOException {
		super(config);
		this.parseIRCEvents();
	}
	
	private void parseIRCEvents() {
		this.on("irc-privmsg", new EventHandler() {
			public void handle(EventDispatcher d, Event e) {
				ParsedMessage message = (ParsedMessage)e.getInfo("message");
				String hostmask = (String)message.getPrefix();
				List<String> params = message.getParams();
				User sender = UserFactory.fromHostmask(hostmask);
				String destination = params.get(0);
				String text = params.get(1);
				
				Event e2 = new Event("msg");
				e2.addInfo("from", sender);
				e2.addInfo("to", destination);
				e2.addInfo("text", text);
				d.dispatchEvent(e2);
				
				if(destination.equals(config.getNickname())) {
					/**
					 * Private message
					 */
					d.dispatchAs(e2, "pm");
				}
				
				else {
					d.dispatchAs(e2, "chanmsg");
				}
			}
		});
		
		this.on("irc-notice", new EventHandler() {
			public void handle(EventDispatcher d, Event e) {
				ParsedMessage message = (ParsedMessage)e.getInfo("message");
				String hostmask = message.getPrefix();
				List<String> params = message.getParams();
				User sender = UserFactory.fromHostmask(hostmask);
				String destination = params.get(0);
				String text = params.get(1);
				
				Event e2 = new Event("notice");
				e2.addInfo("from", sender);
				e2.addInfo("to", destination);
				e2.addInfo("text", text);
				d.dispatchEvent(e2);
				
				if(destination.equals(config.getNickname())) {
					/**
					 * Private notice
					 */
					d.dispatchAs(e2, "privnotice");
				}
				
				else {
					d.dispatchAs(e2, "channotice");
				}
			}
		});
		
		this.on("irc-join", new EventHandler() {
			public void handle(EventDispatcher d, Event e) {
				ParsedMessage message = (ParsedMessage)e.getInfo("message");
				Event e2 = new Event("join");
				e2.addInfo("user", UserFactory.fromHostmask(message.getPrefix()));
				e2.addInfo("channel", new Channel(message.getParams().get(0)));
				d.dispatchEvent(e2);
			}
		});
		
		this.on("irc-part", new EventHandler() {
			public void handle(EventDispatcher d, Event e) {
				ParsedMessage message = (ParsedMessage)e.getInfo("message");
				Event e2 = new Event("part");
				e2.addInfo("user", UserFactory.fromHostmask(message.getPrefix()));
				e2.addInfo("channel", new Channel(message.getParams().get(0)));
				if(message.getParams().size() > 1) {
					e2.addInfo("reason", message.getParams().get(1));
				}
				else {
					e2.addInfo("reason", "");
				}
				d.dispatchEvent(e2);
			}
		});
		
		this.on("irc-quit", new EventHandler() {
			public void handle(EventDispatcher d, Event e) {
				ParsedMessage message = (ParsedMessage)e.getInfo("message");
				Event e2 = new Event("quit");
				e2.addInfo("user", UserFactory.fromHostmask(message.getPrefix()));
				if(message.getParams().size() > 0) {
					e2.addInfo("reason", message.getParams().get(0));
				}
				else {
					e2.addInfo("reason", "");
				}
				d.dispatchEvent(e2);
			}
		});
	}
}
