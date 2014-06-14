package me.fwilson.ircclient;

import me.fwilson.jirc.irc.clients.BaseIRCClient;
import me.fwilson.jirc.modularity.support.Module;

public class FormattingModule extends Module {
	@Override
	public void init(BaseIRCClient client) {
		client.on("join", new JoinPrinter());
		client.on("part", new PartPrinter());
		client.on("quit", new QuitPrinter());
		client.on("nick", new NickPrinter());
		client.on("channotice", new ChannoticePrinter());
		client.on("privnotice", new PrivnoticePrinter());
		client.on("chanmsg", new ChanmsgPrinter());
		client.on("pm", new PrivmsgPrinter());
	}

	@Override
	public String getName() {
		return "FormattingModule";
	}
}
