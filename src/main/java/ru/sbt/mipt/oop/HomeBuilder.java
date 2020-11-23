package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.devices.signalization.Signalization;

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
		SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall), signalization);
		return smartHome;
	}
}
