package entity.impl;

import entity.Person;
import entity.enums.Position;

public class Worker extends Person {
    private Position position;

    public Worker(String name, Position position) {
        super(name);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
