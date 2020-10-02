package ru.sbt.mipt.oop.smarthome;

public class LightOffEvent implements Event {
	private final String objectId;

	public LightOffEvent(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectId() {
		return objectId;
	}

	@Override
	public Action generateAction() {
		return new LightOffAction(objectId);
	}
}
