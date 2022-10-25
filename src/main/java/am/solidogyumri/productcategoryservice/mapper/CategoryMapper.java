package am.solidogyumri.productcategoryservice.mapper;

import am.solidogyumri.productcategoryservice.dto.CategoryResponseDto;
import am.solidogyumri.productcategoryservice.dto.CategoryUpdateDto;
import am.solidogyumri.productcategoryservice.dto.CreateCategoryDto;
import am.solidogyumri.productcategoryservice.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryResponseDto> map(List<Category> category);

    Category map(CreateCategoryDto createCategoryDto);

    Category map(CategoryUpdateDto categoryUpdateDto);
}
