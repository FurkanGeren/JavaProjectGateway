package productservice.productservice.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import productservice.productservice.Entity.Product;
import productservice.productservice.Service.Interface.IProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/all")
    public List<Product> getAllProduct(){return iProductService.getAll();}


    @PutMapping("/make-favorite/{id}")
    public String favoriteProduct(@PathVariable Long id){return iProductService.makeFavorite(id);}

    @GetMapping("/favorites")
    public List<Product> getAllFavoriteProduct(){return iProductService.getFavorite();}













}
