package me.fwilson.ircbuild;

import java.util.List;

/**
 * A class for generating IRC message strings to be sent to the server.
 */
public class MessageBuilder {
	/**
	 * Build an IRC message given a variable argument list.
	 * @param args
	 * @return a server-readable string representing an IRC message
	 */
	public static String build(String ... args) {
		StringBuilder builder = new StringBuilder();
		for(String arg : args) {
			if(arg.contains(" ")) {
				/**
				 * The only argument allowed to have spaces is the trailing one
				 * Don't even worry about user error
				 */
				builder.append(" :");
				builder.append(arg);
			}
			else {
				builder.append(" ");
				builder.append(arg);
			}
		}
		return builder.toString().substring(1);
	}
	
	/**
	 * Build a string.
	 * @param command
	 * @param params
	 * @return a string that was built
	 */
	public static String build(String command, List<String> params) {
		StringBuilder builder = new StringBuilder();
		params.add(0, command);
		for(String param : params) {
			if(param.contains(" ")) {
				builder.append(" :");
				builder.append(param);
			}
			else {
				builder.append(" ");
				builder.append(param);
			}
		}
		return builder.toString().substring(1);
	}
}
