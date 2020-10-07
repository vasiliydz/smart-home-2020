package ru.sbt.mipt.oop.smarthome;

public class LightOnAction implements Action {
	private final String objectId;

	public LightOnAction(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public void actToComponent(HomeComponent component) {
		if (component instanceof Light) {
			if (component.getId().equals(objectId)) {
				((Light) component).setOn(true);
				System.out.println("Light " + objectId + " was turned on.");
			}
		}
	}
}
