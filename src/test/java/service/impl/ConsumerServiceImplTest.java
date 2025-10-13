package service.impl;

import entity.impl.Consumer;
import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.ConsumerService;
import service.ModelsUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsumerServiceImplTest
{
    private ConsumerService consumerService;
    private Consumer consumer;
    private Product product;
    private Store store;

    @BeforeEach
    void setUp()
    {
        consumerService = new ConsumerServiceImpl();

        consumer = ModelsUtils.getConsumer();
        product = ModelsUtils.getProduct();

        store = Mockito.mock(Store.class);

        Map<Product, Integer> stock = new HashMap<>();
        stock.put(product, ModelsUtils.TEST_QUANTITY);
        when(store.getProductIntegerMap()).thenReturn(stock);
    }

    @Test
    @DisplayName("Add and find a consumer")
    void testAddAndFindConsumer()
    {
        consumerService.addConsumer(consumer);
        Consumer found = consumerService.findConsumer(ModelsUtils.TEST_NAME);

        assertNotNull(found, "Consumer should be found after adding");
        assertEquals(ModelsUtils.TEST_NAME, found.getName(), "Consumer name should match");
    }

    @Test
    @DisplayName("Remove a consumer")
    void testRemoveConsumer()
    {
        consumerService.addConsumer(consumer);
        consumerService.removeConsumer(ModelsUtils.TEST_NAME);

        assertNull(consumerService.findConsumer(ModelsUtils.TEST_NAME), "Consumer should be removed");
    }

    @Test
    @DisplayName("Buy product when consumer has enough money and stock is available")
    void testBuyProductSuccess()
    {
        boolean result = consumerService.buyProduct(consumer, store, product, 2);

        assertTrue(result, "Purchase should be successful");
        assertEquals(ModelsUtils.TEST_CONSUMER_MONEY - product.getPrice() * 2, consumer.getMoney(), 0.001,
                "Money should decrease correctly");
        assertEquals(ModelsUtils.TEST_QUANTITY - 2, store.getProductIntegerMap().get(product),
                "Store stock should decrease correctly");
    }

    @Test
    @DisplayName("Fail to buy product when not enough stock")
    void testBuyProductNotEnoughStock()
    {
        consumerService.addConsumer(consumer);
        boolean result = consumerService.buyProduct(consumer, store, product, ModelsUtils.TEST_QUANTITY + 50);

        assertFalse(result, "Purchase should fail if stock is insufficient");
        assertEquals(ModelsUtils.TEST_CONSUMER_MONEY, consumer.getMoney(), 0.001, "Money should remain unchanged");
    }

    @Test
    @DisplayName("Fail to buy product when not enough money")
    void testBuyProductNotEnoughMoney()
    {
        consumer.setMoney(5.0);
        consumerService.addConsumer(consumer);
        boolean result = consumerService.buyProduct(consumer, store, product, 1);

        assertFalse(result, "Purchase should fail if consumer has insufficient funds");
        assertEquals(5.0, consumer.getMoney(), 0.001, "Money should remain unchanged");
    }
}
