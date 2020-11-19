package ru.sbt.mipt.oop.adapter.coolcompany;

import ru.sbt.mipt.oop.smarthome.Event;

public interface EventFactory {
	Event createEvent(String objectId);
}
