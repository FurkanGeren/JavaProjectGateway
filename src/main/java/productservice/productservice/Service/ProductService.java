package productservice.productservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productservice.productservice.Entity.Product;
import productservice.productservice.Repository.ProductRepository;
import productservice.productservice.Service.Interface.IProductService;

import java.util.List;

@Service
public class ProductService implements IProductService {

   @Autowired
   private ProductRepository productRepository;



    @Override
    public List<Product> getAll(){return productRepository.findByIsHasBarcodeFalse();}

    @Override
    public String makeFavorite(Long id) throws RuntimeException{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setFavorite(true);
        productRepository.save(product);

        return "Product made favorite";
    }

    @Override
    public List<Product> getFavorite(){return productRepository.findByIsFavoriteTrue();}


}
