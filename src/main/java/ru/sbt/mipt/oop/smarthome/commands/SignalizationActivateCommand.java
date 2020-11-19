package ru.sbt.mipt.oop.smarthome.commands;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.devices.signalization.SignalizationActivateAction;

public class SignalizationActivateCommand implements SensorCommand {
	private final SmartHome home;
	private final String code;

	public SignalizationActivateCommand(SmartHome home, String code) {
		this.home = home;
		this.code = code;
	}

	@Override
	public void send() {
		home.execute(new SignalizationActivateAction(code));
	}
}
