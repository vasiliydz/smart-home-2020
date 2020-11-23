package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompositeEventHandler implements EventHandler {
	private final List<EventHandler> handlerList;

	public CompositeEventHandler(Collection<EventHandler> handlerList) {
		this.handlerList = new ArrayList<>(handlerList);
	}

	@Override
	public void handleEvent(Event event) {
		for (EventHandler handler : handlerList) {
			handler.handleEvent(event);
		}
	}
}
