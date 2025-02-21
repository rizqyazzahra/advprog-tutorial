package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEdit_Success() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Beni");
        updatedProduct.setProductQuantity(200);

        Product editedProduct = productRepository.edit(product.getProductId(), updatedProduct);

        assertNotNull(editedProduct);
        assertEquals(product.getProductId(), editedProduct.getProductId());
        assertEquals("Sampo Cap Beni", editedProduct.getProductName());
        assertEquals(200, editedProduct.getProductQuantity());

        Product foundProduct = productRepository.findProductById(product.getProductId());
        assertEquals("Sampo Cap Beni", foundProduct.getProductName());
        assertEquals(200, foundProduct.getProductQuantity());
    }

    @Test
    void testEdit_ProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Beni");
        updatedProduct.setProductQuantity(200);
        Product editedProduct = productRepository.edit("non-existent-id", updatedProduct);
        assertNull(editedProduct);
    }

    @Test
    void testEdit_MultipleEditsOnSameProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product firstUpdate = new Product();
        firstUpdate.setProductName("Sampo Cap Beni");
        firstUpdate.setProductQuantity(150);

        Product secondUpdate = new Product();
        secondUpdate.setProductName("Sampo Cap Doni");
        secondUpdate.setProductQuantity(250);
        Product secondEdit = productRepository.edit(product.getProductId(), secondUpdate);

        assertNotNull(secondEdit);
        assertEquals(product.getProductId(), secondEdit.getProductId());
        assertEquals("Sampo Cap Doni", secondEdit.getProductName());
        assertEquals(250, secondEdit.getProductQuantity());

        Product foundProduct = productRepository.findProductById(product.getProductId());
        assertEquals("Sampo Cap Doni", foundProduct.getProductName());
        assertEquals(250, foundProduct.getProductQuantity());
    }

    @Test
    void testDelete_Success() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findProductById(product.getProductId()));

        productRepository.delete(product.getProductId());

        assertNull(productRepository.findProductById(product.getProductId()));
        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testDelete_ProductNotFound() {
        assertDoesNotThrow(() -> productRepository.delete("non-existent-id"));
    }
    @Test
    void testDelete_MultipleProductsAndVerifyOthersRemain() {
        Product product1 = new Product();
        product1.setProductId("product-1");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("product-2");
        product2.setProductName("Sampo Cap Yayan");
        product2.setProductQuantity(45);
        productRepository.create(product2);

        productRepository.delete(product1.getProductId());

        assertNull(productRepository.findProductById(product1.getProductId()));

        Product remainingProduct = productRepository.findProductById(product2.getProductId());
        assertNotNull(remainingProduct);
        assertEquals(product2.getProductName(), remainingProduct.getProductName());
        assertEquals(product2.getProductQuantity(), remainingProduct.getProductQuantity());

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        productIterator.next();
        assertFalse(productIterator.hasNext());
    }
}