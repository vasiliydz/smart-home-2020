package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.HomeBuilder;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.adapter.coolcompany.EventHandlerAdapter;
import ru.sbt.mipt.oop.adapter.coolcompany.EventFactory;
import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.commands.*;
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

	@Bean
	Map<String, SensorCommand> remoteControlCommands(SmartHome smartHome) {
		Map<String, SensorCommand> ret = new HashMap<>();
		ret.put("A", new AllLightsOffCommand(smartHome));
		ret.put("B", new EntranceDoorCloseCommand(smartHome));
		ret.put("C", new AllLightsInHallOnCommand(smartHome));
		ret.put("D", new SignalizationActivateCommand(smartHome, "12345678"));
		ret.put("1", new SignalizationAlertCommand(smartHome));
		ret.put("2", new AllLightsOnCommand(smartHome));
		return ret;
	}

	@Bean
	Map<String, RemoteControl> remoteControls(Map<String, SensorCommand> commands) {
		Map<String, RemoteControl> ret = new HashMap<>();
		String rcId = "1";
		ret.put(rcId, new ProgrammableRemoteControl(rcId, commands));
		return ret;
	}

	@Bean
	RemoteControlRegistry remoteControlRegistry(Map<String, RemoteControl> remoteControls) {
		RemoteControlRegistry rcRegistry = new RemoteControlRegistry();
		for (String id : remoteControls.keySet()) {
			rcRegistry.registerRemoteControl(remoteControls.get(id), id);
		}
		return rcRegistry;
	}


}
