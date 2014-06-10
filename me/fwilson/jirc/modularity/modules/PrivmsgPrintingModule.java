package me.fwilson.jirc.modularity.modules;

import me.fwilson.jirc.BaseIRCClient;
import me.fwilson.jirc.Callback;
import me.fwilson.jirc.Event;
import me.fwilson.jirc.User;
import me.fwilson.jirc.modularity.support.Module;

public class PrivmsgPrintingModule extends Module {
	public void init(BaseIRCClient client) {
		client.on("msg", new Callback() {
			public void callback(Event e) {
				System.out.println("(" + ((String)e.getInfo("to")) + ") <"
						         + ((User) e.getInfo("from")).getNickname() + "> "
						         + (String)e.getInfo("text"));
			}
		});
	}

	public String getName() {
		return "PrivmsgPrintingModule";
	}
}