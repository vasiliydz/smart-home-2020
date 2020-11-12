package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.signalization.Signalization;
import ru.sbt.mipt.oop.smarthome.signalization.SignalizationActivateEventHandler;
import ru.sbt.mipt.oop.smarthome.signalization.SignalizationDeactivateEventHandler;
import ru.sbt.mipt.oop.smarthome.signalization.SignalizationEventHandlerMessageDecorator;

import java.util.ArrayList;
import java.util.List;

public class EventHandlerBuilder {
	private final SmartHome smartHome;
	private boolean hallDoorScenarioOn;
	private boolean signalizationOn;
	private Signalization signalization;
	private List<MessageSender> senders;

	public EventHandlerBuilder(SmartHome smartHome) {
		this.smartHome = smartHome;
		hallDoorScenarioOn = false;
		signalizationOn = false;
		senders = new ArrayList<>();
	}

	public EventHandlerBuilder addHallDoorScenario() {
		hallDoorScenarioOn = true;
		return this;
	}

	public EventHandlerBuilder addSignalization(Signalization signalization) {
		signalizationOn = true;
		this.signalization = signalization;
		return this;
	}

	public EventHandlerBuilder addMessageSender(MessageSender sender) {
		senders.add(sender);
		return this;
	}

	public EventHandler build() {
		// основные обработчики
		List<EventHandler> handlers = new ArrayList<>();
		handlers.add(new LightOffEventHandler(smartHome));
		handlers.add(new LightOnEventHandler(smartHome));
		handlers.add(new DoorCloseEventHandler(smartHome));
		handlers.add(new DoorOpenEventHandler(smartHome));
		if (hallDoorScenarioOn) {
			handlers.add(new HallDoorEventHandler(smartHome));
		}
		EventHandler handler = new ChainEventHandler(handlers);
		// обработчики-обёртки
		if (signalizationOn) {
			List<EventHandler> signalizationHandlers = new ArrayList<>();
			signalizationHandlers.add(new SignalizationActivateEventHandler(signalization));
			signalizationHandlers.add(new SignalizationDeactivateEventHandler(signalization));
			handler = new SignalizationEventHandlerMessageDecorator
					(handler, signalization, new ListMessageSender(senders));
		}
		return handler;
	}

}
