package me.fwilson.jirc.entities;

/**
 * A factory class for creating Users.
 */
public class UserFactory {
	/**
	 * Constructs users from hostmask strings.
	 */
	public static User fromHostmask(String hostmask) {
		if(hostmask == null) {
			return new User("", "", "");
		}
		
		if(!(hostmask.contains("!") && hostmask.contains("@"))) {
			return new User("ircd", "ircd", hostmask);
		}
		
		String nickname = hostmask.split("!")[0];
		hostmask = hostmask.split("!")[1];
		String username = hostmask.split("@")[0];
		String hostname = hostmask.split("@")[1];
		
		return new User(nickname, username, hostname);
	}
}
