package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.commands.*;
import ru.sbt.mipt.oop.smarthome.devices.door.Door;
import ru.sbt.mipt.oop.smarthome.devices.door.EntranceDoor;
import ru.sbt.mipt.oop.smarthome.devices.light.Light;
import ru.sbt.mipt.oop.smarthome.devices.signalization.Signalization;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SensorCommandsTest {

	@Test
	public void allLightsInHallOnCommandSwitchesOnLightsInHall() {
		// given
		Door entranceDoor = new EntranceDoor("1", false);
		Door roomDoor = new Door("2", true);
		Light hall1Light = new Light("3", true);
		Light hall2Light = new Light("4", false);
		Light room1Light = new Light("5", false);
		Light room2Light = new Light("6", true);
		SmartHome smartHome = buildSimpleHome(new Signalization(),
				Arrays.asList(roomDoor, room1Light, room2Light),
				Arrays.asList(entranceDoor, hall1Light, hall2Light));
		// when
		new AllLightsInHallOnCommand(smartHome).execute();
		// then
		Assertions.assertEquals(false, entranceDoor.getOpen());
		Assertions.assertEquals(true, roomDoor.getOpen());
		Assertions.assertEquals(true, hall1Light.isOn());
		Assertions.assertEquals(true, hall2Light.isOn());
		Assertions.assertEquals(false, room1Light.isOn());
		Assertions.assertEquals(true, room2Light.isOn());
	}

	@Test
	public void allLightsOffCommandSwitchesOffAllLights() {
		// given
		Door entranceDoor = new EntranceDoor("1", false);
		Door roomDoor = new Door("2", true);
		Light hall1Light = new Light("3", true);
		Light hall2Light = new Light("4", false);
		Light room1Light = new Light("5", false);
		Light room2Light = new Light("6", true);
		SmartHome smartHome = buildSimpleHome(new Signalization(),
				Arrays.asList(roomDoor, room1Light, room2Light),
				Arrays.asList(entranceDoor, hall1Light, hall2Light));
		// when
		new AllLightsOffCommand(smartHome).execute();
		// then
		Assertions.assertEquals(false, entranceDoor.getOpen());
		Assertions.assertEquals(true, roomDoor.getOpen());
		Assertions.assertEquals(false, hall1Light.isOn());
		Assertions.assertEquals(false, hall2Light.isOn());
		Assertions.assertEquals(false, room1Light.isOn());
		Assertions.assertEquals(false, room2Light.isOn());
	}

	@Test
	public void allLightsOnCommandSwitchesOnAllLights() {
		// given
		Door entranceDoor = new EntranceDoor("1", false);
		Door roomDoor = new Door("2", true);
		Light hall1Light = new Light("3", true);
		Light hall2Light = new Light("4", false);
		Light room1Light = new Light("5", false);
		Light room2Light = new Light("6", true);
		SmartHome smartHome = buildSimpleHome(new Signalization(),
				Arrays.asList(roomDoor, room1Light, room2Light),
				Arrays.asList(entranceDoor, hall1Light, hall2Light));
		// when
		new AllLightsOnCommand(smartHome).execute();
		// then
		Assertions.assertEquals(false, entranceDoor.getOpen());
		Assertions.assertEquals(true, roomDoor.getOpen());
		Assertions.assertEquals(true, hall1Light.isOn());
		Assertions.assertEquals(true, hall2Light.isOn());
		Assertions.assertEquals(true, room1Light.isOn());
		Assertions.assertEquals(true, room2Light.isOn());
	}

	@Test
	public void entranceDoorCloseCommandClosesEntranceDoor() {
		// given
		Door entranceDoor = new EntranceDoor("1", true);
		Door hallDoor = new Door("2", true);
		Door room1Door = new Door("3", true);
		Door room2Door = new Door("4", false);
		Light hallLight = new Light("5", true);
		Light roomLight = new Light("6", false);
		SmartHome smartHome = buildSimpleHome(new Signalization(),
				Arrays.asList(room1Door, room2Door, roomLight),
				Arrays.asList(entranceDoor, hallDoor, hallLight));
		// when
		new EntranceDoorCloseCommand(smartHome).execute();
		// then
		Assertions.assertEquals(false, entranceDoor.getOpen());
		Assertions.assertEquals(true, hallDoor.getOpen());
		Assertions.assertEquals(true, room1Door.getOpen());
		Assertions.assertEquals(false, room2Door.getOpen());
		Assertions.assertEquals(true, hallLight.isOn());
		Assertions.assertEquals(false, roomLight.isOn());
	}

	@Test
	public void signalizationActivateCommandActivatesSignalization() {
		// given
		Signalization signalization = new Signalization();
		SmartHome smartHome = buildSimpleHome(signalization, Collections.emptyList(), Collections.emptyList());
		// when
		new SignalizationActivateCommand(smartHome, "123").execute();
		//then
		Assertions.assertEquals(true, signalization.isActivated());
	}

	@Test
	public void signalizationAlertCommandAlertsActivatedSignalization() {
		// given
		Signalization signalization = new Signalization();
		SmartHome smartHome = buildSimpleHome(signalization, Collections.emptyList(), Collections.emptyList());
		new SignalizationActivateCommand(smartHome, "123").execute();
		// when
		new SignalizationAlertCommand(smartHome).execute();
		//then
		Assertions.assertEquals(true, signalization.isAlerted());
	}

	private SmartHome buildSimpleHome(Signalization signalization, Collection<Actionable> roomActionables,
									  Collection<Actionable> hallActionables) {
		Collection<Room> rooms = Arrays.asList(
				new Room("room", roomActionables),
				new Room("hall", hallActionables)
		);
		return new SmartHome(rooms, signalization);
	}
}
