package service.impl;

import entity.impl.Consumer;
import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerServiceTest {

    private ConsumerServiceImpl consumerService;
    private Store store;
    private Product product;
    private Consumer consumer;

    @BeforeEach
    void setUp() {
        consumerService = new ConsumerServiceImpl();

        // Real product
        product = new Product("Latte", 5.0);

        // Real store with product inventory
        Map<Product, Integer> inventory = new HashMap<>();
        inventory.put(product, 10);  // 10 in stock
        store = new Store("Main Street", "123456", "store@example.com", inventory);

        // Real consumer
        consumer = new Consumer("Alice", 50.0);

        // Add consumer to service
        consumerService.addConsumer(consumer);
    }

    @Test
    void testBuyProduct_Success() {
        boolean result = consumerService.buyProduct(consumer, store, product, 5);
        assertTrue(result);                  // ✅ purchase succeeds
        assertEquals(25.0, consumer.getMoney()); // ✅ money deducted
    }

    @Test
    void testBuyProduct_NotEnoughMoney() {
        boolean result = consumerService.buyProduct(consumer, store, product, 20);
        assertFalse(result);                 // ✅ purchase fails
        assertEquals(50.0, consumer.getMoney()); // ✅ money stays the same
    }
}