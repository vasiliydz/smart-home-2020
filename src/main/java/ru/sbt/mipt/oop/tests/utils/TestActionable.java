package ru.sbt.mipt.oop.tests.utils;

import ru.sbt.mipt.oop.smarthome.Actionable;

public class TestActionable implements Actionable {
	private final String id;
	private boolean acted;

	public TestActionable(String id) {
		this.id = id;
		acted = false;
	}

	public String getId() {
		return id;
	}

	public boolean isActed() {
		return acted;
	}

	public void setActed(boolean acted) {
		this.acted = acted;
	}
}
