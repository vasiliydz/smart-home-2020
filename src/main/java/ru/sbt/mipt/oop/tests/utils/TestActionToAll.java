package ru.sbt.mipt.oop.tests.utils;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.tests.utils.TestActionable;

public class TestActionToAll implements Action {

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof TestActionable) {
			((TestActionable) actionable).setActed(true);
		}
	}
}
