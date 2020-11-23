package ru.sbt.mipt.oop.smarthome.devices.door;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;

public class DoorLockedAction implements Action {
	private final String objectId;

	public DoorLockedAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Door) {
			if (((Door) actionable).getId().equals(objectId)) {
				((Door) actionable).setLocked(true);
				System.out.println("Door " + objectId + " was locked.");
			}
		}
	}
}
