package ru.sbt.mipt.oop.smarthome;

import java.util.List;

public class AllLightsOffAction implements Action {

	@Override
	public void actToComponent(HomeComponent component, List<Actionable> parents) {
		if (component instanceof Light) {
			((Light) component).setOn(false);
			System.out.println("Light " + component.getId() + " was turned off.");
		}
	}
}
