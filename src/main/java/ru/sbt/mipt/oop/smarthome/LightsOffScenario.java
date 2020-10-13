package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class LightsOffScenario implements EventHandler {
	private final SmartHome home;

	public LightsOffScenario(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (!(event instanceof DoorCloseEvent)) { // срабатывает только если дверь закрывается
			return;
		}
		if (isDoorInHall(event.getObjectId())) {
			home.applyAction(new AllLightsOffAction(), new ArrayList<>());
		}
	}

	private boolean isDoorInHall(String doorId) {
		DoorFinder doorFinder = new DoorFinder(doorId);
		home.applyAction(doorFinder, new ArrayList<>());
		return doorFinder.getFound();
	}

	private static class DoorFinder implements Action {
		private final String doorId;
		private boolean found;

		public DoorFinder(String doorId) {
			this.doorId = doorId;
			this.found = false;
		}

		@Override
		public void actToComponent(HomeComponent component, List<Actionable> parents) {
			if (!(component instanceof Door)) { // не рассматриваем, если это не дверь
				return;
			}
			if (!(component.getId().equals(doorId))) { // и если это не та дверь
				return;
			}
			for (Actionable actionable : parents) { // проверяем, есть ли комната с названием hall
				if (isHall(actionable)) {
					found = true;
					return;
				}
			}
		}

		public boolean getFound() {
			return found;
		}

		private boolean isHall(Actionable actionable) {
			if (!(actionable instanceof Room)) { // если не комната, то не холл
				return false;
			}
			Room room = (Room) actionable;
			return room.getName().equals("hall");
		}
	}
}
