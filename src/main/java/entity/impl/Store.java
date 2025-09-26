package entity.impl;

import java.util.Map;
import java.util.Objects;

public class Store {
    private static Integer countId = 1;
    private final int id;
    private final String name = "Novus";
    private final String address;
    private String phone;
    private String email;
    private Map<Product, Integer> productIntegerMap;

    public Store(String address, String phone, String email, Map<Product, Integer> productIntegerMap) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.productIntegerMap = productIntegerMap;
        this.id = countId++;
    }

    public int getId() {
        return id;
    }

    public Map<Product, Integer> getProductIntegerMap() {
        return productIntegerMap;
    }

    public void setProductIntegerMap(Map<Product, Integer> productIntegerMap) {
        this.productIntegerMap = productIntegerMap;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return id == store.id && Objects.equals(name, store.name) && Objects.equals(address, store.address) && Objects.equals(phone, store.phone) && Objects.equals(email, store.email) && Objects.equals(productIntegerMap, store.productIntegerMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, email, productIntegerMap);
    }

    @Override
    public String toString() {
        return "Store{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", phone='" + phone + '\'' +
               ", email='" + email + '\'' +
               ", productIntegerMap=" + productIntegerMap +
               '}';
    }
}
