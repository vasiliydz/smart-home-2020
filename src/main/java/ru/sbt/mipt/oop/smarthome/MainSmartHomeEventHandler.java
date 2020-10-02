package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

import java.util.ArrayList;

public class MainSmartHomeEventHandler implements EventHandler {
	private final SmartHome home;

	public MainSmartHomeEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		Action action = event.generateAction();
		home.applyAction(action, new ArrayList<>());
	}
}
