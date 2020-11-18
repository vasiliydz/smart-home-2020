package ru.sbt.mipt.oop.smarthome;

public class DoorUnlockedEvent implements ActionableEvent {
	private final String objectId;

	public DoorUnlockedEvent(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectId() {
		return objectId;
	}
}
