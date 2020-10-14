package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

public class LightOffEventHandler implements EventHandler {
	private final SmartHome home;

	public LightOffEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof LightOffEvent) {
			LightOffEvent lightOffEvent = (LightOffEvent) event;
			Action action = new LightOffAction(lightOffEvent.getObjectId());
			home.execute(action);
		}
	}
}