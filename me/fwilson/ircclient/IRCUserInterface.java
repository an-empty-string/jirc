package me.fwilson.ircclient;

import java.io.IOException;
import java.net.UnknownHostException;

import me.fwilson.jirc.irc.IRCClientConfiguration;
import me.fwilson.jirc.irc.clients.ModularIRCClient;
import me.fwilson.jirc.modularity.support.NotAModuleException;

public class IRCUserInterface {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NotAModuleException {
		IRCClientConfiguration config = new IRCClientConfiguration();
		config.setHostname(args[0]);
		config.setPort(Integer.parseInt(args[1]));
		
		ModularIRCClient client = null;
		try {
			client = new ModularIRCClient(config);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + config.getHostname());
			System.exit(1);
		} catch (IOException e) {
			System.out.println("An I/O error occurred.");
			e.printStackTrace();
			System.exit(1);
		}
		
		client.loadModule("me.fwilson.ircclient.FormattingModule");
	}
}