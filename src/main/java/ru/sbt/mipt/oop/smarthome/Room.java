package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;

public class Room implements InnerIteratorActionable {
	private final String name;
	private final Collection<Actionable> innerActionables;


	public Room(String name, Collection<Actionable> innerActionables) {
		this.innerActionables = new ArrayList<>(innerActionables);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void executeToInnerActionables(Action action) {
		for (Actionable actionable : innerActionables) {
			actionable.execute(action);
		}
	}
}
