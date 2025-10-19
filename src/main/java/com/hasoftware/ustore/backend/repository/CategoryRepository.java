package com.hasoftware.ustore.backend.repository;

import com.hasoftware.ustore.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find active categories
    List<Category> findByIsActiveTrueOrderBySortOrderAsc();

    // Find categories by parent
    List<Category> findByParentIdAndIsActiveTrueOrderBySortOrderAsc(Long parentId);

    // Find root categories (no parent)
    List<Category> findByParentIdIsNullAndIsActiveTrueOrderBySortOrderAsc();

    // Find by slug
    Optional<Category> findBySlug(String slug);

    // Check if slug exists
    Boolean existsBySlug(String slug);

    // Find by name
    Optional<Category> findByName(String name);

    // Check if name exists
    Boolean existsByName(String name);

    // Find categories with products
    @Query("SELECT DISTINCT c FROM Category c JOIN c.products p WHERE c.isActive = true AND p.isActive = true")
    List<Category> findCategoriesWithProducts();

    // Count products in category
    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId AND p.isActive = true")
    Long countProductsInCategory(@Param("categoryId") Long categoryId);

    // Find category hierarchy
    @Query("SELECT c FROM Category c WHERE c.isActive = true ORDER BY c.sortOrder ASC")
    List<Category> findAllActiveCategoriesOrdered();
}
