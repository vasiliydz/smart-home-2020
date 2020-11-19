package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorOpenAction;
import ru.sbt.mipt.oop.smarthome.events.DoorOpenEvent;

public class DoorOpenEventHandler implements EventHandler {
	private final SmartHome home;

	public DoorOpenEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof DoorOpenEvent) {
			DoorOpenEvent doorOpenEvent = (DoorOpenEvent) event;
			Action action = new DoorOpenAction(doorOpenEvent.getObjectId());
			home.execute(action);
		}
	}
}