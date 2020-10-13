package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

public class DoorOpenEventHandler implements EventHandler {
	private final SmartHome home;

	public DoorOpenEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof DoorOpenEvent) {
			Action action = new DoorOpenAction(event.getObjectId());
			home.execute(action);
		}
	}
}