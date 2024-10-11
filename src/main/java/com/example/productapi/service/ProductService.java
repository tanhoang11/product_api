package com.example.productapi.service;
import com.example.productapi.models.Product;
import com.example.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.productapi.exception.ProductNotFoundException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    //lấy all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
    }
//    public Product getProductById(Integer productId) {
//        return productRepository.findById(productId).orElse(null);
//    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer productId, Product productDetails) {
        Product product = getProductById(productId);
        product.setProduct_name(productDetails.getProduct_name());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setCategory_id(productDetails.getCategory_id());
        product.setImage_url(productDetails.getImage_url());
        return productRepository.save(product);
    }


    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}