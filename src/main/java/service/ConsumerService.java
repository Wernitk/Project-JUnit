package service;

import entity.impl.Consumer;
import entity.impl.Product;
import entity.impl.Store;

public interface ConsumerService {
    void addConsumer(Consumer consumer);
    void removeConsumer(String name);
    Consumer findConsumer(String name);

    boolean buyProduct(Consumer consumer, Store store, Product product, int quantity);
}