package ru.sbt.mipt.oop.smarthome;

public interface InnerIteratorActionable extends Actionable {
	default void execute(Action action) {
		action.apply(this);
		applyToInnerComponents(action);
	}

	void applyToInnerComponents(Action action);
}
