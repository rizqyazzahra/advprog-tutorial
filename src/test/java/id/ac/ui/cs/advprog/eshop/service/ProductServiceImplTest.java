package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private final String TEST_UUID = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        productService.create(product);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        when(productRepository.findProductById(TEST_UUID)).thenReturn(product);

        Product result = productService.getProductById(TEST_UUID);
        assertEquals(product, result);
        verify(productRepository, times(1)).findProductById(TEST_UUID);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        productService.edit(TEST_UUID, product);
        verify(productRepository, times(1)).edit(TEST_UUID, product);
    }

    @Test
    void testDeleteProductById() {
        productService.deleteProductById(TEST_UUID);
        verify(productRepository, times(1)).delete(TEST_UUID);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> result = productService.findAll();
        assertEquals(productList, result);
        verify(productRepository, times(1)).findAll();
    }
}