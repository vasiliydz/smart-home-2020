package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

import java.util.ArrayList;

public class LightOffEventHandler implements EventHandler {
	private final SmartHome home;

	public LightOffEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof LightOffEvent) {
			Action action = new LightOffAction(event.getObjectId());
			home.applyAction(action, new ArrayList<>());
		}
	}
}
