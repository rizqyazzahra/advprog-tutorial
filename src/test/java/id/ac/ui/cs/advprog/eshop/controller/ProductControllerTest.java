package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    private final String TEST_UUID = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String view = productController.createProductPage(model);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
        assertEquals("CreateProduct", view);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String view = productController.createProductPost(product, model);
        verify(productService, times(1)).create(product);
        assertEquals("redirect:list", view);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);

        String view = productController.productListPage(model);
        verify(model, times(1)).addAttribute("products", productList);
        assertEquals("ProductList", view);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(productService.getProductById(TEST_UUID)).thenReturn(product);

        String view = productController.editProductPage(model, TEST_UUID);
        verify(model, times(1)).addAttribute("product", product);
        assertEquals("EditProduct", view);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        product.setProductId(TEST_UUID);

        String view = productController.editProductPost(product, model, TEST_UUID);
        verify(productService, times(1)).edit(TEST_UUID, product);
        assertEquals("redirect:../list", view);
    }

    @Test
    void testDeleteProductPage() {
        String view = productController.deleteProductPage(model, TEST_UUID);
        verify(productService, times(1)).deleteProductById(TEST_UUID);
        assertEquals("redirect:/product/list", view);
    }
}