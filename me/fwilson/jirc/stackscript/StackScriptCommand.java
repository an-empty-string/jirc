package me.fwilson.jirc.stackscript;

import java.util.Stack;

public interface StackScriptCommand {
	void process(Stack<Object> s);
}