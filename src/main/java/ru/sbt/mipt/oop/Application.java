package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.signalization.Signalization;

public class Application {

	public static void main(String... args) {
		// считываем состояние дома из файла (нет)
		Signalization signalization = new Signalization();
		SmartHome smartHome = HomeBuilder.buildHome(signalization);
		// создаём тестовый генератор событий
		EventStream testEventStream = new RandomEventGenerator(0.95);
		// создаём обработчик событий
		EventHandler handler = new EventHandlerBuilder(smartHome)
				.addHallDoorScenario().addSignalization(signalization)
				.addMessageSender(new SmsSender()).build();
		// начинаем обработку событий
		EventStreamProcessor processor = new SimpleEventStreamProcessor(handler);
		processor.processEventStream(testEventStream);
	}
}
