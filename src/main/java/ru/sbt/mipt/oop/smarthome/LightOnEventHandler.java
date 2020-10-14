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
			LightOnEvent lightOnEvent = (LightOnEvent) event;
			Action action = new LightOnAction(lightOnEvent.getObjectId());
			home.execute(action);
		}
	}
}