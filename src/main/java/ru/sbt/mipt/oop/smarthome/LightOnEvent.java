package ru.sbt.mipt.oop.smarthome;

public class LightOnEvent implements Event {
	private final String objectId;

	public LightOnEvent(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectId() {
		return objectId;
	}
}
