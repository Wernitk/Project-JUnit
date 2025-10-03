package service;

import entity.enums.Position;
import entity.impl.Consumer;
import entity.impl.Product;
import entity.impl.Store;
import entity.impl.Worker;

import java.util.*;
import java.util.function.Supplier;

public class ModelsUtils {
    public static final List<String> NAMES = List.of(
            "Alex", "John", "Sophia", "Emma", "Daniel", "Olivia", "James", "Mia"
    );
    public static final List<String> NAMES_OF_PRODUCTS = List.of(
      "Milk", "Egg", "Pork", "Salmon", "Meat", "Orange", "Cucumber"
    );
    public static final List<Integer> QUANTITY = List.of(
            12, 15, 17, 18, 19, 20, 21, 22, 23, 24, 25
    );
    public static final List<String> EMAILS_NAME = List.of(
            "wer@gmail.com", "sd@gmail.com", "werw@ukr.net"
    );
    public static final List<String> ADDRESS = List.of(
            "Wall street 1", "Wall street 2", "Wall street 3"
    );
    public static final List<String> PHONE = List.of(
            "+320942322312", "+380967672211", "+380675432365"
    );
    public static final Random RANDOM = new Random();
    public static final String TEST_NAME = "Mike";
    public static final String TEST_PRODUCT_NAME = "Milk";
    public static final double TEST_PRODUCT_PRICE = 12;
    public static final double TEST_CONSUMER_MONEY = 100;
    public static final String TEST_ADDRESS_NAME = "Wall street";
    public static final String TEST_PHONE = "+380967672211";
    public static final String TEST_EMAIL = "wer@gmail.com";
    public static final Integer TEST_QUANTITY = 100;
    public static final Map<Product, Integer> TEST_PRODUCTS = new HashMap<>(
            Map.of(getProduct(), TEST_QUANTITY)
    );
    public static <T> T getRandomElement(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    public static <T> List<T> getRandomList(Supplier<T> supplier, int count) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(supplier.get());
        }
        return result;
    }

    public static Worker getWorker() {
        return new Worker(TEST_NAME, Position.SELLER);
    }

    public static Product getProduct() {
        return new Product(TEST_PRODUCT_NAME, TEST_PRODUCT_PRICE);
    }

    public static Consumer getConsumer() {
        return new Consumer(TEST_NAME, TEST_CONSUMER_MONEY);
    }

    public static Store getStore() {
        return new Store(TEST_ADDRESS_NAME, TEST_PHONE, TEST_EMAIL, Map.of(getProduct(), TEST_QUANTITY));
    }
}
