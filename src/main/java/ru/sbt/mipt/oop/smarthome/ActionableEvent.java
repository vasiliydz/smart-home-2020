package ru.sbt.mipt.oop.smarthome;

// Event, который относится к некоторому Actionable
public interface ActionableEvent extends Event {
	String getObjectId();
}
