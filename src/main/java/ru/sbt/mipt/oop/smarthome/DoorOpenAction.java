package ru.sbt.mipt.oop.smarthome;

public class DoorOpenAction implements Action {
	private final String objectId;

	public DoorOpenAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Door) {
			if (actionable.getId().equals(objectId)) {
				((Door) actionable).setOpen(true);
				System.out.println("Door " + objectId + " was opened.");
			}
		}
	}
}
