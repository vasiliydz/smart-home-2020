package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

public class LightOnEventHandler implements EventHandler {
	private final SmartHome home;

	public LightOnEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof LightOnEvent) {
			Action action = new LightOnAction(event.getObjectId());
			home.execute(action);
		}
	}
}