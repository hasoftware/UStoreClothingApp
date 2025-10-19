package com.hasoftware.ustore.backend.repository;

import com.hasoftware.ustore.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find by category
    Page<Product> findByCategoryIdAndIsActiveTrue(Long categoryId, Pageable pageable);

    // Find by brand
    Page<Product> findByBrandAndIsActiveTrue(String brand, Pageable pageable);

    // Find featured products
    Page<Product> findByIsFeaturedTrueAndIsActiveTrue(Pageable pageable);

    // Find new products
    Page<Product> findByIsNewTrueAndIsActiveTrue(Pageable pageable);

    // Search products by name or description
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND " +
            "(LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Product> searchProducts(@Param("keyword") String keyword, Pageable pageable);

    // Find products by price range
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND " +
            "p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByPriceRange(@Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable);

    // Find products by rating
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND p.rating >= :minRating")
    Page<Product> findByMinRating(@Param("minRating") Double minRating, Pageable pageable);

    // Find products with discount
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND p.discountPercentage > 0")
    Page<Product> findDiscountedProducts(Pageable pageable);

    // Find products in stock
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND p.stockQuantity > 0")
    Page<Product> findInStockProducts(Pageable pageable);

    // Find low stock products
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND " +
            "p.stockQuantity <= p.minStockLevel AND p.stockQuantity > 0")
    List<Product> findLowStockProducts();

    // Find out of stock products
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND p.stockQuantity = 0")
    List<Product> findOutOfStockProducts();

    // Find by SKU
    Optional<Product> findBySku(String sku);

    // Check if SKU exists
    Boolean existsBySku(String sku);

    // Find similar products (same category, different product)
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND " +
            "p.id != :productId AND p.isActive = true")
    List<Product> findSimilarProducts(@Param("categoryId") Long categoryId,
            @Param("productId") Long productId,
            Pageable pageable);

    // Find best selling products
    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.soldCount DESC")
    Page<Product> findBestSellingProducts(Pageable pageable);

    // Find most viewed products
    @Query("SELECT p FROM Product p WHERE p.isActive = true ORDER BY p.viewCount DESC")
    Page<Product> findMostViewedProducts(Pageable pageable);

    // Find active products
    Page<Product> findByIsActiveTrue(Pageable pageable);

    // Find products by multiple criteria
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND " +
            "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
            "(:brand IS NULL OR p.brand = :brand) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:minRating IS NULL OR p.rating >= :minRating) AND " +
            "(:inStock IS NULL OR (:inStock = true AND p.stockQuantity > 0) OR (:inStock = false AND p.stockQuantity = 0))")
    Page<Product> findProductsWithFilters(@Param("categoryId") Long categoryId,
            @Param("brand") String brand,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("minRating") Double minRating,
            @Param("inStock") Boolean inStock,
            Pageable pageable);
}
