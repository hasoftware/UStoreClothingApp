package com.hasoftware.ustore.backend.service;

import com.hasoftware.ustore.backend.entity.Category;
import com.hasoftware.ustore.backend.entity.Product;
import com.hasoftware.ustore.backend.repository.CategoryRepository;
import com.hasoftware.ustore.backend.repository.ProductRepository;
import com.hasoftware.ustore.backend.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public Product createProduct(Product product) {
        // Check if SKU already exists
        if (product.getSku() != null && productRepository.existsBySku(product.getSku())) {
            throw new RuntimeException("SKU already exists!");
        }

        // Set category if provided
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product productDetails) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update fields
        if (productDetails.getName() != null) {
            product.setName(productDetails.getName());
        }
        if (productDetails.getDescription() != null) {
            product.setDescription(productDetails.getDescription());
        }
        if (productDetails.getPrice() != null) {
            product.setPrice(productDetails.getPrice());
        }
        if (productDetails.getOriginalPrice() != null) {
            product.setOriginalPrice(productDetails.getOriginalPrice());
        }
        if (productDetails.getDiscountPercentage() != null) {
            product.setDiscountPercentage(productDetails.getDiscountPercentage());
        }
        if (productDetails.getBrand() != null) {
            product.setBrand(productDetails.getBrand());
        }
        if (productDetails.getSku() != null) {
            // Check if new SKU is different and doesn't exist
            if (!productDetails.getSku().equals(product.getSku()) &&
                    productRepository.existsBySku(productDetails.getSku())) {
                throw new RuntimeException("SKU already exists!");
            }
            product.setSku(productDetails.getSku());
        }
        if (productDetails.getStockQuantity() != null) {
            product.setStockQuantity(productDetails.getStockQuantity());
        }
        if (productDetails.getMinStockLevel() != null) {
            product.setMinStockLevel(productDetails.getMinStockLevel());
        }
        if (productDetails.getIsActive() != null) {
            product.setIsActive(productDetails.getIsActive());
        }
        if (productDetails.getIsFeatured() != null) {
            product.setIsFeatured(productDetails.getIsFeatured());
        }
        if (productDetails.getIsNew() != null) {
            product.setIsNew(productDetails.getIsNew());
        }
        if (productDetails.getWeight() != null) {
            product.setWeight(productDetails.getWeight());
        }
        if (productDetails.getDimensions() != null) {
            product.setDimensions(productDetails.getDimensions());
        }
        if (productDetails.getColor() != null) {
            product.setColor(productDetails.getColor());
        }
        if (productDetails.getSize() != null) {
            product.setSize(productDetails.getSize());
        }
        if (productDetails.getMaterial() != null) {
            product.setMaterial(productDetails.getMaterial());
        }
        if (productDetails.getWarrantyPeriod() != null) {
            product.setWarrantyPeriod(productDetails.getWarrantyPeriod());
        }

        // Update category if provided
        if (productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
            Category category = categoryRepository.findById(productDetails.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Increment view count
        product.setViewCount(product.getViewCount() + 1);
        return productRepository.save(product);
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getActiveProducts(Pageable pageable) {
        return productRepository.findByIsActiveTrue(pageable);
    }

    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryIdAndIsActiveTrue(categoryId, pageable);
    }

    public Page<Product> getProductsByBrand(String brand, Pageable pageable) {
        return productRepository.findByBrandAndIsActiveTrue(brand, pageable);
    }

    public Page<Product> getFeaturedProducts(Pageable pageable) {
        return productRepository.findByIsFeaturedTrueAndIsActiveTrue(pageable);
    }

    public Page<Product> getNewProducts(Pageable pageable) {
        return productRepository.findByIsNewTrueAndIsActiveTrue(pageable);
    }

    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository.searchProducts(keyword, pageable);
    }

    public Page<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.findByPriceRange(minPrice, maxPrice, pageable);
    }

    public Page<Product> getProductsByMinRating(Double minRating, Pageable pageable) {
        return productRepository.findByMinRating(minRating, pageable);
    }

    public Page<Product> getDiscountedProducts(Pageable pageable) {
        return productRepository.findDiscountedProducts(pageable);
    }

    public Page<Product> getInStockProducts(Pageable pageable) {
        return productRepository.findInStockProducts(pageable);
    }

    public List<Product> getLowStockProducts() {
        return productRepository.findLowStockProducts();
    }

    public List<Product> getOutOfStockProducts() {
        return productRepository.findOutOfStockProducts();
    }

    public Page<Product> getSimilarProducts(Long productId, Pageable pageable) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getCategory() == null) {
            return Page.empty();
        }

        // Get similar products from the same category, excluding current product
        // For now, return empty page as findSimilarProducts needs to be implemented
        // properly
        return Page.empty();
    }

    public Page<Product> getBestSellingProducts(Pageable pageable) {
        return productRepository.findBestSellingProducts(pageable);
    }

    public Page<Product> getMostViewedProducts(Pageable pageable) {
        return productRepository.findMostViewedProducts(pageable);
    }

    public Page<Product> getProductsWithFilters(Long categoryId, String brand, BigDecimal minPrice,
            BigDecimal maxPrice, Double minRating, Boolean inStock,
            Pageable pageable) {
        return productRepository.findProductsWithFilters(categoryId, brand, minPrice, maxPrice,
                minRating, inStock, pageable);
    }

    public void updateProductRating(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Double averageRating = productReviewRepository.calculateAverageRating(productId);
        Long reviewCount = productReviewRepository.countByProductId(productId);

        product.setRating(averageRating != null ? averageRating : 0.0);
        product.setReviewCount(reviewCount.intValue());

        productRepository.save(product);
    }

    public void updateStockQuantity(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStockQuantity(product.getStockQuantity() - quantity);
        product.setSoldCount(product.getSoldCount() + quantity);

        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }
}
