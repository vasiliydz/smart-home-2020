package ru.sbt.mipt.oop.tests.utils;

import ru.sbt.mipt.oop.smarthome.commands.SensorCommand;

public class TestSensorCommand implements SensorCommand {
	private boolean executed;

	public TestSensorCommand() {
		executed = false;
	}

	public boolean isExecuted() {
		return executed;
	}

	@Override
	public void execute() {
		executed = true;
	}
}
