package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.MessageSender;
import ru.sbt.mipt.oop.smarthome.devices.signalization.Signalization;
import ru.sbt.mipt.oop.smarthome.devices.signalization.SignalizationAlertAction;

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
			signalization.execute(new SignalizationAlertAction());
			messageSender.send("Alert! " + event.getClass().getName() + " happened");
		} else if (signalization.isAlerted()) {
			messageSender.send(event.getClass().getName() + " happened");
		}
	}
}
