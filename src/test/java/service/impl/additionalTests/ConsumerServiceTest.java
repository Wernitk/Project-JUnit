package service.impl.additionalTests;

import entity.impl.Consumer;
import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import service.impl.ConsumerServiceImpl;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ConsumerServiceImplTest {

    @InjectMocks
    private ConsumerServiceImpl consumerService;
    private Consumer consumer;

    @BeforeEach
    void setUp() {
        consumer = new Consumer("Alice", 200.0);
    }


    @Test
    @DisplayName("Add consumer should add to list")
    void addConsumerShouldAddToList() {
        consumerService.addConsumer(consumer);
        Consumer found = consumerService.findConsumer("Alice");
        assertNotNull(found);
        assertEquals("Alice", found.getName());
    }

    @Test
    @DisplayName("Remmoved consumer should be delete by name")
    void removeConsumerShouldDeleteByName() {
        consumerService.addConsumer(consumer);
        consumerService.removeConsumer("Alice");
        Consumer found = consumerService.findConsumer("Alice");
        assertNull(found);
    }

    @Test
    @DisplayName("Find consumer should return correct consumer")
    void findConsumerShouldReturnConsumer() {
        consumerService.addConsumer(consumer);
        Consumer found = consumerService.findConsumer("Alice");
        assertNotNull(found);
        assertEquals(200.0, found.getMoney());
    }

    @Test
    @DisplayName("Buying product is available when consumer has enough money and store has enough stock")
    void buyProductShouldSucceed() {
        Product product = new Product("Apple", 10.0);

        Store store = mock(Store.class);
        Map<Product, Integer> stock = new HashMap<>();
        stock.put(product, 10);
        when(store.getProductIntegerMap()).thenReturn(stock);

        boolean result = consumerService.buyProduct(consumer, store, product, 5);

        assertTrue(result);
        assertEquals(150.0, consumer.getMoney());
        assertEquals(5, stock.get(product));
        verify(store, atLeastOnce()).getProductIntegerMap();
    }

    @Test
    @DisplayName("Buy product fails when consumer doesn't have enough money")
    void buyProductShouldFail_NotEnoughMoney() {
        Consumer consumer = new Consumer("Mary", 10.0);
        Product product = new Product("Banana", 10.0);

        Store store = mock(Store.class);
        Map<Product, Integer> stock = new HashMap<>();
        stock.put(product, 10);
        when(store.getProductIntegerMap()).thenReturn(stock);

        boolean result = consumerService.buyProduct(consumer, store, product, 5);
        assertFalse(result);
        assertEquals(10.0, consumer.getMoney());
    }

    @Test
    @DisplayName("Buy product fails when store doesnt have enough")
    void buyProductShouldFailNotEnoughStock() {
        Consumer richConsumer = new Consumer("Nick", 100.0);
        Product product = new Product("Orange", 10.0);

        Store store = mock(Store.class);
        Map<Product, Integer> stock = new HashMap<>();
        stock.put(product, 2);
        when(store.getProductIntegerMap()).thenReturn(stock);

        boolean result = consumerService.buyProduct(richConsumer, store, product, 5);
        assertFalse(result);
        assertEquals(2, stock.get(product));
    }

    @RepeatedTest(3)
    @DisplayName("Multiple successful purchases")
    void repeatedPurchaseTest() {
        Consumer consumerRepeated = new Consumer("Serg", 500.0);
        Product product = new Product("Milk", 50.0);

        Store store = mock(Store.class);
        Map<Product, Integer> stock = new HashMap<>();
        stock.put(product, 15);
        when(store.getProductIntegerMap()).thenReturn(stock);

        boolean result = consumerService.buyProduct(consumerRepeated, store, product, 5);

        assertTrue(result);
        assertEquals(250.0, consumerRepeated.getMoney());
        assertEquals(10, stock.get(product));
        verify(store, atLeastOnce()).getProductIntegerMap();
    }
}