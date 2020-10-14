package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.signalization.SignalizationEventHandler;

import java.util.ArrayList;
import java.util.List;

public class EventHandlerBuilder {
	private final SmartHome smartHome;
	private boolean hallDoorScenarioOn;
	private boolean signalizationOn;
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

	public EventHandlerBuilder addSignalization() {
		signalizationOn = true;
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
			handler = new SignalizationEventHandler(handler, new ListMessageSender(senders));
		}
		return handler;
	}

}
