package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.smarthome.Door;
import ru.sbt.mipt.oop.smarthome.Light;
import ru.sbt.mipt.oop.smarthome.Room;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HomeBuilder {

	public static void main(String[] args) throws IOException {
		Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true),
				new Door("1", false)), "kitchen");
		Room bathroom = new Room(Arrays.asList(new Light("3", true), new Door("2", false)),
				"bathroom");
		Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false),
				new Light("6", false), new Door("3", true)),
				"bedroom");
		Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false),
				new Light("9", false), new Door("4", false)),
				"hall");
		SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(smartHome);
		System.out.println(jsonString);
		Path path = Paths.get("output.js");
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(jsonString);
		}
	}

	public static SmartHome buildHome() {
		Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true),
				new Door("1", false)), "kitchen");
		Room bathroom = new Room(Arrays.asList(new Light("3", true), new Door("2", false)),
				"bathroom");
		Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false),
				new Light("6", false), new Door("3", true)),
				"bedroom");
		Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false),
				new Light("9", false), new Door("4", false)),
				"hall");
		SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
		return smartHome;
	}
}
