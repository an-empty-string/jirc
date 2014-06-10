package me.fwilson.jirc.stackscript;

import java.util.Stack;

public class CommandNoOp implements StackScriptCommand {
	public void process(Stack<Object> s) {
		s.pop();
	}
}
