package ru.sbt.mipt.oop.smarthome;

public class DoorCloseAction implements Action {
	private final String objectId;

	public DoorCloseAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Door) {
			if (actionable.getId().equals(objectId)) {
				((Door) actionable).setOpen(false);
				System.out.println("Door " + objectId + " was closed.");
			}
		}
	}
}
