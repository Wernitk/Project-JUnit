package service.impl;

import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ModelsUtils;
import service.ProductService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceImplTest
{
    private ProductService productService;
    private Store store;
    private Product product;

    @BeforeEach
    void setUp()
    {
        productService = new ProductServiceImpl();
        product = ModelsUtils.getProduct();
        store = ModelsUtils.getStore();

        Map<Product, Integer> productMap = new HashMap<>(store.getProductIntegerMap());
        store.setProductIntegerMap(productMap);
    }

    @Test
    @DisplayName("Add a product")
    void testAddProduct()
    {
        Product newProduct = new Product("Egg", 5.0);
        productService.addProduct(store, newProduct, 10);

        assertTrue(store.getProductIntegerMap().containsKey(newProduct),
                "Store should contain the newly added product");
        assertEquals(10, store.getProductIntegerMap().get(newProduct),
                "Quantity should be equal to added amount");
    }

    @Test
    @DisplayName("Merge quantities when product already exists")
    void testAddProductMergeQuantities()
    {
        productService.addProduct(store, product, 5);
        int expected = ModelsUtils.TEST_QUANTITY + 5;

        assertEquals(expected, store.getProductIntegerMap().get(product),
                "Quantity should be increased by 5");
    }

    @Test
    @DisplayName("Remove product")
    void testRemoveProduct()
    {
        productService.removeProduct(store, product);

        assertFalse(store.getProductIntegerMap().containsKey(product),
                "Product should be removed from the store");
    }

    @Test
    @DisplayName("Update product price")
    void testUpdatePrice()
    {
        double newPrice = 20.0;
        productService.updatePrice(product, newPrice);

        assertEquals(newPrice, product.getPrice(), 0.001,
                "Product price should be updated correctly");
    }

    @Test
    @DisplayName("Get existing product")
    void testGetProduct()
    {
        Product found = productService.getProduct(store, product);

        assertNotNull(found, "Existing product should be found");
        assertEquals(product.getName(), found.getName(),
                "Product names should match");
    }

    @Test
    @DisplayName("Return null when product not found")
    void testGetProductNotFound()
    {
        Product nonExisting = new Product("Banana", 10.0);
        Product found = productService.getProduct(store, nonExisting);

        assertNull(found, "Should return null if product is not found in store");
    }

    @Test
    @DisplayName("Get product by name (case-insensitive)")
    void testGetProductByName()
    {
        Product found = productService.getProductByName(store, ModelsUtils.TEST_PRODUCT_NAME.toUpperCase());

        assertNotNull(found, "Product should be found by name");
        assertEquals(product.getName(), found.getName(), "Product name should match");
    }

    @Test
    @DisplayName("Return null when product name does not exist")
    void testGetProductByNameNotFound()
    {
        Product found = productService.getProductByName(store, "NonExisting");

        assertNull(found, "Should return null for non-existing product name");
    }
}
