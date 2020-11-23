package ru.sbt.mipt.oop.smarthome.devices.door;

import ru.sbt.mipt.oop.smarthome.Actionable;

public class Door implements Actionable {
	private final String id;
	private boolean isOpen, isLocked;

	public Door(String id, boolean isOpen, boolean isLocked) {
		this.id = id;
		this.isOpen = isOpen;
		this.isLocked = isLocked;
	}

	public Door(String id, boolean isOpen) {
		this(id, isOpen, true);
	}

	public String getId() {
		return id;
	}

	public boolean getOpen() {
		return isOpen;
	}

	public boolean getLocked() {
		return isLocked;
	}

	void setOpen(boolean open) {
		isOpen = open;
	}

	void setLocked(boolean locked) {
		isLocked = locked;
	}
}
