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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
	EventHandler lightOffEventHandler(SmartHome smartHome) {
		return new LightOffEventHandler(smartHome);
	}

	@Bean
	EventHandler lightOnEventHandler(SmartHome smartHome) {
		return new LightOnEventHandler(smartHome);
	}

	@Bean
	EventHandler doorCloseEventHandler(SmartHome smartHome) {
		return new DoorCloseEventHandler(smartHome);
	}

	@Bean
	EventHandler doorOpenEventHandler(SmartHome smartHome) {
		return new DoorOpenEventHandler(smartHome);
	}

	@Bean
	EventHandler doorLockedEventHandler(SmartHome smartHome) {
		return new DoorLockedEventHandler(smartHome);
	}

	@Bean
	EventHandler doorUnlockedEventHandler(SmartHome smartHome) {
		return new DoorUnlockedEventHandler(smartHome);
	}

	@Bean
	EventHandler hallDoorEventHandler(SmartHome smartHome){
		return new EntranceDoorScenarioEventHandler(smartHome);
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
			Collection<EventHandler> eventHandlers, Map<String, EventFactory> eventFactories) {
		return new EventHandlerAdapter(new CompositeEventHandler(eventHandlers), eventFactories);
	}

	@Bean
	SensorEventsManager sensorEventsManager(com.coolcompany.smarthome.events.EventHandler eventHandler) {
		SensorEventsManager ret = new SensorEventsManager();
		ret.registerEventHandler(eventHandler);
		return ret;
	}
}
