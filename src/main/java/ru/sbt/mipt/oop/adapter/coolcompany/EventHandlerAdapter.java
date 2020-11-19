package ru.sbt.mipt.oop.adapter.coolcompany;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.smarthome.*;

import java.util.Map;

/**
 * Adapts my EventHandler interface to CoolCompany library EventHandler
 */
public class EventHandlerAdapter implements EventHandler {
	private final ru.sbt.mipt.oop.smarthome.EventHandler adapteeEventHandler;
	private final Map<String, EventFactory> eventFactories;

	public EventHandlerAdapter(ru.sbt.mipt.oop.smarthome.EventHandler adapteeEventHandler,
							   Map<String, EventFactory> eventFactories) {
		this.adapteeEventHandler = adapteeEventHandler;
		this.eventFactories = eventFactories;
	}


	@Override
	public void handleEvent(CCSensorEvent ccEvent) {
		String eventType = ccEvent.getEventType();
		String objectId = ccEvent.getObjectId();
		if (!eventFactories.containsKey(eventType)) {
			System.out.println("Adapter does not support " + eventType + " event. Ignoring it");
		} else {
			Event event = eventFactories.get(eventType).createEvent(objectId);
			adapteeEventHandler.handleEvent(event);
		}
	}

}
