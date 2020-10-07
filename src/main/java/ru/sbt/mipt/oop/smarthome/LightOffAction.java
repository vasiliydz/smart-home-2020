package ru.sbt.mipt.oop.smarthome;

public class LightOffAction implements Action {
	private final String objectId;

	public LightOffAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void actToComponent(HomeComponent component) {
		if (component instanceof Light) {
			if (component.getId().equals(objectId)) {
				((Light) component).setOn(false);
				System.out.println("Light " + objectId + " was turned off.");
			}
		}
	}
}
