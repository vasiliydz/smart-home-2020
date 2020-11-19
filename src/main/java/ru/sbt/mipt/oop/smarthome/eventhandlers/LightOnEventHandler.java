package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.devices.light.LightOnAction;
import ru.sbt.mipt.oop.smarthome.events.LightOnEvent;

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