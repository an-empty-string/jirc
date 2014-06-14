package me.fwilson.ircbuild;

import java.util.LinkedList;
import java.util.List;

import me.fwilson.jirc.entities.Channel;

public class Who extends IRCMessage {
	public Who(Channel c) {
		List<String> params = new LinkedList<String>();
		params.add(c.getName());
		this.command = "WHO";
		this.params = params;
	}
}