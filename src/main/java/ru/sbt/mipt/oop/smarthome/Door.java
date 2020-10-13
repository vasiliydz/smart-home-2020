package ru.sbt.mipt.oop.smarthome;

public class Door implements HomeComponent {
	private final String id;
	private boolean isOpen;


	public Door(boolean isOpen, String id) {
		this.isOpen = isOpen;
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	void setOpen(boolean open) {
		isOpen = open;
	}
}
