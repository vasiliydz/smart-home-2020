package ru.sbt.mipt.oop.smarthome.signalization;

public class Signalization {
	private SignalizationState state;

	public Signalization() {
		state = new StateDeactivated(this);
	}

	void changeState(SignalizationState newState) {
		state = newState;
	}

	public void activate(String code) {
		state.activateSignalization(code);
	}

	public void deactivate(String code) {
		state.deactivateSignalization(code);
	}

	public void alert() {
		state.alertSignalization();
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

}
