package am.solidogyumri.productcategoryservice.service;

import am.solidogyumri.productcategoryservice.entity.Product;
import am.solidogyumri.productcategoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product save(Product product){
        if(product == null){
            throw new RuntimeException("book can not be null");
        }
        return productRepository.save(product);
    }

    public Optional <Product> findById(int id) {
        return productRepository.findById(id);
    }
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProducts(int categoryId){
        List<Product> productList = productRepository.findProductsByCategoryId(categoryId);
        if(productList.isEmpty()){
            return null;
        }
        return productList;
    }
}


