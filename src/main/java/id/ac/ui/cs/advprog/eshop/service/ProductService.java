package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public Product getProductById(String productId);
    public Product edit(String productId, Product newProductData);
    public void deleteProductById(String productId);
}