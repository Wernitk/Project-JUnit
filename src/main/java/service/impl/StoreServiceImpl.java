package service.impl;

import entity.impl.Product;
import entity.impl.Store;
import service.ProductService;
import service.StoreService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreServiceImpl implements StoreService {
    private final List<Store> stores = new ArrayList<>();
    private final ProductService productService;

    public StoreServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void addStore(Store store) {
        stores.add(store);
    }

    @Override
    public Store findStoreById(int id) {
        return stores.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<Product, Integer> getAllProducts(Store store) {
        return new HashMap<>(store.getProductIntegerMap());
    }

    public Product addProductToStore(Store store, Product product, int quantity) {
        productService.addProduct(store, product, quantity);
        return productService.getProduct(store, product);
    }
}