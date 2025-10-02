package service.impl;

import entity.impl.Product;
import entity.impl.Store;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

    @Override
    public void addProduct(Store store, Product product, int quantity) {
        store.getProductIntegerMap().merge(product, quantity, Integer::sum);
    }

    @Override
    public void removeProduct(Store store, Product product) {
        store.getProductIntegerMap().remove(product);
    }

    @Override
    public void updatePrice(Product product, double newPrice) {
        product.setPrice(newPrice);
    }

    @Override
    public Product getProduct(Store store, Product product) {
        return store.getProductIntegerMap().keySet().stream()
                .filter(p -> p.equals(product))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product getProductByName(Store store, String name) {
        return store.getProductIntegerMap().keySet().stream()
                .filter( p -> p.getName().compareToIgnoreCase(name.toLowerCase()) == 0)
                .findFirst()
                .orElse(null);
    }
}
