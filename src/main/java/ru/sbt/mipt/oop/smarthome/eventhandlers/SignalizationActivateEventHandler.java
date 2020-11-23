package ru.sbt.mipt.oop.smarthome.eventhandlers;

import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;
import ru.sbt.mipt.oop.smarthome.events.SignalizationActivateEvent;
import ru.sbt.mipt.oop.smarthome.devices.signalization.Signalization;
import ru.sbt.mipt.oop.smarthome.devices.signalization.SignalizationActivateAction;

public class SignalizationActivateEventHandler implements EventHandler {
	private final Signalization signalization;

	public SignalizationActivateEventHandler(Signalization signalization) {
		this.signalization = signalization;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof SignalizationActivateEvent) {
			String code = ((SignalizationActivateEvent) event).getCode();
			signalization.execute(new SignalizationActivateAction(code));
		}
	}
}
