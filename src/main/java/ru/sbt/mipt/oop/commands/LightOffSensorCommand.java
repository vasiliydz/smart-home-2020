package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.smarthome.devices.light.Light;
import ru.sbt.mipt.oop.smarthome.devices.light.LightOffAction;

public class LightOffSensorCommand implements SensorCommand {

	private final Light light;

	public LightOffSensorCommand(Light light) {
		this.light = light;
	}

	@Override
	public void send() {
		// посылаем команду в реальный мир
		System.out.println("Sent command: light " + light.getId() + " off");
		// и меняем состояние дома (по идее должен приходить Event и обрабатываться,
		// но у нас нету реального умного дмоа, поэтому оставлю так)
		light.execute(new LightOffAction(light.getId()));
	}
}
