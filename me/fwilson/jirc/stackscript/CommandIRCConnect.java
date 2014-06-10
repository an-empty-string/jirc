package me.fwilson.jirc.stackscript;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Stack;

import me.fwilson.jirc.IRCClientConfiguration;
import me.fwilson.jirc.ModularIRCClient;

public class CommandIRCConnect implements StackScriptCommand {
	public void process(Stack<Object> s) {
		String nickname = (String)s.pop();
		int port = Integer.parseInt((String)s.pop());
		String host = (String)s.pop();
		IRCClientConfiguration config = new IRCClientConfiguration();
		config.setNickname(nickname);
		config.setHostname(host);
		config.setPort(port);
		try {
			s.push(new ModularIRCClient(config));
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
