package ru.sbt.mipt.oop.smarthome;

public class DoorOpenAction implements Action {
	private final String objectId;

	public DoorOpenAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void actToComponent(HomeComponent component) {
		if (component instanceof Door) {
			if (component.getId().equals(objectId)) {
				((Door) component).setOpen(true);
				System.out.println("Door " + objectId + " was opened.");
			}
		}
	}
}
