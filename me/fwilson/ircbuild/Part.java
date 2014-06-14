package me.fwilson.ircbuild;

import java.util.LinkedList;
import java.util.List;

import me.fwilson.jirc.entities.Channel;

public class Part extends IRCMessage {
	public Part(Channel c) {
		List<String> params = new LinkedList<String>();
		params.add(c.getName());
		this.command = "PART";
		this.params = params;
	}
}
 