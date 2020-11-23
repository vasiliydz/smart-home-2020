package ru.sbt.mipt.oop.smarthome.devices.signalization;

class StateDeactivated implements SignalizationState {
	private final Signalization signalization;

	StateDeactivated(Signalization signalization) {
		this.signalization = signalization;
	}

	@Override
	public void activateSignalization(String code) {
		signalization.changeState(new StateActivated(signalization, code));
	}

	@Override
	public void deactivateSignalization(String code) {
		// nothing
	}

	@Override
	public void alertSignalization() {
		// nothing
	}
}
