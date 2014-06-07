package me.fwilson.jirc;

import java.util.Set;
import java.util.TreeSet;

public class Channel implements Targetable {
	private String name;
	private String modes;
	private Set<User> users = new TreeSet<User>();
	
	public Channel(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getModes() {
		return modes;
	}
	
	public void setModes(String modes) {
		this.modes = modes;
	}
	
	public Set<User> getUsers() {
		return this.users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void removeUser(User user) {
		this.users.remove(user);
	}
	
	public boolean hasUser(User user) {
		return this.users.contains(user);
	}

	public String getTarget() {
		return this.name;
	}
}
