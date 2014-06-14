package me.fwilson.jirc.irc.clients;

import java.io.IOException;
import java.net.UnknownHostException;

import me.fwilson.jirc.irc.IRCClientConfiguration;
import me.fwilson.jirc.modularity.support.ModuleFactory;
import me.fwilson.jirc.modularity.support.NotAModuleException;

public class ModularIRCClient extends TrackingIRCClient {
	public ModularIRCClient(IRCClientConfiguration config) throws UnknownHostException, IOException {
		super(config);
	}
	
	public void loadModule(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NotAModuleException {
		ModuleFactory.getModule(name).init(this);
	}
}