package ru.sbt.mipt.oop.smarthome.devices.signalization;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;

public class SignalizationDeactivateAction implements Action {
	private final String code;

	public SignalizationDeactivateAction(String code) {
		this.code = code;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Signalization) {
			((Signalization) actionable).deactivate(code);
		}
	}
}
