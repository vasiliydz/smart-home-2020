package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapter.coolcompany.EventHandlerAdapter;
import ru.sbt.mipt.oop.adapter.coolcompany.EventFactory;
import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.eventhandlers.*;
import ru.sbt.mipt.oop.smarthome.events.LightOffEvent;
import ru.sbt.mipt.oop.smarthome.events.LightOnEvent;
import ru.sbt.mipt.oop.smarthome.events.DoorCloseEvent;
import ru.sbt.mipt.oop.smarthome.events.DoorLockedEvent;
import ru.sbt.mipt.oop.smarthome.events.DoorOpenEvent;
import ru.sbt.mipt.oop.smarthome.events.DoorUnlockedEvent;
import ru.sbt.mipt.oop.smarthome.devices.signalization.*;

import java.util.*;

@Configuration
public class ApplicationConfiguration {

	@Bean
	Signalization signalization() {
		return new Signalization();
	}

	@Bean
	SmartHome smartHome(Signalization signalization) {
		return HomeBuilder.buildHome(signalization);
	}

	@Bean
	MessageSender messageSender() {
		return new SmsSender();
	}

	@Bean
	EventHandler mainEventHandler(SmartHome smartHome, Signalization signalization, MessageSender sender) {
		// basic handlers
		EventHandler handler = new CompositeEventHandler(Arrays.asList(
			new LightOffEventHandler(smartHome),
			new LightOnEventHandler(smartHome),
			new DoorCloseEventHandler(smartHome),
			new DoorOpenEventHandler(smartHome),
			new DoorLockedEventHandler(smartHome),
			new DoorUnlockedEventHandler(smartHome),
			new EntranceDoorScenarioEventHandler(smartHome)
		));
		// signalization decorator
		handler = new SignalizationEventHandlerMessageDecorator(handler, signalization, sender);
		// signalization handlers
		handler = new CompositeEventHandler(Arrays.asList(
			new SignalizationActivateEventHandler(signalization),
			new SignalizationDeactivateEventHandler(signalization),
			handler
		));
		return handler;
	}

	@Bean
	Map<String, EventFactory> eventFactoryMap() {
		Map<String, EventFactory> ret = new HashMap<>();
		ret.put("LightIsOn", LightOnEvent::new);
		ret.put("LightIsOff", LightOffEvent::new);
		ret.put("DoorIsOpen", DoorOpenEvent::new);
		ret.put("DoorIsClosed", DoorCloseEvent::new);
		ret.put("DoorIsLocked", DoorLockedEvent::new);
		ret.put("DoorIsUnlocked", DoorUnlockedEvent::new);
		return ret;
	}

	@Bean
	com.coolcompany.smarthome.events.EventHandler eventHandlerAdapter(
			EventHandler adapteeEventHandler, Map<String, EventFactory> eventFactories) {
		return new EventHandlerAdapter(adapteeEventHandler, eventFactories);
	}

	@Bean
	SensorEventsManager sensorEventsManager(com.coolcompany.smarthome.events.EventHandler eventHandler) {
		SensorEventsManager ret = new SensorEventsManager();
		ret.registerEventHandler(eventHandler);
		return ret;
	}
}
