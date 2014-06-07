package me.fwilson.jirc;

public class IRCParsingEventHandler implements EventHandler {
	public void handle(EventDispatcher d, Event e) {
		if(e.getType() == "raw") {
			MessageParser p = new MessageParser((String)e.getInfo("line"));
			p.parseMessage();
			
			Event e2 = new Event("irc");
			e2.addInfo("message", p.getParsedMessage());
			d.dispatchEvent(e2);
			
			Event e3 = new Event("irc-" + p.getParsedMessage().getCommand().toLowerCase());
			e3.addInfo("message", p.getParsedMessage());
			d.dispatchEvent(e3);
		}
	}
}