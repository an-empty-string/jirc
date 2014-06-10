package me.fwilson.jirc.stackscript;

import java.util.HashMap;
import java.util.Map;

public class StackScriptCommands {
	public static Map<String, StackScriptCommand> commands = new HashMap<String, StackScriptCommand>();
	static {
		commands.put("connect", new CommandIRCConnect());
		commands.put("on", new CommandOnEvent());
		commands.put("join", new CommandJoinChannel());
		commands.put("load", new CommandLoadModule());
		commands.put("noload", new CommandNoOp());
	}
}
