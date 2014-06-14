package me.fwilson.jirc.stackscript;

import java.util.Stack;

import me.fwilson.ircbuild.Join;
import me.fwilson.jirc.entities.Channel;
import me.fwilson.jirc.irc.clients.BaseIRCClient;

public class CommandJoinChannel implements StackScriptCommand {
	public void process(Stack<Object> s) {
		final String channelName = (String)s.pop();
		final Channel channel = new Channel(channelName);
		s.push(new StoredProcedure() {
			public void perform(BaseIRCClient client) {
				client.queueMessage(new Join(channel));
			}
		});
	}
}
