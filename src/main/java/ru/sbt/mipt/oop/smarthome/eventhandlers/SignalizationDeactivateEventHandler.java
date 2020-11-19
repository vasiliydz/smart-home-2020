package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.events.SignalizationDeactivateEvent;
import ru.sbt.mipt.oop.smarthome.devices.signalization.Signalization;
import ru.sbt.mipt.oop.smarthome.devices.signalization.SignalizationDeactivateAction;

public class SignalizationDeactivateEventHandler implements EventHandler {
	private final Signalization signalization;

	public SignalizationDeactivateEventHandler(Signalization signalization) {
		this.signalization = signalization;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof SignalizationDeactivateEvent) {
			String code = ((SignalizationDeactivateEvent) event).getCode();
			signalization.execute(new SignalizationDeactivateAction(code));
		}
	}
}
