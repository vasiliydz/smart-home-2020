package ru.sbt.mipt.oop.smarthome;

import java.util.List;

public interface Actionable {
	void applyAction(Action action, List<Actionable> parents);
}
