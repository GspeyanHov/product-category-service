package am.solidogyumri.productcategoryservice.endpoint;

import am.solidogyumri.productcategoryservice.dto.ProductCreateDto;
import am.solidogyumri.productcategoryservice.dto.ProductResponseDto;
import am.solidogyumri.productcategoryservice.dto.ProductUpdateDto;
import am.solidogyumri.productcategoryservice.entity.Product;
import am.solidogyumri.productcategoryservice.mapper.ProductMapper;
import am.solidogyumri.productcategoryservice.security.CurrentUser;
import am.solidogyumri.productcategoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductEndpoint {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping()
    public List<ProductResponseDto> getAllProducts() {
        return productMapper.map(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Optional<Product> byId = productService.findById(id);
        if (!byId.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @PostMapping()
    public ResponseEntity<?> addNewProduct(@RequestBody ProductCreateDto productCreateDto) {
        Product product = productMapper.map(productCreateDto);
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") int id,
                                           @AuthenticationPrincipal CurrentUser currentUser,
                                           @RequestBody ProductUpdateDto productUpdateDto) {
        Product product = productMapper.map(productUpdateDto);
        if(currentUser.getUsername().equals(product.getUser().getEmail())){
            product.setId(id);
            return ResponseEntity.ok(productService.save(product));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@AuthenticationPrincipal CurrentUser currentUser,
                                               @PathVariable("id") int id) {
        Optional<Product> byId = productService.findById(id);
        if(currentUser.getUsername().equals(byId.get().getUser().getEmail())){
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/byCategory/{id}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable("id") int id) {
        List<Product> products = productService.getProducts(id);
        return ResponseEntity.ok(products);
    }
}

