package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.MessageSender;

public class SignalizationEventHandlerMessageDecorator implements EventHandler {
	private final EventHandler basicHandler;
	private Signalization signalization;
	private final MessageSender messageSender;

	public SignalizationEventHandlerMessageDecorator(EventHandler basicHandler, Signalization signalization,
													 MessageSender messageSender) {
		this.basicHandler = basicHandler;
		this.signalization = signalization;
		this.messageSender = messageSender;
	}

	@Override
	public void handleEvent(Event event) {
		if (signalization.isDeactivated()) {
			basicHandler.handleEvent(event);
		} else if (signalization.isActivated()) {
			signalization.alert();
			messageSender.send("Alert! " + event.getClass().getName() + " happened");
		} else if (signalization.isAlerted()) {
			messageSender.send(event.getClass().getName() + " happened");
		}
	}
}
