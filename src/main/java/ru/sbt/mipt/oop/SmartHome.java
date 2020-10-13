package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.InnerIteratorActionable;
import ru.sbt.mipt.oop.smarthome.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements InnerIteratorActionable {
    private final String id;
    private final Collection<Room> rooms;

    public SmartHome(String id) {
        rooms = new ArrayList<>();
        this.id = id;
    }

    public SmartHome(String id, Collection<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void applyToInnerComponents(Action action) {
        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
