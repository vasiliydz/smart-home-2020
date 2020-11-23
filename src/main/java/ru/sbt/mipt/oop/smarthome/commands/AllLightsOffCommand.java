package ru.sbt.mipt.oop.smarthome.commands;

import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;

public class AllLightsOffCommand implements SensorCommand {
	private final Actionable actionable;

	public AllLightsOffCommand(Actionable actionable) {
		this.actionable = actionable;
	}

	@Override
	public void execute() {
		actionable.execute(innerActionable -> {
			if (innerActionable instanceof Light) {
				Light light = ((Light) innerActionable);
				new LightOffCommand(light).execute();
			}
		});
	}
}
