package ru.sbt.mipt.oop.tests.utils;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.tests.utils.TestActionable;

public class TestActionById implements Action {
	private final String targetId;

	public TestActionById(String targetId) {
		this.targetId = targetId;
	}

	@Override
	public void apply(Actionable actionable) {
		if (actionable instanceof TestActionable
				&& actionable.getId().equals(targetId)) {
			((TestActionable) actionable).setActed(true);
		}
	}
}
