package entity.impl;

import entity.Person;

public class Consumer extends Person {
    private String name;
    private double money;

    public Consumer(String name, Double money) {
        super(name);
        this.money = money;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
