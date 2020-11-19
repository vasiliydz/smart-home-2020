package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorLockedAction;
import ru.sbt.mipt.oop.smarthome.events.DoorLockedEvent;

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