package ru.sbt.mipt.oop.smarthome.events;

import ru.sbt.mipt.oop.smarthome.Event;

public class SignalizationDeactivateEvent implements Event {
	private final String code;

	public SignalizationDeactivateEvent(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
