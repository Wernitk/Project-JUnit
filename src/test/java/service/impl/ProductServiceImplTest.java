package service.impl;

import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import service.ModelsUtils;
import service.ProductService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    private ProductService service;
    private Store store;
    private Product product;

    @BeforeEach
    void setUp() {
        service = new ProductServiceImpl();
        product = ModelsUtils.getProduct();

        store = Mockito.mock(Store.class);
        Map<Product, Integer> stock = new HashMap<>();
        stock.put(product, ModelsUtils.TEST_QUANTITY);
        when(store.getProductIntegerMap()).thenReturn(stock);
    }

    @Test
    @DisplayName("Testing add products")
    void addProduct() {
        Product product = new Product("winston siniy",150.00);
        service.addProduct(store, product, 1000);
        assertEquals(1000, store.getProductIntegerMap().get(product));
        service.addProduct(store, product, 100);
        assertEquals(1100, store.getProductIntegerMap().get(product));
    }

    @Test
    @DisplayName("Testing remove product")
    void removeProduct() {
        service.removeProduct(store, product);
        assertFalse(store.getProductIntegerMap().containsKey(product));
    }

    @Test
    @DisplayName("Testing price update")
    void updatePrice() {
        service.updatePrice(product, 112.00);
        assertEquals(112.00, product.getPrice());
    }

    @Test
    @DisplayName("Testing get product")
    void getProduct() {
        assertEquals(product, service.getProduct(store, product));
    }

    @Test
    @DisplayName("Testing get product by name")
    void getProductByName() {
        assertEquals(product, service.getProductByName(store,"Milk"));
    }
}
