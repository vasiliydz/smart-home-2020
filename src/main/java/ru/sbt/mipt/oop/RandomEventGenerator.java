package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.events.DoorCloseEvent;
import ru.sbt.mipt.oop.smarthome.events.DoorOpenEvent;
import ru.sbt.mipt.oop.smarthome.events.LightOffEvent;
import ru.sbt.mipt.oop.smarthome.events.LightOnEvent;
import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventStream;

public class RandomEventGenerator implements EventStream {
	private final double capacity;

	private enum PossibleEventType {
		LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSE
	}

	public RandomEventGenerator(double capacity) {
		this.capacity = capacity;
	}

	@Override
	public Event getNextEvent() {
		// pretend like we're getting the events from physical world, but here we're going to just generate some random events
		if (Math.random() < 1. - capacity) return null; // null means end of event stream
		PossibleEventType[] possibleEventTypes = PossibleEventType.values();
		int numOfEventTypes = possibleEventTypes.length;
		// choose random event type
		PossibleEventType eventType = possibleEventTypes[(int) (numOfEventTypes * Math.random())];
		String objectId = "" + ((int) (10 * Math.random()));
		// event to return
		Event event;
		switch (eventType) {
			case LIGHT_ON:
				return new LightOnEvent(objectId);
			case LIGHT_OFF:
				return new LightOffEvent(objectId);
			case DOOR_OPEN:
				return new DoorOpenEvent(objectId);
			case DOOR_CLOSE:
				return new DoorCloseEvent(objectId);
			default:
				throw new RuntimeException("Wrong event type chosen");
		}
	}
}
