package ru.sbt.mipt.oop.smarthome;

public class Door implements HomeComponent {
	private final String id;
	private boolean isOpen;


	public Door(String id, boolean isOpen) {
		this.isOpen = isOpen;
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	public boolean getOpen() {
		return isOpen;
	}

	void setOpen(boolean open) {
		isOpen = open;
	}
}
