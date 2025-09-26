package service.impl;

import entity.impl.Consumer;
import entity.impl.Product;
import entity.impl.Store;
import service.ConsumerService;

import java.util.ArrayList;
import java.util.List;

public class ConsumerServiceImpl implements ConsumerService {
    private final List<Consumer> consumers = new ArrayList<>();

    @Override
    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }

    @Override
    public void removeConsumer(String name) {
        consumers.removeIf(c -> c.getName().equals(name));
    }

    @Override
    public Consumer findConsumer(String name) {
        return consumers.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean buyProduct(Consumer consumer, Store store, Product product, int quantity) {
        Integer stock = store.getProductIntegerMap().get(product);
        if (stock != null && stock >= quantity) {
            double totalPrice = product.getPrice() * quantity;
            if (consumer.getMoney() >= totalPrice) {
                consumer.setMoney(consumer.getMoney() - totalPrice);
                store.getProductIntegerMap().put(product, stock - quantity);
                return true;
            }
        }
        return false;
    }
}
