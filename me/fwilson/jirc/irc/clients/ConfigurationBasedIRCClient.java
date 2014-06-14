package me.fwilson.jirc.irc.clients;

import java.io.IOException;
import java.net.UnknownHostException;

import me.fwilson.jirc.irc.IRCClientConfiguration;

public class ConfigurationBasedIRCClient extends BaseIRCClient {
	IRCClientConfiguration config;
	
	public ConfigurationBasedIRCClient(IRCClientConfiguration config) throws UnknownHostException, IOException {
		super(config.getHostname(), config.getPort());
		this.client.writeln("USER " + config.getUsername() + " . . :" + config.getRealname());
		this.client.writeln("NICK " + config.getNickname());
		this.config = config;
	}

	public IRCClientConfiguration getConfig() {
		return config;
	}
}