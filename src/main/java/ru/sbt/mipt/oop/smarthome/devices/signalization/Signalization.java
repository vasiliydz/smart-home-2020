package ru.sbt.mipt.oop.smarthome.devices.signalization;

import ru.sbt.mipt.oop.smarthome.Actionable;

public class Signalization implements Actionable {
	private SignalizationState state;

	public Signalization() {
		state = new StateDeactivated(this);
	}

	public boolean isDeactivated() {
		return state instanceof StateDeactivated;
	}

	public boolean isActivated() {
		return state instanceof StateActivated;
	}

	public boolean isAlerted() {
		return state instanceof StateAlert;
	}


	void changeState(SignalizationState newState) {
		state = newState;
	}

	void activate(String code) {
		state.activateSignalization(code);
	}

	void deactivate(String code) {
		state.deactivateSignalization(code);
	}

	void alert() {
		state.alertSignalization();
	}

}
