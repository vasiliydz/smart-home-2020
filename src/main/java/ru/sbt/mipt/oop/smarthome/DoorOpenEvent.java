package ru.sbt.mipt.oop.smarthome;

public class DoorOpenEvent implements Event {
	private final String objectId;

	public DoorOpenEvent(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectId() {
		return objectId;
	}
}
