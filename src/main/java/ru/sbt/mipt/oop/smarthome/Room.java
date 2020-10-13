package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;

public class Room implements InnerIteratorActionable {
	private final String id;
	private final Collection<Actionable> components;


	public Room(String id, Collection<Actionable> components) {
		this.components = new ArrayList<>(components);
		this.id = id;
	}

	public boolean containsComponent(String componentId) {
		for (Actionable component : components) {
			if (component.getId().equals(componentId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void applyToInnerComponents(Action action) {
		for (Actionable component : components) {
			action.apply(component);
		}
	}
}
