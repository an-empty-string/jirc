package me.fwilson.jirc;

import java.util.Queue;

import me.fwilson.ircbuild.IRCMessage;

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
				Thread.sleep(500);
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
			client.master.dispatchEvent(e);
		}
	}
}
