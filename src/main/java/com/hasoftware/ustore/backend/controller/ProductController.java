package com.hasoftware.ustore.backend.controller;

import com.hasoftware.ustore.backend.entity.Product;
import com.hasoftware.ustore.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<Product> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/active")
    public Page<Product> getActiveProducts(Pageable pageable) {
        return productService.getActiveProducts(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{categoryId}")
    public Page<Product> getProductsByCategory(@PathVariable Long categoryId, Pageable pageable) {
        return productService.getProductsByCategory(categoryId, pageable);
    }

    @GetMapping("/brand/{brand}")
    public Page<Product> getProductsByBrand(@PathVariable String brand, Pageable pageable) {
        return productService.getProductsByBrand(brand, pageable);
    }

    @GetMapping("/featured")
    public Page<Product> getFeaturedProducts(Pageable pageable) {
        return productService.getFeaturedProducts(pageable);
    }

    @GetMapping("/new")
    public Page<Product> getNewProducts(Pageable pageable) {
        return productService.getNewProducts(pageable);
    }

    @GetMapping("/search")
    public Page<Product> searchProducts(@RequestParam String keyword, Pageable pageable) {
        return productService.searchProducts(keyword, pageable);
    }

    @GetMapping("/price-range")
    public Page<Product> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice,
            Pageable pageable) {
        return productService.getProductsByPriceRange(minPrice, maxPrice, pageable);
    }

    @GetMapping("/rating")
    public Page<Product> getProductsByMinRating(@RequestParam Double minRating, Pageable pageable) {
        return productService.getProductsByMinRating(minRating, pageable);
    }

    @GetMapping("/discounted")
    public Page<Product> getDiscountedProducts(Pageable pageable) {
        return productService.getDiscountedProducts(pageable);
    }

    @GetMapping("/in-stock")
    public Page<Product> getInStockProducts(Pageable pageable) {
        return productService.getInStockProducts(pageable);
    }

    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts() {
        return productService.getLowStockProducts();
    }

    @GetMapping("/out-of-stock")
    public List<Product> getOutOfStockProducts() {
        return productService.getOutOfStockProducts();
    }

    @GetMapping("/{id}/similar")
    public Page<Product> getSimilarProducts(@PathVariable Long id, Pageable pageable) {
        return productService.getSimilarProducts(id, pageable);
    }

    @GetMapping("/best-selling")
    public Page<Product> getBestSellingProducts(Pageable pageable) {
        return productService.getBestSellingProducts(pageable);
    }

    @GetMapping("/most-viewed")
    public Page<Product> getMostViewedProducts(Pageable pageable) {
        return productService.getMostViewedProducts(pageable);
    }

    @GetMapping("/filter")
    public Page<Product> getProductsWithFilters(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Boolean inStock,
            Pageable pageable) {
        return productService.getProductsWithFilters(categoryId, brand, minPrice, maxPrice, minRating, inStock,
                pageable);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
