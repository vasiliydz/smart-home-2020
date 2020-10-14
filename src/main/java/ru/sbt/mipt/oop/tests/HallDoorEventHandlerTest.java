package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.*;

import java.util.Arrays;

public class HallDoorEventHandlerTest {

	@Test
	public void switchingOffLightsWhenHallDoorIsClosed() {
		// given
		Door hallDoor = new Door("1", true);
		Door roomDoor = new Door("2", true);
		Light hallLight = new Light("3", true);
		Light roomLight = new Light("4", true);
		SmartHome home = buildSimpleHomeWithHall(hallDoor, hallLight, roomDoor, roomLight);
		EventHandler handler = new EventHandlerBuilder(home)
				.setHallDoorScenario(true).build();
		// when
		handler.handleEvent(new DoorCloseEvent("1"));
		// then
		Assertions.assertArrayEquals(new boolean[]{false, true, false, false},
				new boolean[]{hallDoor.getOpen(), roomDoor.getOpen(),
						hallLight.isOn(), roomLight.isOn()});
	}

	@Test
	public void notSwitchingOffLightsWhenNotHallDoorIsClosed() {
		// given
		Door hallDoor = new Door("1", true);
		Door roomDoor = new Door("2", true);
		Light hallLight = new Light("3", true);
		Light roomLight = new Light("4", true);
		SmartHome home = buildSimpleHomeWithHall(hallDoor, hallLight, roomDoor, roomLight);
		EventHandler handler = new EventHandlerBuilder(home)
				.setHallDoorScenario(true).build();
		// when
		handler.handleEvent(new DoorCloseEvent("2"));
		// then
		Assertions.assertArrayEquals(new boolean[]{true, false, true, true},
				new boolean[]{hallDoor.getOpen(), roomDoor.getOpen(),
						hallLight.isOn(), roomLight.isOn()});
	}


	private SmartHome buildSimpleHomeWithHall(Door hallDoor, Light hallLight,
											  Door roomDoor, Light roomLight) {
		Room room = new Room("room", Arrays.asList(
				roomDoor, roomLight
		));
		Room hall = new Room("hall", Arrays.asList(
				hallDoor, hallLight
		));
		return new SmartHome("smartHome", Arrays.asList(room, hall));
	}
}
