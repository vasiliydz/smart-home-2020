package ru.sbt.mipt.oop.smarthome;

public interface Actionable {
	default void execute(Action action) {
		action.apply(this);
	}
}
