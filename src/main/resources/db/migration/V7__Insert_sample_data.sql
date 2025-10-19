-- Insert sample categories
INSERT INTO categories (name, description, slug, is_active, sort_order) VALUES 
('Điện thoại', 'Điện thoại thông minh và phụ kiện', 'dien-thoai', TRUE, 1),
('Laptop', 'Máy tính xách tay và phụ kiện', 'laptop', TRUE, 2),
('Phụ kiện', 'Phụ kiện điện tử', 'phu-kien', TRUE, 3),
('Thời trang', 'Quần áo và phụ kiện thời trang', 'thoi-trang', TRUE, 4),
('Gia dụng', 'Đồ dùng gia đình', 'gia-dung', TRUE, 5);

-- Insert sub-categories
INSERT INTO categories (name, description, parent_id, slug, is_active, sort_order) VALUES 
('iPhone', 'Điện thoại iPhone', 1, 'iphone', TRUE, 1),
('Samsung', 'Điện thoại Samsung', 1, 'samsung', TRUE, 2),
('Xiaomi', 'Điện thoại Xiaomi', 1, 'xiaomi', TRUE, 3),
('MacBook', 'Laptop Apple', 2, 'macbook', TRUE, 1),
('Dell', 'Laptop Dell', 2, 'dell', TRUE, 2),
('HP', 'Laptop HP', 2, 'hp', TRUE, 3);

-- Insert sample products
INSERT INTO products (name, description, price, original_price, discount_percentage, brand, sku, stock_quantity, min_stock_level, is_active, is_featured, is_new, category_id, rating, review_count) VALUES 
('iPhone 15 Pro Max', 'iPhone 15 Pro Max 256GB - Titanium tự nhiên', 29990000, 32990000, 9, 'Apple', 'IPHONE15PM256', 50, 10, TRUE, TRUE, TRUE, 6, 4.8, 125),
('Samsung Galaxy S24 Ultra', 'Samsung Galaxy S24 Ultra 512GB - Titanium Black', 24990000, 26990000, 7, 'Samsung', 'SGS24U512', 30, 5, TRUE, TRUE, TRUE, 7, 4.7, 98),
('Xiaomi 14 Pro', 'Xiaomi 14 Pro 256GB - Đen', 18990000, 20990000, 10, 'Xiaomi', 'XM14P256', 25, 8, TRUE, FALSE, TRUE, 8, 4.5, 67),
('MacBook Pro 16" M3', 'MacBook Pro 16" M3 512GB - Space Gray', 45990000, 49990000, 8, 'Apple', 'MBP16M3512', 15, 3, TRUE, TRUE, TRUE, 9, 4.9, 45),
('Dell XPS 15', 'Dell XPS 15 9530 i7 16GB 512GB', 32990000, 35990000, 8, 'Dell', 'DXP15I7', 20, 5, TRUE, FALSE, TRUE, 10, 4.6, 78),
('HP Pavilion 15', 'HP Pavilion 15 i5 8GB 256GB', 15990000, 17990000, 11, 'HP', 'HPP15I5', 35, 10, TRUE, FALSE, TRUE, 11, 4.3, 56);

-- Insert sample product images
INSERT INTO product_images (product_id, image_url, alt_text, is_primary, sort_order) VALUES 
(1, 'https://example.com/images/iphone15pm1.jpg', 'iPhone 15 Pro Max - Mặt trước', TRUE, 1),
(1, 'https://example.com/images/iphone15pm2.jpg', 'iPhone 15 Pro Max - Mặt sau', FALSE, 2),
(2, 'https://example.com/images/sgs24u1.jpg', 'Samsung Galaxy S24 Ultra - Mặt trước', TRUE, 1),
(2, 'https://example.com/images/sgs24u2.jpg', 'Samsung Galaxy S24 Ultra - Mặt sau', FALSE, 2),
(3, 'https://example.com/images/xm14p1.jpg', 'Xiaomi 14 Pro - Mặt trước', TRUE, 1),
(4, 'https://example.com/images/mbp16m31.jpg', 'MacBook Pro 16" M3 - Mặt trên', TRUE, 1),
(5, 'https://example.com/images/dxp15i71.jpg', 'Dell XPS 15 - Mặt trên', TRUE, 1),
(6, 'https://example.com/images/hpp15i51.jpg', 'HP Pavilion 15 - Mặt trên', TRUE, 1);
