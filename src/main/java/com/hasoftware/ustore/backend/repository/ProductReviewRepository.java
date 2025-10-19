package com.hasoftware.ustore.backend.repository;

import com.hasoftware.ustore.backend.entity.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    // Find reviews by product
    Page<ProductReview> findByProductIdOrderByCreatedAtDesc(Long productId, Pageable pageable);

    // Find reviews by user
    Page<ProductReview> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    // Find review by user and product
    Optional<ProductReview> findByUserIdAndProductId(Long userId, Long productId);

    // Check if user has reviewed product
    Boolean existsByUserIdAndProductId(Long userId, Long productId);

    // Find reviews by rating
    Page<ProductReview> findByProductIdAndRatingOrderByCreatedAtDesc(Long productId, Integer rating, Pageable pageable);

    // Find verified purchase reviews
    Page<ProductReview> findByProductIdAndIsVerifiedPurchaseTrueOrderByCreatedAtDesc(Long productId, Pageable pageable);

    // Count reviews by product
    Long countByProductId(Long productId);

    // Count reviews by rating
    Long countByProductIdAndRating(Long productId, Integer rating);

    // Calculate average rating
    @Query("SELECT AVG(pr.rating) FROM ProductReview pr WHERE pr.product.id = :productId")
    Double calculateAverageRating(@Param("productId") Long productId);

    // Find helpful reviews
    @Query("SELECT pr FROM ProductReview pr WHERE pr.product.id = :productId ORDER BY (pr.helpfulCount - pr.notHelpfulCount) DESC")
    Page<ProductReview> findMostHelpfulReviews(@Param("productId") Long productId, Pageable pageable);

    // Find recent reviews
    @Query("SELECT pr FROM ProductReview pr WHERE pr.product.id = :productId ORDER BY pr.createdAt DESC")
    Page<ProductReview> findRecentReviews(@Param("productId") Long productId, Pageable pageable);
}
