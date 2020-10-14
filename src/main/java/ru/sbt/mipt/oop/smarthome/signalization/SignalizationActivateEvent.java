package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;

public class SignalizationActivateEvent implements Event {
	private final String code;

	public SignalizationActivateEvent(String code) {
		this.code = code;
	}

	String getCode() {
		return code;
	}
}
