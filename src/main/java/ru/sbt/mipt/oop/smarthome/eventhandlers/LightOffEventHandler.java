package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.devices.light.LightOffAction;
import ru.sbt.mipt.oop.smarthome.events.LightOffEvent;

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