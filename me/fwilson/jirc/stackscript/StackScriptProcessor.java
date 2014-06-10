package me.fwilson.jirc.stackscript;

import java.util.List;
import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class StackScriptProcessor {
	public static void main(String[] args) throws FileNotFoundException {
		runFromFile(args[0]);
	}
	
	public static void runFromFile(String filename) throws FileNotFoundException {
		// http://stackoverflow.com/questions/3402735/what-is-simplest-way-to-read-a-file-into-string
		File f = new File(filename);
		Scanner s = new Scanner(f);
		s.useDelimiter("\\Z");
		String contents = s.next();
		s.close();
		runStackScript(contents);
	}
	
	public static void runStackScript(String in) {
		List<String> commands = StackScriptLexer.lex(in);
		Stack<Object> stack = new Stack<Object>();
		for(String cmd : commands) {
			if(StackScriptCommands.commands.containsKey(cmd)) {
				try {
					StackScriptCommands.commands.get(cmd).process(stack);
				}
				catch(ClassCastException e) {
					e.printStackTrace();
					System.out.println(stack);
				}
			}
			else {
				stack.push(cmd);
			}
		}
	}
}