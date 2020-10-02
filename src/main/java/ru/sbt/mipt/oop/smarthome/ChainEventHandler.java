package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.List;

public class ChainEventHandler implements EventHandler {
	private final List<EventHandler> handlerList;

	public ChainEventHandler(List<EventHandler> handlerList) {
		this.handlerList = new ArrayList<>(handlerList);
	}

	@Override
	public void handleEvent(Event event) {
		for (EventHandler handler : handlerList) {
			handler.handleEvent(event);
		}
	}
}
