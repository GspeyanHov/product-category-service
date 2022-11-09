package am.solidogyumri.productcategoryservice.service;

import am.solidogyumri.productcategoryservice.entity.Category;
import am.solidogyumri.productcategoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }
    public Category createCategory(Category category){
        if (category == null) {
            throw new RuntimeException("category can not be null");
        }
        return categoryRepository.save(category);
    }
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
