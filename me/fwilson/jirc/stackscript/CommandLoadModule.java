package me.fwilson.jirc.stackscript;

import java.util.Stack;

import me.fwilson.jirc.BaseIRCClient;
import me.fwilson.jirc.modularity.support.ModuleFactory;
import me.fwilson.jirc.modularity.support.NotAModuleException;

public class CommandLoadModule implements StackScriptCommand {
	public void process(Stack<Object> s) {
		String moduleName = (String)s.pop();
		BaseIRCClient client = (BaseIRCClient)s.peek();
		try {
			ModuleFactory.getModule(moduleName).init(client);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (InstantiationException e) {
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (NotAModuleException e) {
			e.printStackTrace();
		}
	}
}
