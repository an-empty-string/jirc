package me.fwilson.jirc;

public class IRCClientConfiguration {
	private String hostname = "chat.freenode.net";
	private int port = 6667;
	private String nickname = "jircbot";
	private String username = "jircbot";
	private String realname = "jIRC bot framework";
	
	public IRCClientConfiguration() {}
	
	public IRCClientConfiguration(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
}