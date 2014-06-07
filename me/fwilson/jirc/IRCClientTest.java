package me.fwilson.jirc;

import java.io.IOException;
import java.net.UnknownHostException;

import me.fwilson.ircbuild.Join;

public class IRCClientTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		IRCClientConfiguration config = new IRCClientConfiguration();
		config.setHostname("chat.freenode.net");
		config.setPort(6667);
		final EventParsingIRCClient client = new EventParsingIRCClient(config);
		client.on("irc-001", new Callback() {
			public void callback(Event e) {
				client.queueMessage(new Join(new Channel("#botwar")));
			}
		});
	}
}
