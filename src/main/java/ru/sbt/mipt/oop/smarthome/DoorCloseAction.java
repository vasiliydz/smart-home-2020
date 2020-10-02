package ru.sbt.mipt.oop.smarthome;

import java.util.List;

public class DoorCloseAction implements Action {
	private final String objectId;

	public DoorCloseAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void actToComponent(HomeComponent component, List<Actionable> parents) {
		if (component instanceof Door) {
			if (component.getId().equals(objectId)) {
				((Door) component).setOpen(false);
				System.out.println("Door " + objectId + " was closed.");
			}
		}
	}
}
