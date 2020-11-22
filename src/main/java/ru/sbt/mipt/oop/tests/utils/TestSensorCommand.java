package ru.sbt.mipt.oop.tests.utils;

import ru.sbt.mipt.oop.smarthome.commands.SensorCommand;

public class TestSensorCommand implements SensorCommand {
	private boolean sent;

	public TestSensorCommand() {
		sent = false;
	}

	public boolean isSent() {
		return sent;
	}

	@Override
	public void send() {
		sent = true;
	}
}
