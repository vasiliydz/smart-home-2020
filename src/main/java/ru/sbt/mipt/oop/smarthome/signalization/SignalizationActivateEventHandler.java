package ru.sbt.mipt.oop.smarthome.signalization;

import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventHandler;

public class SignalizationActivateEventHandler implements EventHandler {
	private final Signalization signalization;

	public SignalizationActivateEventHandler(Signalization signalization) {
		this.signalization = signalization;
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof SignalizationActivateEvent) {
			String code = ((SignalizationActivateEvent) event).getCode();
			signalization.activate(code);
		}
	}
}
