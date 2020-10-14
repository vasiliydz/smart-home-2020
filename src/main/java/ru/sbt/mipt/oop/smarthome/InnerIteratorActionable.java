package ru.sbt.mipt.oop.smarthome;

public interface InnerIteratorActionable extends Actionable {
	@Override
	default void execute(Action action) {
		action.apply(this);
		executeToInnerActionables(action);
	}

	void executeToInnerActionables(Action action);
}
