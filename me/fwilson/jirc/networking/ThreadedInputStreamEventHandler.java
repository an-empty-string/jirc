package me.fwilson.jirc.networking;

import java.io.BufferedReader;
import java.io.IOException;

import me.fwilson.jirc.Event;
import me.fwilson.jirc.EventDispatcher;

/**
 * The ThreadedInputStreamEventHandler dispatches "raw" events to an
 * EventDispatcher given a BufferedReader.
 * @author fwilson
 *
 */
public class ThreadedInputStreamEventHandler implements Runnable {
	private EventDispatcher dispatcher;
	private BufferedReader reader;
	
	/**
	 * Construct a new ThreadedInputStreamEventHandler given an EventDispatcher
	 * and a BufferedReader.
	 * @param dispatcher the EventDispatcher to use
	 * @param reader the BufferedReader to use
	 */
	public ThreadedInputStreamEventHandler(EventDispatcher dispatcher, BufferedReader reader) {
		this.dispatcher = dispatcher;
		this.reader = reader;
	}
	
	/**
	 * Start the main event dispatch loop. This should probably be in a thread.
	 */
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
