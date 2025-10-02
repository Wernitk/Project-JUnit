package service;

import entity.impl.Product;
import entity.impl.Store;

public interface ProductService {
    void addProduct(Store store, Product product, int quantity);
    void removeProduct(Store store, Product product);
    void updatePrice(Product product, double newPrice);
    Product getProduct(Store store, Product product);
    Product getProductByName(Store store, String name);
}
