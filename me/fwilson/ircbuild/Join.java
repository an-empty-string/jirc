package me.fwilson.ircbuild;

import java.util.LinkedList;
import java.util.List;

import me.fwilson.jirc.Channel;

public class Join extends IRCMessage {
	public Join(Channel c) {
		List<String> params = new LinkedList<String>();
		params.add(c.getName());
		this.command = "JOIN";
		this.params = params;
	}
}