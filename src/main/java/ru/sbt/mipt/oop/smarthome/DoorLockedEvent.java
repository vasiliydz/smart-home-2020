package ru.sbt.mipt.oop.smarthome;

public class DoorLockedEvent implements ActionableEvent {
	private final String objectId;

	public DoorLockedEvent(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectId() {
		return objectId;
	}
}
