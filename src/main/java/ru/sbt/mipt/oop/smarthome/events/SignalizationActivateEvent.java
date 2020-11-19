package ru.sbt.mipt.oop.smarthome.events;

import ru.sbt.mipt.oop.smarthome.Event;

public class SignalizationActivateEvent implements Event {
	private final String code;

	public SignalizationActivateEvent(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
