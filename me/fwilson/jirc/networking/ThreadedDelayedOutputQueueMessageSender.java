package me.fwilson.jirc.networking;

import java.util.Queue;

import me.fwilson.ircbuild.IRCMessage;
import me.fwilson.jirc.events.Event;
import me.fwilson.jirc.irc.clients.BaseIRCClient;

public class ThreadedDelayedOutputQueueMessageSender implements Runnable {
	/**
	 * The class that handles threaded and delayed message sending in the
	 * output queue.
	 */
	private BaseIRCClient client;
	private Queue<IRCMessage> messages;
	
	public ThreadedDelayedOutputQueueMessageSender(BaseIRCClient client, Queue<IRCMessage> messages) {
		this.client = client;
		this.messages = messages;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(messages.isEmpty()) {
				continue;
			}
			IRCMessage message = messages.remove();
			Event e = new Event("message-sent");
			e.addInfo("message", message);
			this.client.writeln(message.build());
			client.getMaster().dispatchEvent(e);
		}
	}
}
