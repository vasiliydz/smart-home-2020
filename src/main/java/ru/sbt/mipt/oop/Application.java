package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.smarthome.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Application {

	public static void main(String... args) {
		// считываем состояние дома из файла (нет)
		SmartHome smartHome = HomeBuilder.buildHome();
		// создаём тестовый генератор событий
		EventStream testEventStream = new RandomEventGenerator(0.95);
		// создаём обработчик событий
		EventHandler handler = new ChainEventHandler(Arrays.asList(
				new MainSmartHomeEventHandler(smartHome),
				new LightsOffScenario(smartHome)
		));
		// начинаем обработку событий
		EventStreamProcessor processor = new SimpleEventStreamProcessor(handler);
		processor.processEventStream(testEventStream);
	}

	private static SmartHome getSmartHomeFromJsonFile(String filename) throws IOException {
		Gson gson = new Gson();
		String json = new String(Files.readAllBytes(Paths.get(filename)));
		SmartHome smartHome = gson.fromJson(json, SmartHome.class);
		return smartHome;
	}
}
