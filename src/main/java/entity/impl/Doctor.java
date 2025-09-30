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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Double.compare(money, doctor.money) == 0 && Objects.equals(name, doctor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money);
    }

    @Override
    public String toString() {
        return "Doctor{" +
               "name='" + name + '\'' +
               ", money=" + money +
               '}';
    }
}
