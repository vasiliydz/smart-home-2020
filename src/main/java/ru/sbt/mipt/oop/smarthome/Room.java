package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Room implements Actionable {
	private final Collection<HomeComponent> components;
	private final String name;

	public Room(Collection<HomeComponent> components, String name) {
		this.components = new ArrayList<>(components);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void applyAction(Action action, List<Actionable> parents) {
		List<Actionable> newParents = new ArrayList<>(parents);
		newParents.add(this);
		for (HomeComponent component : components) {
			action.actToComponent(component, newParents);
		}
	}
}
