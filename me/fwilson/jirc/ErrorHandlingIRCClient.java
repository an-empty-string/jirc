package me.fwilson.jirc;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ErrorHandlingIRCClient extends ConfigurationBasedIRCClient {

	public ErrorHandlingIRCClient(IRCClientConfiguration config) throws UnknownHostException, IOException {
		super(config);
		this.doErrorHandlers();
	}
	
	private void doErrorHandlers() {
		final Map<String, String> errors = new HashMap<String, String>();
		errors.put("401", "ERR_NOSUCHNICK");
		errors.put("402", "ERR_NOSUCHSERVER");
		errors.put("403", "ERR_NOSUCHCHANNEL");
		errors.put("404", "ERR_CANNOTSENDTOCHAN");
		errors.put("405", "ERR_TOOMANYCHANNELS");
		errors.put("406", "ERR_WASNOSUCHNICK");
		errors.put("407", "ERR_TOOMANYTARGETS");
		errors.put("408", "ERR_NOSUCHSERVICE");
		errors.put("409", "ERR_NOORIGIN");
		errors.put("411", "ERR_NORECIPIENT");
		errors.put("412", "ERR_NOTEXTTOSEND");
		errors.put("413", "ERR_NOTOPLEVEL");
		errors.put("414", "ERR_WILDTOPLEVEL");
		errors.put("415", "ERR_BADMASK");
		errors.put("421", "ERR_UNKNOWNCOMMAND");
		errors.put("422", "ERR_NOMOTD");
		errors.put("423", "ERR_NOADMININFO");
		errors.put("424", "ERR_FILEERROR");
		errors.put("431", "ERR_NONICKNAMEGIVEN");
		errors.put("432", "ERR_ERRONEUSNICKNAME");
		errors.put("433", "ERR_NICKNAMEINUSE");
		errors.put("436", "ERR_NICKCOLLISION");
		errors.put("441", "ERR_USERNOTINCHANNEL");
		errors.put("442", "ERR_NOTONCHANNEL");
		errors.put("443", "ERR_USERONCHANNEL");
		errors.put("444", "ERR_NOLOGIN");
		errors.put("445", "ERR_SUMMONDISABLED");
		errors.put("446", "ERR_USERSDISABLED");
		errors.put("451", "ERR_NOTREGISTERED");
		errors.put("461", "ERR_NEEDMOREPARAMS");
		errors.put("462", "ERR_ALREADYREGISTERED");
		errors.put("463", "ERR_NOPERMFORHOST");
		errors.put("464", "ERR_PASSWDMISMATCH");
		errors.put("465", "ERR_YOUREBANNEDCREEP");
		errors.put("466", "ERR_YOUWILLBEBANNED");
		errors.put("467", "ERR_KEYSET");
		errors.put("471", "ERR_CHANNELISFULL");
		errors.put("472", "ERR_UNKNOWNMODE");
		errors.put("473", "ERR_INVITEONLYCHAN");
		errors.put("474", "ERR_BANNEDFROMCHAN");
		errors.put("475", "ERR_BADCHANNELKEY");
		errors.put("476", "ERR_BADCHANMASK");
		errors.put("478", "ERR_BANLISTFULL");
		errors.put("481", "ERR_NOPRIVILEGES");
		errors.put("482", "ERR_CHANOPRIVSNEEDED");
		errors.put("483", "ERR_CANTKILLSERVER");
		errors.put("485", "ERR_UNIQOPRIVSNEEDED");
		errors.put("491", "ERR_NOOPERHOST");
		errors.put("492", "ERR_NOSERVICEHOST");
		errors.put("501", "ERR_UMODEUNKNOWNFLAG");
		errors.put("502", "ERR_USERSDONTMATCH");
		
		for(final String error : errors.keySet()) {
			this.on("irc-" + error, new EventHandler() {
				public void handle(EventDispatcher d, Event e) {
					Event e2 = new Event("error");
					e2.addInfo("type", errors.get(error));
					e2.addInfo("message", e.getInfo("message"));
					d.dispatchEvent(e2);
				}
			});
		}
		
		this.on("error", new PrintingEventHandler());
		this.on("irc-433", new EventHandler() {
			public void handle(EventDispatcher d, Event e) {
				config.setNickname(config.getNickname() + "_");
				String response = "NICK " + config.getNickname();
				client.writeln(response);
			}
		});
	}
}
