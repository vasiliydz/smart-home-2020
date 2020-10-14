package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;

public class StopAlertEvent implements Event {
	private final String code;

	public StopAlertEvent(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
