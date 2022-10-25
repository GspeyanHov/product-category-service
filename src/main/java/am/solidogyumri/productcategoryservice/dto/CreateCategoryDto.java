package am.solidogyumri.productcategoryservice.dto;

import am.solidogyumri.productcategoryservice.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryDto {

    private String name;
}
