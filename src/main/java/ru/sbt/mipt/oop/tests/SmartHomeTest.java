package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Room;

import java.util.Arrays;

public class SmartHomeTest {

	@Test
	void actionToAllComponents() {
		// given
		TestComponent component1 = new TestComponent("1");
		TestComponent component2 = new TestComponent("2");
		TestComponent component3 = new TestComponent("3");
		TestComponent component4 = new TestComponent("4");
		SmartHome home = buildSimpleHome(component1, component2, component3, component4);
		// when
		home.execute(new TestActionToAll());
		// then
		Assertions.assertArrayEquals(new boolean[]{true, true, true, true},
				new boolean[]{component1.isActed(), component2.isActed(),
				component3.isActed(), component4.isActed()});
	}

	@Test
	void actionToComponentById() {
		// given
		TestComponent component1 = new TestComponent("1");
		TestComponent component2 = new TestComponent("2");
		TestComponent component3 = new TestComponent("3");
		TestComponent component4 = new TestComponent("4");
		SmartHome home = buildSimpleHome(component1, component2, component3, component4);
		// when
		home.execute(new TestActionById("3"));
		// then
		Assertions.assertArrayEquals(new boolean[]{false, false, true, false},
				new boolean[]{component1.isActed(), component2.isActed(),
						component3.isActed(), component4.isActed()});
	}

	private SmartHome buildSimpleHome(Actionable component1, Actionable component2,
									  Actionable component3, Actionable component4) {
		Room room1 = new Room("room1", Arrays.asList(component1, component2));
		Room room2 = new Room("room2", Arrays.asList(component3, component4));
		return new SmartHome("smartHome", Arrays.asList(room1, room2));

	}

	private static class TestComponent implements Actionable {
		private final String id;
		private boolean acted;

		public TestComponent(String id) {
			this.id = id;
			acted = false;
		}

		@Override
		public String getId() {
			return id;
		}

		public boolean isActed() {
			return acted;
		}

		void setActed(boolean acted) {
			this.acted = acted;
		}
	}

	private static class TestActionToAll implements Action {

		@Override
		public void apply(Actionable actionable) {
			if (actionable instanceof TestComponent) {
				((TestComponent) actionable).setActed(true);
			}
		}
	}

	private static class TestActionById implements Action {
		private final String targetId;

		public TestActionById(String targetId) {
			this.targetId = targetId;
		}

		@Override
		public void apply(Actionable actionable) {
			if (actionable.getId().equals(targetId)) {
				((TestComponent) actionable).setActed(true);
			}
		}
	}
}
