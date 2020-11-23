package ru.sbt.mipt.oop.smarthome.events;

import ru.sbt.mipt.oop.smarthome.ActionableEvent;

public class DoorOpenEvent implements ActionableEvent {
	private final String objectId;

	public DoorOpenEvent(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectId() {
		return objectId;
	}
}
