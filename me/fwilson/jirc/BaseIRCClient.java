package me.fwilson.jirc;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import me.fwilson.ircbuild.IRCMessage;

public class BaseIRCClient extends RedispatchingEventedTCPClient {
	private Queue<IRCMessage> outgoingQueue;
	private ThreadedDelayedOutputQueueMessageSender sender;
	
	public BaseIRCClient(String hostname, int port) throws UnknownHostException, IOException {
		super(hostname, port);
		setupQueue();
		setupHandlers();
	}
	
	public BaseIRCClient(String hostname) throws UnknownHostException, IOException {
		super(hostname, 6667);
		setupQueue();
		setupHandlers();
	}
	
	protected void setupHandlers() {
		this.on("raw", new IRCParsingEventHandler());
		this.on("irc-ping", new EventHandler() {
			public void handle(EventDispatcher d, Event e) {
				String response = "PONG " + ((ParsedMessage)(e.getInfo("message"))).getParams().get(0);
				client.writeln(response);
			}
		});
	}
	
	protected void setupQueue() {
		outgoingQueue = new LinkedList<IRCMessage>();
		sender = new ThreadedDelayedOutputQueueMessageSender(this, outgoingQueue);
		new Thread(sender).start();
	}
	
	public void queueMessage(IRCMessage m) {
		outgoingQueue.add(m);
		Event e = new Event("message-queued");
		e.addInfo("message", m);
		this.master.dispatchEvent(e);
	}
}
