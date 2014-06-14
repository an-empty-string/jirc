package me.fwilson.jirc.entities;

/**
 * Represents a single user on IRC.
 */
public class User implements Targetable {

	private String nickname = "";
	private String username = "";
	private String hostname = "";
	
	public User(String nickname) {
		this.nickname = nickname;
	}
	
	public User(String nickname, String username, String hostname) {
		this.nickname = nickname;
		this.username = username;
		this.hostname = hostname;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return this.nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return this.hostname;
	}

	/**
	 * @param hostname the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public String toString() {
		return "<User " + this.nickname + "!" + this.username + "@" + this.hostname + ">";
	}

	public String getTarget() {
		return this.nickname;
	}
}
