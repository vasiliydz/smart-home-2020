package ru.sbt.mipt.oop.smarthome.commands;

import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Room;

public class AllLightsInHallOnCommand implements SensorCommand {
	private final Actionable actionable;

	public AllLightsInHallOnCommand(Actionable actionable) {
		this.actionable = actionable;
	}

	@Override
	public void send() {
		actionable.execute(innerActionable -> {
			if (innerActionable instanceof Room) {
				Room room = (Room) innerActionable;
				if (room.getName().equals("hall")) {
					new AllLightsOnCommand(room).send();
				}
			}
		});
	}

}
