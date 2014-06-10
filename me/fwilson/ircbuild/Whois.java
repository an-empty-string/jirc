package me.fwilson.ircbuild;

import java.util.LinkedList;
import java.util.List;

import me.fwilson.jirc.User;

public class Whois extends IRCMessage {
	public Whois(User user) {
		List<String> params = new LinkedList<String>();
		params.add(user.getNickname());
		this.command = "WHOIS";
		this.params = params;
	}
}
