package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.InnerIteratorActionable;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.signalization.Signalization;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements InnerIteratorActionable {
    private final String id;
    private final Collection<Room> rooms;
    private final Signalization signalization;

    public SmartHome(String id, Signalization signalization) {
        rooms = new ArrayList<>();
        this.id = id;
        this.signalization = signalization;
    }

    public SmartHome(String id, Collection<Room> rooms, Signalization signalization) {
        this.rooms = new ArrayList<>(rooms);
        this.id = id;
        this.signalization = signalization;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void executeToInnerActionables(Action action) {
        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
