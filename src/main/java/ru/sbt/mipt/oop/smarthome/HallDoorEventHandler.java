package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.SmartHome;

public class HallDoorEventHandler implements EventHandler {
	private final SmartHome home;
	private final Room hall;

	public HallDoorEventHandler(SmartHome home) {
		this.home = home;
		hall = home.getRoomByName("hall");
		if (hall == null) {
			throw new RuntimeException("There is no hall in the home");
		}
	}

	@Override
	public void handleEvent(Event event) {
		if (!(event instanceof DoorCloseEvent)) { // срабатывает только если дверь закрывается
			return;
		}
		if (hall.containsComponent(event.getObjectId())) { // если эта дверь находится в холле
			// то вырубаем везде свет
			home.execute(component -> {
				if (component instanceof Light) {
					Light light = (Light) component;
					light.setOn(false);
					new LightOffSensorCommand(light).send(); // и посылаем лампе команду в реальный мир
				}
			});
		}
	}
}
