package ru.sbt.mipt.oop.smarthome;

public class LightOffSensorCommand implements SensorCommand {

	private final Light light;

	public LightOffSensorCommand(Light light) {
		this.light = light;
	}

	@Override
	public void send() {
		// посылаем команду в реальный мир
		System.out.println("Sent command: light " + light.getId() + " off");
	}
}
