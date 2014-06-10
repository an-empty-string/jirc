package me.fwilson.jirc;

import java.io.IOException;
import java.net.UnknownHostException;

import me.fwilson.ircbuild.Join;
import me.fwilson.jirc.modularity.support.NotAModuleException;

public class IRCClientTest {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NotAModuleException {
		IRCClientConfiguration config = new IRCClientConfiguration();
		config.setHostname("127.0.0.1");
		
		final ModularIRCClient client = new ModularIRCClient(config);
		client.master.attachHandler(new PrintingEventHandler());
		
		client.on("irc-001", new Callback() {
			public void callback(Event e) {
				client.queueMessage(new Join(new Channel("###jirc-test")));
			}
		});
		
		client.loadModule("me.fwilson.jirc.modularity.modules.PrivmsgPrintingModule");
	}
}