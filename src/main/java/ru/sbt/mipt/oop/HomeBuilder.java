package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.smarthome.Door;
import ru.sbt.mipt.oop.smarthome.Light;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.signalization.Signalization;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HomeBuilder {

	public static SmartHome buildHome(Signalization signalization) {
		Room kitchen = new Room("kitchen", Arrays.asList(new Light("1", false),
				new Light("2", true), new Door("1", false)));
		Room bathroom = new Room("bathroom", Arrays.asList(new Light("3", true),
				new Door("2", false)));
		Room bedroom = new Room("bedroom", Arrays.asList(new Light("4", false),
				new Light("5", false),	new Light("6", false), new Door("3", true)));
		Room hall = new Room("hall", Arrays.asList(new Light("7", false),
				new Light("8", false),	new Light("9", false), new Door("4", false)));
		SmartHome smartHome = new SmartHome("smartHome", Arrays.asList(kitchen, bathroom, bedroom, hall), signalization);
		return smartHome;
	}
}
