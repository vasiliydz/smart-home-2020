package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.EventHandlerBuilder;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.signalization.Signalization;

import java.util.Arrays;

public class MainSmartHomeEventHandlerTest {

	@Test
	public void closingDoor() {
		// given
		Door door1 = new Door("1", true);
		Door door2 = new Door("2", true);
		Light light1 = new Light("3", true);
		Light light2 = new Light("4", false);
		SmartHome home = buildSimpleHome(door1, door2, light1, light2);
		EventHandler handler = new EventHandlerBuilder(home).build();
		// when
		handler.handleEvent(new DoorCloseEvent("1"));
		// then
		Assertions.assertArrayEquals(new boolean[]{false, true, true, false},
				new boolean[]{door1.getOpen(), door2.getOpen(),
						light1.isOn(), light2.isOn()});
	}

	@Test
	public void openingDoor() {
		// given
		Door door1 = new Door("1", false);
		Door door2 = new Door("2", false);
		Light light1 = new Light("3", true);
		Light light2 = new Light("4", false);
		SmartHome home = buildSimpleHome(door1, door2, light1, light2);
		EventHandler handler = new EventHandlerBuilder(home).build();
		// when
		handler.handleEvent(new DoorOpenEvent("2"));
		// then
		Assertions.assertArrayEquals(new boolean[]{false, true, true, false},
				new boolean[]{door1.getOpen(), door2.getOpen(),
						light1.isOn(), light2.isOn()});
	}

	@Test
	public void switchingOffLight() {
		// given
		Door door1 = new Door("1", true);
		Door door2 = new Door("2", false);
		Light light1 = new Light("3", true);
		Light light2 = new Light("4", true);
		SmartHome home = buildSimpleHome(door1, door2, light1, light2);
		EventHandler handler = new EventHandlerBuilder(home).build();
		// when
		handler.handleEvent(new LightOffEvent("3"));
		// then
		Assertions.assertArrayEquals(new boolean[]{true, false, false, true},
				new boolean[]{door1.getOpen(), door2.getOpen(),
						light1.isOn(), light2.isOn()});
	}

	@Test
	public void switchingOnLight() {
		// given
		Door door1 = new Door("1", false);
		Door door2 = new Door("2", true);
		Light light1 = new Light("3", false);
		Light light2 = new Light("4", false);
		SmartHome home = buildSimpleHome(door1, door2, light1, light2);
		EventHandler handler = new EventHandlerBuilder(home).build();
		// when
		handler.handleEvent(new LightOnEvent("4"));
		// then
		Assertions.assertArrayEquals(new boolean[]{false, true, false, true},
				new boolean[]{door1.getOpen(), door2.getOpen(),
						light1.isOn(), light2.isOn()});
	}

	private SmartHome buildSimpleHome(Door door1, Door door2, Light light1, Light light2) {
		Room room1 = new Room("room1", Arrays.asList(
				door1, light1
		));
		Room room2 = new Room("room2", Arrays.asList(
				door2, light2
		));
		return new SmartHome("smartHome", Arrays.asList(room1, room2), new Signalization());
	}
}