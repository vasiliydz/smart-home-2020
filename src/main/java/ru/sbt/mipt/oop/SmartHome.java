package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.InnerIteratorActionable;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.devices.signalization.Signalization;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements InnerIteratorActionable {
    private final Collection<Room> rooms;
    private final Signalization signalization;

    public SmartHome(Signalization signalization) {
        rooms = new ArrayList<>();
        this.signalization = signalization;
    }

    public SmartHome(Collection<Room> rooms, Signalization signalization) {
        this.rooms = new ArrayList<>(rooms);
        this.signalization = signalization;
    }

    @Override
    public void executeToInnerActionables(Action action) {
        signalization.execute(action);
        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
