package am.solidogyumri.productcategoryservice.endpoint;

import am.solidogyumri.productcategoryservice.dto.CategoryCreateDto;
import am.solidogyumri.productcategoryservice.dto.CategoryResponseDto;
import am.solidogyumri.productcategoryservice.dto.CategoryUpdateDto;
import am.solidogyumri.productcategoryservice.entity.Category;
import am.solidogyumri.productcategoryservice.mapper.CategoryMapper;
import am.solidogyumri.productcategoryservice.security.CurrentUser;
import am.solidogyumri.productcategoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryEndpoint {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    @GetMapping()
    public List<CategoryResponseDto> getAllCategories() {
        return categoryMapper.map(categoryService.findAllCategories());
    }
    @PostMapping()
    public ResponseEntity<Category> addNewCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        categoryService.createCategory(categoryMapper.map(categoryCreateDto));
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        Optional<Category> byId = categoryService.findById(id);
        if(!byId.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") int id,
                                            @AuthenticationPrincipal CurrentUser currentUser,
                                            @RequestBody CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryMapper.map(categoryUpdateDto);
        if(currentUser.getUsername().equals(category.getUser().getEmail())) {
            category.setId(id);
            return ResponseEntity.ok(categoryService.createCategory(category));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") int id,
                                                @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<Category> byId = categoryService.findById(id);
        if(currentUser.getUsername().equals(byId.get().getUser().getEmail())) {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
