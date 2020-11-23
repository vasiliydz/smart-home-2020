package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorUnlockedAction;
import ru.sbt.mipt.oop.smarthome.events.DoorUnlockedEvent;

public class DoorUnlockedEventHandler implements EventHandler {
	private final SmartHome home;

	public DoorUnlockedEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof DoorUnlockedEvent) {
			DoorUnlockedEvent doorUnlockedEvent = (DoorUnlockedEvent) event;
			Action action = new DoorUnlockedAction(doorUnlockedEvent.getObjectId());
			home.execute(action);
		}
	}
}