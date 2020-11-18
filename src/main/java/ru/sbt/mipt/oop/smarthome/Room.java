package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;

public class Room implements InnerIteratorActionable {
	private final String id;
	private final Collection<Actionable> innerActionables;


	public Room(String id, Collection<Actionable> innerActionables) {
		this.innerActionables = new ArrayList<>(innerActionables);
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void executeToInnerActionables(Action action) {
		for (Actionable actionable : innerActionables) {
			actionable.execute(action);
		}
	}
}
