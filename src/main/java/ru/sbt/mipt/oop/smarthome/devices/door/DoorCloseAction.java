package ru.sbt.mipt.oop.smarthome.devices.door;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;

public class DoorCloseAction implements Action {
	private final String objectId;

	public DoorCloseAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Door) {
			if (((Door) actionable).getId().equals(objectId)) {
				((Door) actionable).setOpen(false);
				System.out.println("Door " + objectId + " was closed.");
			}
		}
	}
}
