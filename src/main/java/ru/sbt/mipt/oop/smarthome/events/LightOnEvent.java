package ru.sbt.mipt.oop.smarthome.events;

import ru.sbt.mipt.oop.smarthome.ActionableEvent;

public class LightOnEvent implements ActionableEvent {
	private final String objectId;

	public LightOnEvent(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectId() {
		return objectId;
	}
}
