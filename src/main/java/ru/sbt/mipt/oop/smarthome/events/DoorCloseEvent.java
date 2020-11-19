package ru.sbt.mipt.oop.smarthome.events;

import ru.sbt.mipt.oop.smarthome.ActionableEvent;

public class DoorCloseEvent implements ActionableEvent {
	private final String objectId;

	public DoorCloseEvent(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectId() {
		return objectId;
	}
}
