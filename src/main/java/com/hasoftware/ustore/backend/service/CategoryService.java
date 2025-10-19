package com.hasoftware.ustore.backend.service;

import com.hasoftware.ustore.backend.entity.Category;
import com.hasoftware.ustore.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        // Check if name already exists
        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category name already exists!");
        }

        // Check if slug already exists
        if (category.getSlug() != null && categoryRepository.existsBySlug(category.getSlug())) {
            throw new RuntimeException("Category slug already exists!");
        }

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, Category categoryDetails) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Check if new name already exists (if changed)
        if (!category.getName().equals(categoryDetails.getName()) &&
                categoryRepository.existsByName(categoryDetails.getName())) {
            throw new RuntimeException("Category name already exists!");
        }

        // Check if new slug already exists (if changed)
        if (categoryDetails.getSlug() != null &&
                !categoryDetails.getSlug().equals(category.getSlug()) &&
                categoryRepository.existsBySlug(categoryDetails.getSlug())) {
            throw new RuntimeException("Category slug already exists!");
        }

        // Update fields
        if (categoryDetails.getName() != null) {
            category.setName(categoryDetails.getName());
        }
        if (categoryDetails.getDescription() != null) {
            category.setDescription(categoryDetails.getDescription());
        }
        if (categoryDetails.getImage() != null) {
            category.setImage(categoryDetails.getImage());
        }
        if (categoryDetails.getParentId() != null) {
            category.setParentId(categoryDetails.getParentId());
        }
        if (categoryDetails.getIsActive() != null) {
            category.setIsActive(categoryDetails.getIsActive());
        }
        if (categoryDetails.getSortOrder() != null) {
            category.setSortOrder(categoryDetails.getSortOrder());
        }
        if (categoryDetails.getSlug() != null) {
            category.setSlug(categoryDetails.getSlug());
        }
        if (categoryDetails.getMetaTitle() != null) {
            category.setMetaTitle(categoryDetails.getMetaTitle());
        }
        if (categoryDetails.getMetaDescription() != null) {
            category.setMetaDescription(categoryDetails.getMetaDescription());
        }

        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getActiveCategories() {
        return categoryRepository.findByIsActiveTrueOrderBySortOrderAsc();
    }

    public List<Category> getCategoriesByParent(Long parentId) {
        return categoryRepository.findByParentIdAndIsActiveTrueOrderBySortOrderAsc(parentId);
    }

    public List<Category> getRootCategories() {
        return categoryRepository.findByParentIdIsNullAndIsActiveTrueOrderBySortOrderAsc();
    }

    public List<Category> getCategoriesWithProducts() {
        return categoryRepository.findCategoriesWithProducts();
    }

    public Long getProductCountInCategory(Long categoryId) {
        return categoryRepository.countProductsInCategory(categoryId);
    }

    public void deactivateCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setIsActive(false);
        categoryRepository.save(category);
    }

    public void activateCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setIsActive(true);
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(categoryId);
    }

    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    public boolean existsBySlug(String slug) {
        return categoryRepository.existsBySlug(slug);
    }
}
