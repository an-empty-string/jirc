package me.fwilson.jirc.stackscript;

import java.util.LinkedList;
import java.util.List;

/**
 * The StackScriptLexer provides methods to support the parsing of StackScript. 
 */
public class StackScriptLexer {
	/**
	 * lex() splits a string on whitespace with respect to "quotes"
	 * and \escapes. 
	 * @param in
	 * @return the split string
	 */
	public static List<String> lex(String in) {
		in += " ";
		StringBuilder current = new StringBuilder();
		List<String> l = new LinkedList<String>();
		boolean isQuoted = false;
		boolean escaping = false;
		
		for(char c : in.toCharArray()) {
			/**
			 * Escaping overrides everything
			 */
			if(escaping) {
				current.append(c);
				escaping = false;
				continue;
			}
			
			/**
			 * Start escaping, if we can
			 */
			if(c == '\\') {
				escaping = true;
				continue;
			}
			
			/**
			 * Start/end quoting
			 */
			if(c == '"') {
				isQuoted = !isQuoted;
				continue;
			}
			
			/**
			 * Check whitespace
			 */
			if(!isQuoted && Character.isWhitespace(c)) {
				if(current.length() == 0) {
					continue;
				}
				l.add(current.toString());
				current = new StringBuilder();
				continue;
			}
			
			current.append(c);
		}
		return l;
	}
}
