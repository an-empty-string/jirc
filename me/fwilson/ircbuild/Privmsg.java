package me.fwilson.ircbuild;

import java.util.LinkedList;
import java.util.List;

import me.fwilson.jirc.Targetable;

public class Privmsg extends IRCMessage {
	public Privmsg(Targetable target, String message) {
		super();
		List<String> params = new LinkedList<String>();
		params.add(target.getTarget());
		params.add(message);
		this.command = "PRIVMSG";
		this.params = params;
	}
}
