package me.fwilson.jirc.modularity.modules;

import me.fwilson.jirc.BaseIRCClient;
import me.fwilson.jirc.Callback;
import me.fwilson.jirc.Event;
import me.fwilson.jirc.modularity.support.Module;

public class DebugModule extends Module {
	@Override
	public void init(BaseIRCClient client) {
		client.getMaster().attachHandler(new Callback(){
			public void callback(Event e) {
				System.out.println(e);
			}
		});
	}
	
	@Override
	public String getName() {
		return "DebugModule";
	}
}
