package ru.sbt.mipt.oop.smarthome.devices.signalization;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;

public class SignalizationAlertAction implements Action {
	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof Signalization) {
			((Signalization) actionable).alert();
		}
	}
}
