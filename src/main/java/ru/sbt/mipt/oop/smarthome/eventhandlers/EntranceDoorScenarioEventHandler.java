package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.commands.AllLightsOffCommand;
import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.devices.door.EntranceDoor;
import ru.sbt.mipt.oop.smarthome.events.DoorCloseEvent;

/**
 * Scenario of switching off all lights when EntranceDoor is closed
 */
public class EntranceDoorScenarioEventHandler implements EventHandler {
	private final SmartHome home;

	public EntranceDoorScenarioEventHandler(SmartHome home) {
		this.home = home;
	}

	@Override
	public void handleEvent(Event event) {
		if (!(event instanceof DoorCloseEvent)) { // срабатывает только если дверь закрывается
			return;
		}
		DoorCloseEvent doorCloseEvent = (DoorCloseEvent) event;
		// ищем в доме входную дверь с заданным id
		EntranceDoorFinder doorFinder = new EntranceDoorFinder(doorCloseEvent.getObjectId());
		home.execute(doorFinder);
		if (doorFinder.found()) { // если есть такая дверь
			new AllLightsOffCommand(home).execute(); // то выключаем свет в доме
		}
	}

	private static class EntranceDoorFinder implements Action {
		private final String doorId;
		private boolean found;

		public EntranceDoorFinder(String doorId) {
			this.doorId = doorId;
			found = false;
		}

		@Override
		public void apply(Actionable actionable) {
			if (actionable instanceof EntranceDoor) {
				EntranceDoor door = (EntranceDoor) actionable;
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
