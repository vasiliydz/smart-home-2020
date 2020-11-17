package ru.sbt.mipt.oop.smarthome;

public class DoorUnlockedAction implements Action {
		private final String objectId;

		public DoorUnlockedAction(String objectId) {
			this.objectId = objectId;
		}

		@Override
		public void apply(Actionable actionable) {
			if (actionable instanceof Door) {
				if (actionable.getId().equals(objectId)) {
					((Door) actionable).setLocked(false);
					System.out.println("Door " + objectId + " was unlocked.");
				}
			}
		}
	}

