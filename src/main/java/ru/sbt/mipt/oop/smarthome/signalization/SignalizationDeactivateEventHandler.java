package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;

public class SignalizationDeactivateEventHandler implements EventHandler {
	private final Signalization signalization;

	public SignalizationDeactivateEventHandler(Signalization signalization) {
		this.signalization = signalization;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof SignalizationDeactivateEvent) {
			String code = ((SignalizationDeactivateEvent) event).getCode();
			signalization.deactivate(code);
		}
	}
}
