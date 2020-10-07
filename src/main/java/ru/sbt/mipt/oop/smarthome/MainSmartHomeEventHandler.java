package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

public class MainSmartHomeEventHandler implements EventHandler {
	private final SmartHome home;

	public MainSmartHomeEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		Action action = event.generateAction();
		home.execute(action);
	}
}
