# jIRC overview
## The Event Bus
- Implemented using the Event, EventHandler, and EventDispatcher classes
- Allows messages to be sent asynchronously throughout the application
- Used by the networking portion of the library to notify the app of network
  events

## The Networking Part
- Uses the Event bus to dispatch messages throughout the application
- Uses a Queue to handle rate-limiting to the server
- ThreadedInputStreamEventHandler!
- ThreadedDelayedOutputQueueMessageSender!

## StackScript
- Uses a stack to write scripts that interact with an IRC server
- Custom split method (handles "quoted strings like this one" and \\\\escapes)

## Modular design
- Uses fancy reflection stuff to load other classes at runtime
- Uses the Class type

## IRC parsing library
- Generic enough to use in other projects

## Example
<pre>
"chat.freenode.net" 6667 jircbot_test connect
"me.fwilson.ircclient.FormattingModule" load
"##fwilson-test" join irc-001 on
</pre>

- `java me.fwilson.jirc.stackscript.StackScriptProcessor example.stackscript`
- StackScript can load modules
