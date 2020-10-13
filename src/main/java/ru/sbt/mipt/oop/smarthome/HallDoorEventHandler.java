package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

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
		IsInHallChecker checker = new IsInHallChecker(event.getObjectId());
		home.execute(checker);
		if (checker.found()) { // если эта дверь находится в холле
			// то вырубаем везде свет
			home.execute(component -> {
				if (component instanceof Light) {
					Light light = (Light) component;
					light.setOn(false);
					new LightOffSensorCommand(light).send(); // и посылаем лампе команду в реальный мир
				}
			});
		}
	}

	private static class IsInHallChecker implements Action {
		private final String doorId;
		private boolean found;

		IsInHallChecker(String doorId) {
			this.doorId = doorId;
			found = false;
		}

		@Override
		public void apply(Actionable actionable) {
			if (actionable instanceof Room) {
				Room room = (Room) actionable;
				String roomId = room.getId();
				if (roomId.equals("hall") && room.containsComponent(doorId)) {
					found = true;
				}
			}
		}

		public boolean found() {
			return found;
		}
	}
}
