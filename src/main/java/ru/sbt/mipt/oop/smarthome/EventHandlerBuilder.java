package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class EventHandlerBuilder {
	private final SmartHome smartHome;
	private boolean hallDoorScenarioOn;

	public EventHandlerBuilder(SmartHome smartHome) {
		this.smartHome = smartHome;
		hallDoorScenarioOn = false;
	}

	public EventHandlerBuilder setHallDoorScenario(boolean isOn) {
		hallDoorScenarioOn = isOn;
		return this;
	}

	public EventHandler build() {
		List<EventHandler> handlers = new ArrayList<>();
		handlers.add(new LightOffEventHandler(smartHome));
		handlers.add(new LightOnEventHandler(smartHome));
		handlers.add(new DoorCloseEventHandler(smartHome));
		handlers.add(new DoorOpenEventHandler(smartHome));
		if (hallDoorScenarioOn) {
			handlers.add(new HallDoorEventHandler(smartHome));
		}
		return new ChainEventHandler(handlers);
	}

}
