package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;

public class StateAlert implements SignalizationState {
	private final SignalizationEventHandler signalization;
	private final String code;

	StateAlert(SignalizationEventHandler signalization, String code) {
		this.signalization = signalization;
		this.code = code;
	}

	@Override
	public SignalizationState handle(Event event) {
		// если это событие выключения сигнализации
		if (event instanceof StopAlertEvent) {
			return handleStopEvent((StopAlertEvent) event);
		}
		// в противном случае
		return sendAlertMessage(createAlertMessage(event));
	}

	private SignalizationState handleStopEvent(StopAlertEvent event) {
		// если код совпал, то деактивируем
		if (event.getCode().equals(code)) {
			return new StateDeactivated(signalization);
		}
		//в противном случае шлём сообщение
		return sendAlertMessage("Wrong code");
	}

	private SignalizationState sendAlertMessage(String message) {
		signalization.sendMessage(message);
		return this;
	}

	private String createAlertMessage(Event event) {
		return event.getClass().getName() + " happened";
	}
}
