package service;

import entity.impl.Product;
import entity.impl.Store;

import java.util.Map;

public interface StoreService {
    void addStore(Store store);
    Store findStoreById(int id);
    Map<Product, Integer> getAllProducts(Store store);
}
