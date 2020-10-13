package ru.sbt.mipt.oop.smarthome;

public class DoorCloseEvent implements Event {
	private final String objectId;

	public DoorCloseEvent(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectId() {
		return objectId;
	}
}
