package am.solidogyumri.productcategoryservice.service;

import am.solidogyumri.productcategoryservice.entity.Category;
import am.solidogyumri.productcategoryservice.entity.Product;
import am.solidogyumri.productcategoryservice.entity.User;
import am.solidogyumri.productcategoryservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private  ProductService productService;

    @Test
    void getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        when(productRepository.findAll()).thenReturn(allProducts);
    }

    @Test
    void save() {

        Product product = Product.builder()
                .id(1)
                .category(new Category())
                .count(33)
                .user(new User())
                .title("title1")
                .price(570)
                .build();
        when(productRepository.save(any())).thenReturn(product);

        productService.save(Product.builder()
                .category(new Category())
                .count(33)
                .user(new User())
                .title("title1")
                .price(570)
                .build());
        verify(productRepository,times(1)).save(any());

    }
    @Test
    void save_null() {
        Product product = Product.builder()
                .id(1)
                .category(new Category())
                .count(33)
                .user(new User())
                .title("title1")
                .price(570)
                .build();
        when(productRepository.save(any())).thenReturn(product);

        assertThrows(RuntimeException.class,()-> {
            productService.save(null);
        });
        verify(productRepository, times(0)).save(any());

    }

    @Test
    void findById() {
        Product product = new Product();
        when(productRepository.findById(product.getId()))
                .thenReturn(Optional.of(product));

       Optional<Product> productById = productService.findById(product.getId());
        assertNotNull(productById);
        int id = productById.get().getId();
        assertEquals(product.getId(), id);
    }

    @Test
    void deleteById() {
        Product product = new Product();
        when(productService.findById(product.getId())).thenReturn(Optional.of(product));
        productService.deleteById(product.getId());
    }
    @Test
    void updateProduct(){
        Product product = new Product();
        when(productRepository.findById(product.getId()))
                .thenReturn(Optional.of(product));

        Optional<Product> productById = productService.findById(product.getId());
        assertNotNull(productById);
        int id = productById.get().getId();
        assertEquals(product.getId(), id);
        productService.save(product);
    }

}