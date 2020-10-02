package ru.sbt.mipt.oop.smarthome;

import java.util.List;

public class DoorOpenAction implements Action {
	private final String objectId;

	public DoorOpenAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void actToComponent(HomeComponent component, List<Actionable> parents) {
		if (component instanceof Door) {
			if (component.getId().equals(objectId)) {
				((Door) component).setOpen(true);
				System.out.println("Door " + objectId + " was opened.");
			}
		}
	}
}
