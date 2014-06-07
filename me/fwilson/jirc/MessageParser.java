package me.fwilson.jirc;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MessageParser {
    private ParsedMessage msg = null;
    private String parsing = "";
    private Logger logger = Logger.getLogger("MessageParser");

    public MessageParser(String parsing) {
        this.parsing = parsing;
        logger.setLevel(Level.FINEST);
    }

    public ParsedMessage parseMessage() {
        /**
         * An IRC message consists of four parts: prefix, command, parameters,
         * and a trailing parameter.
         *
         * :prefix command parameters :trailing
         *
         * The only required one is the command.
         */
        String prefix = "";
        String command = "";
        List<String> params = new ArrayList<String>();
        String trailing = "";

        String withoutPrefix = "";

        if(parsing.startsWith(":")) {
            /**
             * This means that we have a prefix.
             */
            logger.finest("Our message has a prefix.");
            int endPrefix = parsing.indexOf(" ");
            prefix = parsing.substring(1, endPrefix);
            logger.finer("The prefix is: " + prefix);
            withoutPrefix = parsing.substring(endPrefix + 1);
            logger.finest("The message without the prefix: " + withoutPrefix);
        }
        else {
            /**
             * This means that we have no prefix.
             */
            logger.finest("No prefix.");
            withoutPrefix = parsing;
        }
        
        String withoutTrailing = "";
        if(withoutPrefix.contains(" :")) {
        	/**
        	 * This means that we have a trailing bit.
        	 */
        	String[] commandAndTrailing = withoutPrefix.split(":", 2);
        	withoutTrailing = commandAndTrailing[0];
        	trailing = commandAndTrailing[1];
        }
        else {
        	/**
        	 * We have no trailing bit
        	 */
        	withoutTrailing = withoutPrefix;
        }
        
        /**
         * Now we can parse the command/args
         */
        String[] commandAndArgs = withoutTrailing.split(" ", 2);
        command = commandAndArgs[0];
        String args = commandAndArgs[1];
        if(args.length() > 0) {
        	for(String arg : args.split(" ")) {
        		if(arg != "") {
        			params.add(arg);
        		}
        	}
        }
        
        if(trailing != "") {
        	params.add(trailing);
        }
        msg = new ParsedMessage(prefix, command, params);
        return msg;
    }

    public ParsedMessage getParsedMessage() {
        return msg;
    }
}
