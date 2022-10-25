package am.solidogyumri.productcategoryservice.repository;

import am.solidogyumri.productcategoryservice.entity.Category;
import am.solidogyumri.productcategoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductsByCategoryId(int categoryId);

}
