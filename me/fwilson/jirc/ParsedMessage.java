package me.fwilson.jirc;

import java.util.List;

public class ParsedMessage {
	private String prefix;
	private String command;
	private List<String> params;
	
	public ParsedMessage(String prefix, String command, List<String> params) {
		this.prefix = prefix;
		this.command = command;
		this.params = params;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public String getCommand() {
		return this.command;
	}
	
	public List<String> getParams() {
		return params;
	}
	
	public String toString() {
		return "<ParsedMessage prefix=" + this.prefix + " command=" + this.command + " params=" + this.params + ">";
	}
}
