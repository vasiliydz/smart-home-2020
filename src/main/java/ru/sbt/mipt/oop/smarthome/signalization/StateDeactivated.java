package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;

public class StateDeactivated implements SignalizationState {
	private final SignalizationEventHandler signalization;

	StateDeactivated(SignalizationEventHandler signalization) {
		this.signalization = signalization;
	}

	@Override
	public SignalizationState handle(Event event) {
		// если это не событие активации сигнализации, то отдаём событие внутреннему обработчику
		if (!(event instanceof SignalizationActivateEvent)) {
			return handleOtherEvent(event);
		}
		// а в противном случае переводим сигнализацию в активированное состояние
		return handleActivateEvent((SignalizationActivateEvent) event);
	}

	private SignalizationState handleActivateEvent(SignalizationActivateEvent event) {
		String code = event.getCode();
		return new StateActivated(signalization, code);
	}

	private SignalizationState handleOtherEvent(Event event) {
		signalization.delegateToBasicHandler(event);
		return this;
	}
}
