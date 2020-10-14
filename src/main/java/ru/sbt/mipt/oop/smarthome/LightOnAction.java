package ru.sbt.mipt.oop.smarthome;

public class LightOnAction implements Action {
	private final String objectId;

	public LightOnAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Light) {
			if (actionable.getId().equals(objectId)) {
				((Light) actionable).setOn(true);
				System.out.println("Light " + objectId + " was turned on.");
			}
		}
	}
}
