package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.MessageSender;

public class SignalizationEventHandler implements EventHandler {
	private final EventHandler basicHandler;
	private SignalizationState state;
	private final MessageSender messageSender;

	public SignalizationEventHandler(EventHandler basicHandler, MessageSender messageSender) {
		this.basicHandler = basicHandler;
		this.messageSender = messageSender;
		state = new StateDeactivated(this);
	}

	void delegateToBasicHandler(Event event) {
		basicHandler.handleEvent(event);
	}

	void sendMessage(String message) {
		messageSender.send(message);
	}

	@Override
	public void handleEvent(Event event) {
		// state обрабатывает событие и возвращает состояние сигнализации после обработки
		state = state.handle(event);
	}
}
