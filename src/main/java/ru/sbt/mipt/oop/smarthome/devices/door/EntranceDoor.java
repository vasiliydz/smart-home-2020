package ru.sbt.mipt.oop.smarthome.devices.door;

public class EntranceDoor extends Door {

	public EntranceDoor(String id, boolean isOpen, boolean isLocked) {
		super(id, isOpen, isLocked);
	}

	public EntranceDoor(String id, boolean isOpen) {
		super(id, isOpen);
	}
}
