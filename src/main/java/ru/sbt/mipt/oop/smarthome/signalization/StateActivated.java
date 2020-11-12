package ru.sbt.mipt.oop.smarthome.signalization;

class StateActivated implements SignalizationState {
	private final Signalization signalization;
	private final String code;

	StateActivated(Signalization signalization, String code) {
		this.signalization = signalization;
		this.code = code;
	}

	@Override
	public void activateSignalization(String code) {
		// nothing
	}

	@Override
	public void deactivateSignalization(String code) {
		if (this.code.equals(code)) {
			signalization.changeState(new StateDeactivated(signalization));
		} else {
			alertSignalization();
		}
	}

	@Override
	public void alertSignalization() {
		signalization.changeState(new StateAlert(signalization, code));
	}
}
