package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapter.coolcompany.EventHandlerAdapter;
import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.signalization.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		return new HallDoorEventHandler(smartHome);
	}

	@Bean
	EventHandlerAdapter eventHandlerAdapter(Collection<EventHandler> eventHandlers) {
		return new EventHandlerAdapter(new CompositeEventHandler(eventHandlers));
	}

	@Bean
	SensorEventsManager sensorEventsManager(EventHandlerAdapter eventHandlerAdapter) {
		SensorEventsManager ret = new SensorEventsManager();
		ret.registerEventHandler(eventHandlerAdapter);
		return ret;
	}
}
