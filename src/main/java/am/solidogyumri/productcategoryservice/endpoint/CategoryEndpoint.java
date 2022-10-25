package am.solidogyumri.productcategoryservice.endpoint;

import am.solidogyumri.productcategoryservice.dto.CategoryUpdateDto;
import am.solidogyumri.productcategoryservice.dto.CreateCategoryDto;
import am.solidogyumri.productcategoryservice.entity.Category;
import am.solidogyumri.productcategoryservice.entity.Product;
import am.solidogyumri.productcategoryservice.mapper.CategoryMapper;
import am.solidogyumri.productcategoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryEndpoint {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @PostMapping("/category")
    public ResponseEntity <Category> addNewCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        Category category = categoryService.createCategory(categoryMapper.map(createCategoryDto));
        return ResponseEntity.ok(category);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        Optional<Category> byId = categoryService.findById(id);
        if(!byId.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }
    @PutMapping("/category")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto){
        if(categoryUpdateDto == null){
            return ResponseEntity.badRequest().build();
        }
        Category category = categoryService.createCategory(categoryMapper.map(categoryUpdateDto));
        return ResponseEntity.ok(category);
    }
    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") int id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
