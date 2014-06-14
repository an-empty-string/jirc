package me.fwilson.jirc.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import me.fwilson.jirc.events.EventDispatcher;

/**
 * An EventedTCPClient uses the Event system that is part of jIRC to complement
 * raw sockets. When constructed, it creates a ThreadedInputStreamEventHandler
 * to handle incoming lines from the server.
 */
public class EventedTCPClient {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private EventDispatcher dispatcher;
	private ThreadedInputStreamEventHandler inHandler;
	private Thread inHandlerThread;
	
	/**
	 * Construct a new EventedTCPClient, given a hostname and port.
	 * Automatically connects to given hostname/port.
	 * @param hostname a String representing the host to connect to
	 * @param port the port to connect to
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public EventedTCPClient(String hostname, int port) throws UnknownHostException, IOException {
		this.socket = new Socket(hostname, port);
		this.out = new PrintWriter(this.socket.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.dispatcher = new EventDispatcher();
		this.inHandler = new ThreadedInputStreamEventHandler(this.dispatcher, this.in);
		this.inHandlerThread = new Thread(this.inHandler);
		this.inHandlerThread.start();
	}
	
	/**
	 * Write and send a line to the server.
	 * @param s the string to send to the server
	 */
	public void writeln(String s) {
		this.out.write(s + "\n");
		this.out.flush();
	}
	
	/**
	 * Get the EventDispatcher used for this EventedTCPClient.
	 * @return an EventDispatcher
	 */
	public EventDispatcher getDispatcher() {
		return this.dispatcher;
	}
}
