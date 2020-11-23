package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorCloseAction;
import ru.sbt.mipt.oop.smarthome.events.DoorCloseEvent;

public class DoorCloseEventHandler implements EventHandler {
	private final SmartHome home;

	public DoorCloseEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof DoorCloseEvent) {
			DoorCloseEvent doorCloseEvent = (DoorCloseEvent) event;
			Action action = new DoorCloseAction(doorCloseEvent.getObjectId());
			home.execute(action);
		}
	}
}