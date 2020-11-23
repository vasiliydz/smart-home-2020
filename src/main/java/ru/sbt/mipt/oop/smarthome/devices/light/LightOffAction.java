package ru.sbt.mipt.oop.smarthome.devices.light;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;

public class LightOffAction implements Action {
	private final String objectId;

	public LightOffAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Light) {
			if (((Light) actionable).getId().equals(objectId)) {
				((Light) actionable).setOn(false);
				System.out.println("Light " + objectId + " was turned off.");
			}
		}
	}
}
