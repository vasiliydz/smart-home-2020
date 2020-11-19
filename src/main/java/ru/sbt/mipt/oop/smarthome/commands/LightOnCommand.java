package ru.sbt.mipt.oop.smarthome.commands;

import ru.sbt.mipt.oop.smarthome.devices.light.Light;
import ru.sbt.mipt.oop.smarthome.devices.light.LightOnAction;

public class LightOnCommand implements SensorCommand {
	private final Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void send() {
		// посылаем команду в реальный мир
		System.out.println("Sent command: light " + light.getId() + " on");
		// и меняем состояние дома (по идее должен приходить Event и обрабатываться,
		// но у нас нету реального умного дмоа, поэтому оставлю так)
		light.execute(new LightOnAction(light.getId()));
	}
}
