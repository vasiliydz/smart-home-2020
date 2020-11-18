package ru.sbt.mipt.oop.smarthome.signalization;

class StateAlert implements SignalizationState {
	private final Signalization signalization;
	private final String code;

	StateAlert(Signalization signalization, String code) {
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
		}
	}

	@Override
	public void alertSignalization() {
		// nothing
	}
}
