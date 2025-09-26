import entity.enums.Position;
import entity.impl.Consumer;
import entity.impl.Product;
import entity.impl.Store;
import entity.impl.Worker;
import service.WorkerService;
import service.impl.WorkerServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class EcommerceApplication {

    public static void main(String[] args) {
        Map<Product, Integer> productIntegerMap = new HashMap<>();
        Consumer consumer = new Consumer("Mike", 456.0);
        Product product = new Product("Milk", 1.23);
        productIntegerMap.put(product, 12);

        Store store = new Store("sdsd", "0000", "wer@gmail.com", productIntegerMap);

        Worker worker = new Worker("Sara", Position.SELLER);

        WorkerService workerService  = new WorkerServiceImpl();

    }
}
