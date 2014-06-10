package me.fwilson.jirc.modularity.support;

import java.util.LinkedList;
import java.util.List;

public class ModuleFactory {
	public static Module getModule(String name, List<String> params) throws ClassNotFoundException, NotAModuleException, InstantiationException, IllegalAccessException {
		Class<?> moduleClass = Class.forName(name);
		Class<?> moduleInterface = Module.class;
		Object o = moduleClass.newInstance();
		Module m;
		if(moduleInterface.isInstance(o)) {
			m = (Module)o;
		}
		else {
			throw new NotAModuleException();
		}
		m.setParams(params);
		return m;
	}
	
	public static Module getModule(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NotAModuleException {
		return getModule(name, new LinkedList<String>());
	}
}
