package entity.impl;

import java.util.Objects;

public class Doctor {
    private String name;
    private double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Doctor{" +
               "name='" + name + '\'' +
               ", money=" + money +
               '}';
    }
}
