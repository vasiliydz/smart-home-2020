package ru.sbt.mipt.oop.smarthome;

import java.util.List;

public interface Action {
	void actToComponent(HomeComponent component, List<Actionable> parents);
}
