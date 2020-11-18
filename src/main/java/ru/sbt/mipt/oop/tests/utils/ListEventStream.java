package ru.sbt.mipt.oop.tests.utils;

import ru.sbt.mipt.oop.smarthome.Event;
import ru.sbt.mipt.oop.smarthome.EventStream;

import java.util.ArrayList;
import java.util.List;

public class ListEventStream implements EventStream {
	private final List<Event> eventList;
	private int currentIndex;

	public ListEventStream(List<Event> eventList) {
		this.eventList = new ArrayList<>(eventList);
		currentIndex = 0;
	}

	@Override
	public Event getNextEvent() {
		if (currentIndex < eventList.size()) {
			currentIndex++;
			return eventList.get(currentIndex - 1);
		}
		return null;
	}
}
