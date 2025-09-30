package entity.impl;

import entity.Person;

public class Consumer extends Person {
    private double money;

    public Consumer(String name, Double money) {
        super(name);
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
