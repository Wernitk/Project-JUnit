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
}
