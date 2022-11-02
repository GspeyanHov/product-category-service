package am.solidogyumri.productcategoryservice.mapper;

import am.solidogyumri.productcategoryservice.dto.CategoryResponseDto;
import am.solidogyumri.productcategoryservice.dto.CategoryUpdateDto;
import am.solidogyumri.productcategoryservice.dto.CategoryCreateDto;
import am.solidogyumri.productcategoryservice.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    List<CategoryResponseDto> map(List<Category> category);

    Category map(CategoryCreateDto categoryCreateDto);

    Category map(CategoryUpdateDto categoryUpdateDto);
}
