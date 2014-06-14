package me.fwilson.ircbuild;

import java.util.List;

/**
 * Represents an IRC message to be sent to the server.
 */
public class IRCMessage {
	protected String command;
	protected List<String> params;
	
	/**
	 * Creates a new IRCMessage, with the given "command" and the given
	 * list of parameters.
	 * @param command
	 * @param params
	 */
	public IRCMessage(String command, List<String> params) {
		this.command = command;
		this.params = params;
	}
	
	protected IRCMessage() {}

	/**
	 * Call MessageBuilder to return a string representing this IRCMessage.
	 * @return a server-readable string
	 */
	public String build() {
		return MessageBuilder.build(this.command, this.params);
	}
	
	/**
	 * Returns a human-readable representation of the IRCMessage.
	 */
	public String toString() {
		return "<IRCMessage (" + this.command + "): " + this.params + ">";
	}
}
