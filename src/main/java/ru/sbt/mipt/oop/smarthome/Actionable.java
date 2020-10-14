package ru.sbt.mipt.oop.smarthome;

public interface Actionable {
	String getId();
	default void execute(Action action) {
		action.apply(this);
	}
}
