package ru.sbt.mipt.oop.smarthome.signalization;

public interface SignalizationState {
	void activateSignalization(String code);
	void deactivateSignalization(String code);
	void alertSignalization();
}
