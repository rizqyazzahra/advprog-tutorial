package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        product.setProductId(String.valueOf(productData.size()));
        return product;
    }

    public Product edit(String productId, Product newProduct){
        Product targetProduct = findProductById(productId);

        if (targetProduct != null) {
            targetProduct.setProductName(newProduct.getProductName());
            targetProduct.setProductQuantity(newProduct.getProductQuantity());
        }

        return targetProduct;
    }

    public void delete(String productId) {
        Product productToDelete = findProductById(productId);
        productData.remove(productToDelete);
    }

    public Product findProductById(String productId) {
        for(Product currentProduct : productData){
            if(currentProduct.getProductId().equals(productId)){
                return currentProduct;
            }
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}