package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

public class DoorLockedEventHandler implements EventHandler {
	private final SmartHome home;

	public DoorLockedEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof DoorLockedEvent) {
			DoorLockedEvent doorLockedEvent = (DoorLockedEvent) event;
			Action action = new DoorLockedAction(doorLockedEvent.getObjectId());
			home.execute(action);
		}
	}
}