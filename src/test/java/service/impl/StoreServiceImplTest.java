package service.impl;

import entity.impl.Product;
import entity.impl.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static service.ModelsUtils.*;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {
    // NOTE: Inject mocks into service
    @InjectMocks
    private StoreServiceImpl storeService;
    // NOTE: Create a new mock
    @Mock
    private ProductService productService;

    @Test
    @DisplayName("Add a product to the store")
    void addProductToStore() {
        // given
        Store store = getStore();
        Product product = getProduct();

        // when
        when(productService.getProduct(store, product)).thenReturn(product);

        storeService.addProductToStore(store, product, TEST_QUANTITY);

        // then
        assertTrue(store.getProductIntegerMap().containsKey(product));
        assertEquals(100, store.getProductIntegerMap().get(product));

        verify(productService, times(1)).addProduct(store, product, TEST_QUANTITY);
    }

    // NOTE: Common RepeatedTest
    @RepeatedTest(5)
    @DisplayName("Add a product to the store")
    void addProductToStoreRepetition() {
        // given
        Store store = getStore();
        Product product = getProduct();

        when(productService.getProduct(store, product)).thenReturn(product);

        // when
        storeService.addProductToStore(store, product, 5);

        // then
        assertTrue(store.getProductIntegerMap().containsKey(product));
        assertEquals(100, store.getProductIntegerMap().get(product));

        verify(productService, times(1)).getProduct(store, product);
    }

    // NOTE: RepeatedTest with parametrization
    @RepeatedTest(value = 5, name = "Add a product to the store {currentRepetition}/{totalRepetitions}")
    @DisplayName("Add a product to the store")
    void addProductToStoreRepetitionSecond() {
        // given
        Store store = getStore();
        Product product = getProduct();

        when(productService.getProduct(store, product)).thenReturn(product);

        // when
        storeService.addProductToStore(store, product, 5);

        // then
        assertTrue(store.getProductIntegerMap().containsKey(product));
        assertEquals(100, store.getProductIntegerMap().get(product));

        verify(productService, times(1)).getProduct(store, product);
    }

}