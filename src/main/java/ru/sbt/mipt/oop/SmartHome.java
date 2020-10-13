package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;
import ru.sbt.mipt.oop.smarthome.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SmartHome implements Actionable {
    private final Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
    }

    @Override
    public void applyAction(Action action, List<Actionable> parents) {
        List<Actionable> newParents = new ArrayList<>(parents); // формируем новый список родителей
        newParents.add(this);

        for (Room room : rooms) {
            room.applyAction(action, newParents);
        }
    }
}
