package me.fwilson.jirc.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import me.fwilson.jirc.EventDispatcher;

public class EventedTCPClient {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private EventDispatcher dispatcher;
	private ThreadedInputStreamEventHandler inHandler;
	private Thread inHandlerThread;
	
	public EventedTCPClient(String hostname, int port) throws UnknownHostException, IOException {
		this.socket = new Socket(hostname, port);
		this.out = new PrintWriter(this.socket.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.dispatcher = new EventDispatcher();
		this.inHandler = new ThreadedInputStreamEventHandler(this.dispatcher, this.in);
		this.inHandlerThread = new Thread(this.inHandler);
		this.inHandlerThread.start();
	}
	
	public void writeln(String s) {
		this.out.write(s + "\n");
		this.out.flush();
	}
	
	public EventDispatcher getDispatcher() {
		return this.dispatcher;
	}
}
