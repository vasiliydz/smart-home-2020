package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;

public class StateActivated implements SignalizationState {
	private final SignalizationEventHandler signalization;
	private final String code;

	StateActivated(SignalizationEventHandler signalization, String code) {
		this.signalization = signalization;
		this.code = code;
	}

	@Override
	public SignalizationState handle(Event event) {
		// если событие деактивирует сигнализацию
		if (event instanceof SignalizationDeactivateEvent) {
			return handleDeactivateEvent((SignalizationDeactivateEvent) event);
		}
		// при остальных событиях бьём тревогу
		return alert(createAlertMessage(event));
	}

	private SignalizationState handleDeactivateEvent(SignalizationDeactivateEvent event) {
		// если код совпал, то деактивируем
		if (event.getCode().equals(code)) {
			return new StateDeactivated(signalization);
		}
		//в противном случае бьём тревогу
		return alert("Alert! Wrong deactivation code");
	}

	private SignalizationState alert(String message) {
		signalization.sendMessage(message);
		return new StateAlert(signalization, code);
	}

	private String createAlertMessage(Event event) {
		return "Alert! " + event.getClass().getName() + " happened";
	}
}
