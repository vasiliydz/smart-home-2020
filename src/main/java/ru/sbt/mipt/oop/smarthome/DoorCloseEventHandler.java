package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

public class DoorCloseEventHandler implements EventHandler {
	private final SmartHome home;

	public DoorCloseEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof DoorCloseEvent) {
			Action action = new DoorCloseAction(event.getObjectId());
			home.execute(action);
		}
	}
}