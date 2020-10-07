package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;

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

	public boolean containsComponent(String componentId) {
		for (HomeComponent component : components) {
			if (component.getId().equals(componentId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void execute(Action action) {
		for (HomeComponent component : components) {
			action.actToComponent(component);
		}
	}
}
