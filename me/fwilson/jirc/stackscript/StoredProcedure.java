package me.fwilson.jirc.stackscript;

import me.fwilson.jirc.irc.clients.BaseIRCClient;

public interface StoredProcedure {
	void perform(BaseIRCClient client);
}
