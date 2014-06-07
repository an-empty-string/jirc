package me.fwilson.jirc;

import java.io.IOException;
import java.net.UnknownHostException;

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