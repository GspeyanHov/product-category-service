package am.solidogyumri.productcategoryservice.endpoint;

import am.solidogyumri.productcategoryservice.dto.CreateProductDto;
import am.solidogyumri.productcategoryservice.dto.ProductResponseDto;
import am.solidogyumri.productcategoryservice.dto.UpdateProductDto;
import am.solidogyumri.productcategoryservice.entity.Product;
import am.solidogyumri.productcategoryservice.mapper.ProductMapper;
import am.solidogyumri.productcategoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductEndpoint {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/product")
    public List<ProductResponseDto> getAllProducts(){
       return productService.getAllProducts();
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getBookById(@PathVariable("id") int id) {
        Optional<Product> byId = productService.findById(id);
        if(!byId.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }
    @PostMapping("/product")
    public ResponseEntity<Product> addNewProduct(@RequestBody CreateProductDto createProductDto){
        Product product = productService.save(productMapper.map(createProductDto));
        return ResponseEntity.ok(product);
    }
    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductDto updateProductDto){
        if(updateProductDto == null){
            return ResponseEntity.badRequest().build();
        }
        Product product = productService.save(productMapper.map(updateProductDto));
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") int id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/product/byCategory/{id}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable("id") int id){
        List<Product> products = productService.getProducts(id);
        return ResponseEntity.ok(products);
    }
}
