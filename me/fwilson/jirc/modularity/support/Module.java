package me.fwilson.jirc.modularity.support;

import java.util.List;

import me.fwilson.jirc.irc.clients.BaseIRCClient;

public abstract class Module {
	public abstract void init(BaseIRCClient client);
	public abstract String getName();
	public void setParams(List<String> params) {}
}
