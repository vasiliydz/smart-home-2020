package ru.sbt.mipt.oop.smarthome;

public class DoorLockedAction implements Action {
	private final String objectId;

	public DoorLockedAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Door) {
			if (actionable.getId().equals(objectId)) {
				((Door) actionable).setLocked(true);
				System.out.println("Door " + objectId + " was locked.");
			}
		}
	}
}
