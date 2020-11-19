package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Light;

public class AllLightsOffSensorCommand implements SensorCommand {
	private final Actionable actionable;

	public AllLightsOffSensorCommand(Actionable actionable) {
		this.actionable = actionable;
	}

	@Override
	public void send() {
		actionable.execute(innerActionable -> {
			if (innerActionable instanceof Light) {
				Light light = ((Light) innerActionable);
				new LightOffSensorCommand(light).send();
			}
		});
	}
}
