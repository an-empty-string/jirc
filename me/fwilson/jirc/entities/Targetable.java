package me.fwilson.jirc.entities;

/**
 * This interface represents an entity that you can send a message or notice
 * to on IRC.
 */
public interface Targetable {
	String getTarget();
}
