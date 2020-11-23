package ru.sbt.mipt.oop.smarthome.commands;

import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.devices.door.EntranceDoor;

public class EntranceDoorCloseCommand implements SensorCommand {
	private final Actionable actionable;

	public EntranceDoorCloseCommand(Actionable actionable) {
		this.actionable = actionable;
	}

	@Override
	public void execute() {
		actionable.execute(innerActionable -> {
			if (innerActionable instanceof EntranceDoor) {
				new DoorCloseCommand((EntranceDoor) innerActionable).execute();
			}
		});
	}
}
