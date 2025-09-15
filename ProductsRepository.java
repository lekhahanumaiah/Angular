//package com.example.demo.repository;
//import com.example.demo.model.Products;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import java.util.List;
//
//@Repository
//public interface ProductsRepository extends JpaRepository<Products, Long> {
//    List<Products>findByNameContainingIgnoreCase(String name);
//    List<Products>findByCategoryIgnoreCase(String category);
//    List<Products>findByPriceBetween(Double minPrice, Double maxPrice);
//
//    List<Products> findByCategoryIgnoreCaseAndPriceBetween(String category, Double minPrice, Double maxPrice);
//}
//
package com.example.demo.repository;
import com.example.demo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products>findByNameContainingIgnoreCase(String name);
    List<Products>findByCategoryIgnoreCase(String category);
    List<Products>findByPriceBetween(Double minPrice, Double maxPrice);
}
