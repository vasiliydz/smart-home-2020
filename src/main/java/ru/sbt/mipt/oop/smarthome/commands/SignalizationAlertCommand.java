package ru.sbt.mipt.oop.smarthome.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.signalization.SignalizationAlertAction;

public class SignalizationAlertCommand implements SensorCommand {
	private final SmartHome home;

	public SignalizationAlertCommand(SmartHome home) {
		this.home = home;
	}

	@Override
	public void execute() {
		home.execute(new SignalizationAlertAction());
	}
}
