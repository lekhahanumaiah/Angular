////package com.example.demo.model;
////
////import com.fasterxml.jackson.annotation.JsonProperty;
////import jakarta.persistence.*;
////
////@Entity
////@Table(name = "products")
////public class Products {
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    private String name;
////    private Double price;
////    private String category;
////
////    @Column(length = 1000)
////    private String description;
////
////    @Column(name = "image_url")                      // map DB column
////    @JsonProperty("imageUrl")                        // map JSON field
////    private String imageUrl;
////
////    public Products() {}
////
////    public Products(String name, Double price, String category, String description, String imageUrl) {
////        this.name = name;
////        this.price = price;
////        this.category = category;
////        this.description = description;
////        this.imageUrl = imageUrl;
////    }
////
////    // Getters & Setters
////    public Long getId() { return id; }
////    public void setId(Long id) { this.id = id; }
////
////    public String getName() { return name; }
////    public void setName(String name) { this.name = name; }
////
////    public Double getPrice() { return price; }
////    public void setPrice(Double price) { this.price = price; }
////
////    public String getCategory() { return category; }
////    public void setCategory(String category) { this.category = category; }
////
////    public String getDescription() { return description; }
////    public void setDescription(String description) { this.description = description; }
////
////    public String getImageUrl() { return imageUrl; }
////    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
////}
//
//package com.example.demo.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "products")
//public class Products {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private Double price;
//    private String category;
//
//    @Column(length = 1000) // allow longer text
//    private String description;
//
//    private String imageUrl;
//
//    public Products() {}
//
//    public Products(String name, Double price, String category, String description, String imageUrl) {
//        this.name = name;
//        this.price = price;
//        this.category = category;
//        this.description = description;
//        this.imageUrl = imageUrl;
//    }
//
//    // Getters & Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public Double getPrice() { return price; }
//    public void setPrice(Double price) { this.price = price; }
//
//    public String getCategory() { return category; }
//    public void setCategory(String category) { this.category = category; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public String getImageUrl() { return imageUrl; }
//    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
//}
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String category;

    @Column(length = 1000) // allow longer text
    private String description;

    private String imageUrl;

    // If you don’t need to expose carts, you can remove this field
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore   // Prevent infinite recursion
    private List<Cart> carts;

    public Products() {}

    public Products(String name, Double price, String category, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<Cart> getCarts() { return carts; }
    public void setCarts(List<Cart> carts) { this.carts = carts; }
}

