package ru.sbt.mipt.oop.adapter.coolcompany;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.adapter.coolcompany.eventfactories.EventFactory;
import ru.sbt.mipt.oop.smarthome.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapts my EventHandler interface to CoolCompany library EventHandler
 */
public class EventHandlerAdapter implements EventHandler {
	private final ru.sbt.mipt.oop.smarthome.EventHandler adapteeEventHandler;
	private final Map<String, EventFactory> eventFactories;

	public EventHandlerAdapter(ru.sbt.mipt.oop.smarthome.EventHandler adapteeEventHandler) {
		this.adapteeEventHandler = adapteeEventHandler;
		this.eventFactories = createEventFactories();
	}


	@Override
	public void handleEvent(CCSensorEvent ccEvent) {
		String eventType = ccEvent.getEventType();
		String objectId = ccEvent.getObjectId();
		Event event = eventFactories.get(eventType).createEvent(objectId);
		adapteeEventHandler.handleEvent(event);
	}

	private Map<String, EventFactory> createEventFactories() {
		Map<String, EventFactory> ret = new HashMap<>();
		ret.put("LightIsOn", LightOnEvent::new);
		ret.put("LightIsOff", LightOffEvent::new);
		ret.put("DoorIsOpen", DoorOpenEvent::new);
		ret.put("DoorIsClosed", DoorCloseEvent::new);
		ret.put("DoorIsLocked", DoorLockedEvent::new);
		ret.put("DoorIsUnlocked", DoorUnlockedEvent::new);
		return ret;
	}
}
