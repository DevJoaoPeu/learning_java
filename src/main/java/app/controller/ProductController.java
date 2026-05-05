package app.controller;

import app.model.Product;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import app.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController (ProductService service) {
        productService = service;
    }

    @GetMapping
    public List<Product> findAll () {
        return productService.findAll();
    }

    @GetMapping("/search/{price}")
    public List<Product> findByPrice (@PathVariable double price) {
        return productService.findByPrice(price);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public Product create(@RequestBody @Valid Product product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody @Valid Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
