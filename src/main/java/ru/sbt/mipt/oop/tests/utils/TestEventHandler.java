package ru.sbt.mipt.oop.tests.utils;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;

public class TestEventHandler implements EventHandler {
	private final Actionable actionable;

	public TestEventHandler(Actionable actionable) {
		this.actionable = actionable;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof TestEventActedToAll) {
			actionable.execute(new TestActionToAll());
		}
	}
}
