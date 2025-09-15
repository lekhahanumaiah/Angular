//package com.example.demo.controller;
//
//import com.example.demo.model.Products;
//import com.example.demo.service.ProductsService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/products")
//@CrossOrigin(origins = "http://localhost:4200")
//@SuppressWarnings("unused")
//public class ProductsController {
//
//    private final ProductsService productsService;
//
//    public ProductsController(ProductsService productsService) {
//        this.productsService = productsService;
//    }
//
//    // Add product
//    @PostMapping
//    public Products addProduct(@RequestBody Products product) {
//        return productsService.addProduct(product);
//    }
//
//    // Get products with optional search/filter parameters
//    @GetMapping
//    public List<Products> getProducts(
//            @RequestParam(required = false) String search,
//            @RequestParam(required = false) String category,
//            @RequestParam(required = false) Double minPrice,
//            @RequestParam(required = false) Double maxPrice) {
//
//        if (search != null && !search.isEmpty()) {
//            return productsService.searchProductsByName(search);
//        } else if (category != null || minPrice != null || maxPrice != null) {
//            return productsService.filterProducts(category, minPrice, maxPrice);
//        } else {
//            return productsService.getAllProductsSortedByName();
//        }
//    }

    // You can remove these if you want, as the above method covers their functionality
    /*
    @GetMapping("/search")
    public List<Products> searchProducts(@RequestParam String name) {
        return productsService.searchProductsByName(name);
    }

    @GetMapping("/filter")
    public List<Products> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return productsService.filterProducts(category, minPrice, maxPrice);
    }
    */
//}
package com.example.demo.controller;
import com.example.demo.model.Products;
import com.example.demo.service.ProductsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // allow frontend access
public class ProductsController {

    @Autowired
    private ProductsService service;

    // List all products, search, filter
    @GetMapping
    public List<Products>getAllProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        if (search != null && !search.isEmpty()) {
            return service.searchProductsByName(search);
        }
        if (category != null || minPrice != null || maxPrice != null) {
            return service.filterProducts(category, minPrice, maxPrice);
        }
        return service.getAllProductsSortedByName();
    }

    // Add a new product
    @PostMapping
    public Products addProduct(@RequestBody Products product) {
        return service.addProduct(product);
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

}

