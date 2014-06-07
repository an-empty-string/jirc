package me.fwilson.ircbuild;

import java.util.List;

public class IRCMessage {
	protected String command;
	protected List<String> params;
	
	public IRCMessage(String command, List<String> params) {
		this.command = command;
		this.params = params;
	}
	
	protected IRCMessage() {}

	public String build() {
		return MessageBuilder.build(this.command, this.params);
	}
	
	public String toString() {
		return "<IRCMessage (" + this.command + "): " + this.params + ">";
	}
}
