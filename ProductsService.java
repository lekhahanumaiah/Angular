//package com.example.demo.service;
//
//import com.example.demo.model.Products;
//import com.example.demo.repository.ProductsRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Comparator;
//import java.util.List;
//
//@Service
//public class ProductsService {
//
//    private final ProductsRepository repository;
//
//    public ProductsService(ProductsRepository repository) {
//        this.repository = repository;
//    }
//
//    // Save new product
//    public Products addProduct(Products product) {
//        return repository.save(product);
//    }
//
//    // Get all products sorted by name
//    public List<Products> getAllProductsSortedByName() {
//        return repository.findAll()
//                .stream()
//                .sorted(Comparator.comparing(
//                        p -> p.getName() == null ? "" : p.getName(),
//                        String.CASE_INSENSITIVE_ORDER))
//                .toList();
//    }
//
//    // Search products by name
//    public List<Products> searchProductsByName(String name) {
//        return repository.findByNameContainingIgnoreCase(name);
//    }
//
//    // Filter products
//    public List<Products> filterProducts(String category, Double minPrice, Double maxPrice) {
//        if (category != null && minPrice != null && maxPrice != null) {
//            return repository.findByCategoryIgnoreCaseAndPriceBetween(category, minPrice, maxPrice);
//        } else if (category != null) {
//            return repository.findByCategoryIgnoreCase(category);
//        } else if (minPrice != null && maxPrice != null) {
//            return repository.findByPriceBetween(minPrice, maxPrice);
//        } else {
//            return repository.findAll();
//        }
//    }
//}

package com.example.demo.service;
import com.example.demo.model.Products;
import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository repository;

    // Get all products sorted by name
    public List<Products>getAllProductsSortedByName() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Products::getName))
                .collect(Collectors.toList());
    }

    // Search by name
    public List<Products>searchProductsByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    // Filter by category and price range
    public List<Products>filterProducts(String category, Double minPrice, Double maxPrice) {
        return repository.findAll()
                .stream()
                .filter(p -> (category == null || p.getCategory().equalsIgnoreCase(category)))
                .filter(p -> (minPrice == null || p.getPrice() >= minPrice))
                .filter(p -> (maxPrice == null || p.getPrice() <= maxPrice))
                .sorted(Comparator.comparing(Products::getName))
                .collect(Collectors.toList());
    }

    // Save new product
    public Products addProduct(Products product) {
        return repository.save(product);
    }

    public Products getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
}
