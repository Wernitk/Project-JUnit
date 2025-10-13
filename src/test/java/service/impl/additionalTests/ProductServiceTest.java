package service.impl.additionalTests;

import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.impl.ProductServiceImpl;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private ProductServiceImpl productService;
    private Store mockStore;
    private Map<Product, Integer> stock;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
        mockStore = mock(Store.class);
        stock = new HashMap<>();
        when(mockStore.getProductIntegerMap()).thenReturn(stock);
    }


    @Test
    @DisplayName("Add product to store")
    void addProductShouldAddProduct() {
        Product product = new Product("Apple", 10.0);
        productService.addProduct(mockStore, product, 5);

        assertTrue(stock.containsKey(product));
        assertEquals(5, stock.get(product));
    }

    @Test
    @DisplayName("Add product again should increase quantity")
    void addProductShouldIncreaseQuantity() {
        Product product = new Product("Apple", 10.0);
        stock.put(product, 3);
        productService.addProduct(mockStore, product, 2);

        assertEquals(5, stock.get(product));
    }

    @Test
    @DisplayName("Remove product from store")
    void removeProductShouldRemoveProduct() {
        Product product = new Product("Banana", 15.0);
        stock.put(product, 10);

        productService.removeProduct(mockStore, product);

        assertFalse(stock.containsKey(product));
    }

    @Test
    @DisplayName("Update product price")
    void updatePriceShouldUpdatePrice() {
        Product product = new Product("Orange", 10.0);
        productService.updatePrice(product, 100.0);

        assertEquals(100.0, product.getPrice());
    }

    @Test
    @DisplayName("Get existing product by object")
    void getProductShouldReturnProduct() {
        Product product = new Product("Milk", 5.0);
        stock.put(product, 10);

        Product result = productService.getProduct(mockStore, product);
        assertNotNull(result);
        assertEquals(product, result);
    }

    @Test
    @DisplayName("Get not existing product by object should return null")
    void getProductShouldReturnNullIfNotFound() {
        Product product = new Product("Milk", 5.0);

        Product result = productService.getProduct(mockStore, product);
        assertNull(result);
    }

    @Test
    @DisplayName("Get existing product by name")
    void getProductByName_shouldReturnProduct() {
        Product product = new Product("milk", 5.0);
        stock.put(product, 10);

        Product result = productService.getProductByName(mockStore, "Milk");

        assertNotNull(result);
        assertEquals(product, result);
    }

    @Test
    @DisplayName("Getting not existing product by name should return null")
    void getProductByNameShouldReturnNullIfNotFound() {
        Product result = productService.getProductByName(mockStore, "Cheese");
        assertNull(result);
    }
}