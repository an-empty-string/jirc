package me.fwilson.jirc.stackscript;

import me.fwilson.jirc.BaseIRCClient;

public interface StoredProcedure {
	void perform(BaseIRCClient client);
}
