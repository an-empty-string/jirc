package me.fwilson.jirc.irc.clients;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.fwilson.ircbuild.Who;
import me.fwilson.ircbuild.Whois;
import me.fwilson.jirc.entities.Channel;
import me.fwilson.jirc.entities.User;
import me.fwilson.jirc.events.Callback;
import me.fwilson.jirc.events.Event;
import me.fwilson.jirc.events.EventDispatcher;
import me.fwilson.jirc.events.EventHandler;
import me.fwilson.jirc.irc.IRCClientConfiguration;
import me.fwilson.jirc.parsing.ParsedMessage;

public class TrackingIRCClient extends EventParsingIRCClient {
	protected Map<String, Channel> channels;
	protected Map<String, User> users;
	public TrackingIRCClient(IRCClientConfiguration config) throws UnknownHostException, IOException {
		super(config);
		channels = new HashMap<String, Channel>();
		users = new HashMap<String, User>();
		this.startTrack();
	}
	
	private void startTrack() {
		this.on("join", new Callback() {
			public void callback(Event e) {
				User sender = (User)e.getInfo("user");
				if(sender.getNickname().equals(config.getNickname())) {
					Channel c = (Channel)e.getInfo("channel");
					channels.put(c.getName(), c);
					queueMessage(new Who(c));
				}
			}
		});
		this.on("part", new Callback() {
			public void callback(Event e) {
				User sender = (User)e.getInfo("user");
				if(sender.getNickname().equals(config.getNickname())) {
					Channel c = (Channel)e.getInfo("channel");
					if(channels.containsKey(c.getName())) {
						channels.remove(c.getName());
					}
				}
			}
		});
		this.on("irc-352", new EventHandler() {
			public void handle(EventDispatcher d, Event e) {
				ParsedMessage message = (ParsedMessage)e.getInfo("message");
				List<String> params = message.getParams().subList(2, message.getParams().size());
				String username = params.get(0);
				String hostname = params.get(1);
				String nickname = params.get(3);
				users.put(nickname, new User(nickname, username, hostname));
			}
		});
		this.on("nick", new Callback() {
			public void callback(Event e) {
				queueMessage(new Whois(new User((String)e.getInfo("newnick"))));
			}
		});
		this.on("irc-311", new Callback() {
			public void callback(Event e) {
				ParsedMessage message = (ParsedMessage)e.getInfo("message");
				List<String> params = message.getParams().subList(1, message.getParams().size());
				String nickname = params.get(0);
				String username = params.get(1);
				String hostname = params.get(2);
				users.put(nickname, new User(nickname, username, hostname));
			}
		});
	}
}
