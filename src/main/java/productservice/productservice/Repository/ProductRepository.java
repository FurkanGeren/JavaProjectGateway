package productservice.productservice.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productservice.productservice.Entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

    List<Product> findByIsHasBarcodeFalse();

    List<Product> findByIsFavoriteTrue();

}
