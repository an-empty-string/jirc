package me.fwilson.jirc.networking;

import java.io.BufferedReader;
import java.io.IOException;

import me.fwilson.jirc.Event;
import me.fwilson.jirc.EventDispatcher;

public class ThreadedInputStreamEventHandler implements Runnable {
	private EventDispatcher dispatcher;
	private BufferedReader reader;
	
	public ThreadedInputStreamEventHandler(EventDispatcher dispatcher, BufferedReader reader) {
		this.dispatcher = dispatcher;
		this.reader = reader;
	}
	
	public void run() {
		String in;
		try {
			while((in = this.reader.readLine()) != null) {
				Event e = new Event("raw");
				e.addInfo("line", in);
				this.dispatcher.dispatchEvent(e);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
