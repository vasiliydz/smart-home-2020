package ru.sbt.mipt.oop.smarthome.commands;

import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.door.DoorCloseAction;

public class DoorCloseCommand implements SensorCommand {
	private final Door door;

	public DoorCloseCommand(Door door) {
		this.door = door;
	}

	@Override
	public void execute() {
		// посылаем команду в реальный мир
		System.out.println("Sent command: door " + door.getId() + " close");
		// и меняем состояние дома (по идее должен приходить Event и обрабатываться,
		// но у нас нету реального умного дмоа, поэтому оставлю так)
		door.execute(new DoorCloseAction(door.getId()));
	}
}
