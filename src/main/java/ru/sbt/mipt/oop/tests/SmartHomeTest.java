package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.tests.utils.TestActionById;
import ru.sbt.mipt.oop.tests.utils.TestActionToAll;
import ru.sbt.mipt.oop.tests.utils.TestActionable;

import java.util.Arrays;

public class SmartHomeTest {

	@Test
	void actionToAllComponents() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		// when
		home.execute(new TestActionToAll());
		// then
		Assertions.assertArrayEquals(new boolean[]{true, true, true, true},
				getFlagsArray(actionables));
	}

	@Test
	void actionToComponentById() {
		// given
		TestActionable[] actionables = createTestActionables();
		SmartHome home = buildSimpleHome(actionables);
		// when
		home.execute(new TestActionById("3"));
		// then
		Assertions.assertArrayEquals(new boolean[]{false, false, true, false},
				getFlagsArray(actionables));
	}

	private TestActionable[] createTestActionables() {
		return new TestActionable[] {
				new TestActionable("1"),
				new TestActionable("2"),
				new TestActionable("3"),
				new TestActionable("4")
		};
	}

	private SmartHome buildSimpleHome(Actionable[] actionables) {
		Room room1 = new Room("room1", Arrays.asList(actionables[0], actionables[1]));
		Room room2 = new Room("room2", Arrays.asList(actionables[2], actionables[3]));
		return new SmartHome("smartHome", Arrays.asList(room1, room2));

	}

	private boolean[] getFlagsArray(TestActionable[] actionables) {
		return new boolean[] {
				actionables[0].isActed(),
				actionables[1].isActed(),
				actionables[2].isActed(),
				actionables[3].isActed()
		};
	}
}
