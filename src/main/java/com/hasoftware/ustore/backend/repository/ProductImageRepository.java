package com.hasoftware.ustore.backend.repository;

import com.hasoftware.ustore.backend.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    // Find images by product
    List<ProductImage> findByProductIdOrderBySortOrderAsc(Long productId);

    // Find primary image of product
    Optional<ProductImage> findByProductIdAndIsPrimaryTrue(Long productId);

    // Find first image of product (if no primary)
    @Query("SELECT pi FROM ProductImage pi WHERE pi.product.id = :productId ORDER BY pi.sortOrder ASC")
    Optional<ProductImage> findFirstByProductIdOrderBySortOrderAsc(@Param("productId") Long productId);

    // Count images for product
    Long countByProductId(Long productId);

    // Delete all images for product
    void deleteByProductId(Long productId);
}
