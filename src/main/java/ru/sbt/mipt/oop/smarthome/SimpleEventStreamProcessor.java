package ru.sbt.mipt.oop.smarthome;

public class SimpleEventStreamProcessor implements EventStreamProcessor {
	private EventHandler eventHandler;

	public SimpleEventStreamProcessor(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	@Override
	public void processEventStream(EventStream eventStream) {
		Event event = eventStream.getNextEvent();
		while (event != null) {
			System.out.println("Got event: " + event);
			eventHandler.handleEvent(event);
			event = eventStream.getNextEvent();
		}
	}
}
