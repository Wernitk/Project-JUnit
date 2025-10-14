package service.impl;

import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ModelsUtils;

import java.util.Map;

public class ProductServiceImplTest {
    private ProductServiceImpl productService;
    private Product product;
    private Store store;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
        product = ModelsUtils.getProduct();

        store = new Store(
                ModelsUtils.TEST_ADDRESS_NAME,
                ModelsUtils.TEST_PHONE,
                ModelsUtils.TEST_EMAIL,
                new java.util.HashMap<>(Map.of(product, ModelsUtils.TEST_QUANTITY))
        );
    }

    @Test
    @DisplayName("Should add product successfully")
    void addProduct() {
        Product newProduct = new Product("Test", 10.0);

        productService.addProduct(store, newProduct, ModelsUtils.TEST_QUANTITY);

        assert store.getProductIntegerMap().containsKey(newProduct);
        assert store.getProductIntegerMap().get(newProduct).equals(ModelsUtils.TEST_QUANTITY);
    }

    @Test
    @DisplayName("Remove product successfully")
    void removeProduct() {
        productService.removeProduct(store, product);

        assert !store.getProductIntegerMap().containsKey(product);
    }

    @Test
    @DisplayName("Update product price successfully")
    void updateProductPrice() {
        double newPrice = 50.0;

        productService.updatePrice(product, newPrice);

        assert product.getPrice() == newPrice;
    }

    @Test
    @DisplayName("Get product successfully")
    void getProduct() {
        Product foundProduct = productService.getProduct(store, product);

        assert foundProduct != null;
        assert foundProduct.getName().equals(product.getName());
    }

    @Test
    @DisplayName("Get product by name successfully")
    void getProductByName() {
        Product foundProduct = productService.getProductByName(store, "NoneExisting");

        assert foundProduct == null;
    }
}
