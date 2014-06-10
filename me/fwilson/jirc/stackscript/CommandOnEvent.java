package me.fwilson.jirc.stackscript;

import java.util.Stack;

import me.fwilson.jirc.BaseIRCClient;
import me.fwilson.jirc.Callback;
import me.fwilson.jirc.Event;

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