package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;

public class SignalizationDeactivateEvent implements Event {
	private final String code;

	public SignalizationDeactivateEvent(String code) {
		this.code = code;
	}

	String getCode() {
		return code;
	}
}
