package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

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