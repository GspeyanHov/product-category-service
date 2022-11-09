package am.solidogyumri.productcategoryservice.service;

import am.solidogyumri.productcategoryservice.entity.Category;
import am.solidogyumri.productcategoryservice.entity.User;
import am.solidogyumri.productcategoryservice.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    void getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        when(categoryRepository.findAll()).thenReturn(allCategories);
    }

    @Test
    void save() {
        Category category = Category.builder()
                .id(1)
                .name("name")
                .user(new User())
                .build();
        when(categoryRepository.save(any())).thenReturn(category);

        categoryService.createCategory(Category.builder()
                .user(new User())
                .build());
        verify(categoryRepository, times(1)).save(any());

    }

    @Test
    void save_null() {
        Category category = Category.builder()
                .id(1)
                .user(new User())
                .build();
        when(categoryRepository.save(any())).thenReturn(category);

        assertThrows(RuntimeException.class, () -> {
            categoryService.createCategory(null);
        });
        verify(categoryRepository, times(0)).save(any());

    }

    @Test
    void findById() {
        Category category = new Category();
        when(categoryRepository.findById(category.getId()))
                .thenReturn(Optional.of(category));

        Optional<Category> categoryById = categoryService.findById(category.getId());
        assertNotNull(categoryById);
        int id = categoryById.get().getId();
        assertEquals(category.getId(), id);
    }

    @Test
    void deleteById() {
        Category category = new Category();
        when(categoryService.findById(category.getId())).thenReturn(Optional.of(category));
        categoryService.deleteById(category.getId());
    }

    @Test
    void updateProduct() {
        Category category = new Category();
        when(categoryRepository.findById(category.getId()))
                .thenReturn(Optional.of(category));

        Optional<Category> categoryById = categoryService.findById(category.getId());
        assertNotNull(categoryById);
        int id = categoryById.get().getId();
        assertEquals(category.getId(), id);
        categoryService.createCategory(category);
    }

}