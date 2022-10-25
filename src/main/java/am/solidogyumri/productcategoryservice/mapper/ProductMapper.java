package am.solidogyumri.productcategoryservice.mapper;

import am.solidogyumri.productcategoryservice.dto.CategoryUpdateDto;
import am.solidogyumri.productcategoryservice.dto.CreateProductDto;
import am.solidogyumri.productcategoryservice.dto.ProductResponseDto;
import am.solidogyumri.productcategoryservice.dto.UpdateProductDto;
import am.solidogyumri.productcategoryservice.entity.Category;
import am.solidogyumri.productcategoryservice.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductResponseDto> map(List<Product> products);

    Product map(CreateProductDto createProductDto);

    Product map(UpdateProductDto updateProductDto);
}
