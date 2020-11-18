package ru.sbt.mipt.oop.smarthome;

public class LightOffAction implements Action {
	private final String objectId;

	public LightOffAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Light) {
			if (actionable.getId().equals(objectId)) {
				((Light) actionable).setOn(false);
				System.out.println("Light " + objectId + " was turned off.");
			}
		}
	}
}
