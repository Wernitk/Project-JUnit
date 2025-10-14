package service.impl;

import entity.impl.Consumer;
import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import service.ConsumerService;
import service.ModelsUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static service.ModelsUtils.*;

class ConsumerServiceImplTest {

    private ConsumerService service;
    private Store store;
    private Product product;
    private Consumer consumer;

    @BeforeEach
    void setUp() {
        service = new ConsumerServiceImpl();
        product = ModelsUtils.getProduct();
        consumer = ModelsUtils.getConsumer();

        store = Mockito.mock(Store.class);
        Map<Product, Integer> stock = new HashMap<>();
        stock.put(product, ModelsUtils.TEST_QUANTITY);
        when(store.getProductIntegerMap()).thenReturn(stock);
    }

    @Test
    @DisplayName("Testing method add and find consumer")
    void addAndFindConsumer() {
        service.addConsumer(consumer);
        Consumer found = service.findConsumer(TEST_NAME);
        assertNotNull(found,"Consumer can't be null");
        assertEquals(TEST_NAME,found.getName(),"Consumer must be equals");
    }

    @Test
    @DisplayName("Testing method remove consumer")
    void removeConsumer() {
        service.removeConsumer(TEST_NAME);
        assertNull(service.findConsumer(TEST_NAME), "Consumer doesn't not exist");
    }

    @Test
    @DisplayName("Testing success buy")
    void successBuy() {
        boolean successBuy = service.buyProduct(consumer,store,product,1);
        assertTrue(successBuy,"buy must be success");
        assertEquals(TEST_CONSUMER_MONEY - product.getPrice(),consumer.getMoney());
        assertEquals(TEST_QUANTITY - 1, store.getProductIntegerMap().get(product));
    }

    @Test
    @DisplayName("Testing enough money")
    void notEnoughMoney() {
        boolean successBuy = service.buyProduct(consumer,store,product,10);
        assertFalse(successBuy, "consumer must have money");
        assertEquals(TEST_CONSUMER_MONEY, consumer.getMoney());
    }
    @Test
    @DisplayName("Testing enough products")
    void notEnoughProduct() {
        boolean successBuy = service.buyProduct(consumer,store,product,TEST_QUANTITY + 1);
        assertFalse(successBuy,"product must be enough quantity");
        assertEquals(TEST_QUANTITY, store.getProductIntegerMap().get(product));
    }
}
