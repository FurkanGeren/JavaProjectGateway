package productservice.productservice.Service.Interface;

import org.springframework.stereotype.Service;
import productservice.productservice.Entity.Product;

import java.util.List;

@Service
public interface IProductService {
    List<Product> getAll();

    String makeFavorite(Long id) throws RuntimeException;

    List<Product> getFavorite();
}
