package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.AllLightsOffSensorCommand;
import ru.sbt.mipt.oop.commands.LightOffSensorCommand;

public class HallDoorEventHandler implements EventHandler {
	private final SmartHome home;

	public HallDoorEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (!(event instanceof DoorCloseEvent)) { // срабатывает только если дверь закрывается
			return;
		}
		DoorCloseEvent doorCloseEvent = (DoorCloseEvent) event;
		// ищем холл
		HallFinder hallFinder = new HallFinder();
		home.execute(hallFinder);
		if (!hallFinder.found()) {
			return;
		}
		Room hall = hallFinder.getHall();
		// ищем в холле дверь
		DoorFinder doorFinder = new DoorFinder(doorCloseEvent.getObjectId());
		hall.execute(doorFinder);
		if (doorFinder.found()) { // если эта дверь находится в холле
			// то вырубаем везде свет
			new AllLightsOffSensorCommand(home).send();
		}
	}

	private static class HallFinder implements Action {
		private Room hall;

		HallFinder() {
			hall = null;
		}

		@Override
		public void apply(Actionable actionable) {
			if (actionable instanceof Room) {
				Room room = (Room) actionable;
				if (room.getId().equals("hall")) {
					hall = room;
				}
			}
		}
		public boolean found() {
			return hall != null;
		}
		public Room getHall() {
			return hall;
		}
	}

	private static class DoorFinder implements Action {
		private final String doorId;
		private boolean found;

		public DoorFinder(String doorId) {
			this.doorId = doorId;
			found = false;
		}

		@Override
		public void apply(Actionable actionable) {
			if (actionable instanceof Door) {
				Door door = (Door) actionable;
				if (door.getId().equals(doorId)) {
					found = true;
				}
			}
		}

		public boolean found() {
			return found;
		}
	}
}
