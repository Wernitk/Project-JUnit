package service.impl;

import entity.impl.Consumer;
import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ModelsUtils;

import java.util.Map;

import static service.ModelsUtils.TEST_NAME;
import static service.ModelsUtils.getConsumer;

public class ConsumerServiceImplTest {
    private ConsumerServiceImpl consumerService;

    @BeforeEach
    void setUp() {
        consumerService = new ConsumerServiceImpl();
    }

    @Test
    @DisplayName("Should add and find consumer successfully")
    void addAndFindConsumer() {
        Consumer consumer = ModelsUtils.getConsumer();

        consumerService.addConsumer(consumer);
        Consumer foundConsumer = consumerService.findConsumer(consumer.getName());

        assert foundConsumer != null;
        assert foundConsumer.getName().equals(TEST_NAME);
        assert foundConsumer.getMoney() == ModelsUtils.TEST_CONSUMER_MONEY;
    }

    @Test
    @DisplayName("Should remove consumer successfully")
    void removeConsumer() {
        Consumer consumer = ModelsUtils.getConsumer();

        consumerService.addConsumer(consumer);
        consumerService.removeConsumer(consumer.getName());

        assert consumerService.findConsumer(consumer.getName()) == null;
    }

    @Test
    @DisplayName("Should buy product successfully")
    void buyProductSuccess() {
        Consumer consumer = ModelsUtils.getConsumer();
        Product product = ModelsUtils.getProduct();
//        Store store = ModelsUtils.getStore();
        Store store = new Store(
                ModelsUtils.TEST_ADDRESS_NAME,
                ModelsUtils.TEST_PHONE,
                ModelsUtils.TEST_EMAIL,
                new java.util.HashMap<>(Map.of(product, ModelsUtils.TEST_QUANTITY))
        );

        boolean result = consumerService.buyProduct(consumer, store, product, 5);

        assert result;
        assert consumer.getMoney() == ModelsUtils.TEST_CONSUMER_MONEY - (product.getPrice() * 5);
        assert store.getProductIntegerMap().get(product) == ModelsUtils.TEST_QUANTITY - 5;
    }

    @Test
    @DisplayName("Should fail to buy product due to insufficient stock")
    void buyProductFailInsufficientStock() {
        Consumer consumer = getConsumer();
        Store store = ModelsUtils.getStore();
        Product product = ModelsUtils.getProduct();

        boolean result = consumerService.buyProduct(consumer, store, product, 300);

        assert !result;
        assert consumer.getMoney() == ModelsUtils.TEST_CONSUMER_MONEY;
        assert store.getProductIntegerMap().get(product).equals(ModelsUtils.TEST_QUANTITY);
    }

    @Test
    @DisplayName("Should fail to buy product due to insufficient funds")
    void buyProductFailInsufficientFunds() {
        Consumer consumer = new Consumer(TEST_NAME, 0.1);
        Store store = ModelsUtils.getStore();
        Product product = ModelsUtils.getProduct();

        boolean result = consumerService.buyProduct(consumer, store, product, 150);

        assert !result;
        assert consumer.getMoney() == 0.1;
        assert store.getProductIntegerMap().get(product).equals(ModelsUtils.TEST_QUANTITY);
    }
}
