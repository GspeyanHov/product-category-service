package am.solidogyumri.productcategoryservice.repository;

import am.solidogyumri.productcategoryservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
