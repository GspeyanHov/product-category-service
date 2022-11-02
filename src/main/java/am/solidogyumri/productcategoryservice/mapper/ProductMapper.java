package am.solidogyumri.productcategoryservice.mapper;

import am.solidogyumri.productcategoryservice.dto.ProductCreateDto;
import am.solidogyumri.productcategoryservice.dto.ProductResponseDto;
import am.solidogyumri.productcategoryservice.dto.ProductUpdateDto;
import am.solidogyumri.productcategoryservice.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductResponseDto> map(List<Product> products);

    Product map(ProductCreateDto productCreateDto);

    Product map(ProductUpdateDto productUpdateDto);

}
