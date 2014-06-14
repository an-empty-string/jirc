package me.fwilson.jirc.stackscript;

import java.util.Stack;

import me.fwilson.jirc.events.Callback;
import me.fwilson.jirc.events.Event;
import me.fwilson.jirc.irc.clients.BaseIRCClient;

public class CommandOnEvent implements StackScriptCommand {
	public void process(Stack<Object> s) {
		final String eventType = (String)s.pop();
		final StoredProcedure proc = (StoredProcedure)s.pop();
		final BaseIRCClient c = (BaseIRCClient)s.peek();
		c.on(eventType, new Callback() {
			public void callback(Event e) {
				proc.perform(c);
			}
		});
	}
}