-- Initial schema for ecommerce application
-- Created: 14/09/2025 06:59:17

-- Create CUSTOMER table (User entity)
CREATE TABLE IF NOT EXISTS customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER',
    address TEXT
);

-- Create CATEGORY table
CREATE TABLE IF NOT EXISTS category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create PRODUCT table
CREATE TABLE IF NOT EXISTS product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(500),
    category_id INT,
    quantity INT NOT NULL DEFAULT 0,
    price INT NOT NULL DEFAULT 0,
    weight INT NOT NULL DEFAULT 0,
    description TEXT,
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE
);

-- Create CART table
CREATE TABLE IF NOT EXISTS cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

-- Create CART_PRODUCT table (many-to-many relationship)
CREATE TABLE IF NOT EXISTS cart_product (
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

-- Create indexes for better performance (ignore errors if they already exist)
CREATE INDEX idx_customer_username ON customer(username);
CREATE INDEX idx_product_category ON product(category_id);
CREATE INDEX idx_cart_customer ON cart(customer_id);

-- Insert initial admin user (password: admin123 - BCrypt encoded)
INSERT IGNORE INTO customer (username, email, password, role, address) VALUES
('admin', 'admin@ecommerce.com', '$2a$08$MzAmZ8dJhup1NqLg.6pZ8uZ/sysgZL1PAIe7v5dg.oNFVxxEFWVju', 'ROLE_ADMIN', 'Admin Address'),
('gojo', 'gojo@ecommerce.com', '$2a$08$MzAmZ8dJhup1NqLg.6pZ8uZ/sysgZL1PAIe7v5dg.oNFVxxEFWVju', 'ROLE_NORMAL', 'User Address'),
('yuji', 'yuji@ecommerce.com', '$2a$08$MzAmZ8dJhup1NqLg.6pZ8uZ/sysgZL1PAIe7v5dg.oNFVxxEFWVju', 'ROLE_NORMAL', 'User Address');


-- Insert sample categories
INSERT IGNORE INTO category (name) VALUES
('Electronics'),
('Clothing'),
('Books'),
('Home & Garden'),
('Sports'),
('Fruits'),
('Vegetables'),
('Meat'),
('Fish'),
('Dairy'),
('Bakery'),
('Drinks'),
('Sweets'),
('Other');

-- Insert sample products
INSERT IGNORE INTO product (name, image, category_id, quantity, price, weight, description) VALUES
('Laptop Dell XPS 13', 'https://i.dell.com/is/image/DellContent/content/dam/ss2/product-images/dell-client-products/notebooks/xps-notebooks/xps-13-9350/media-gallery/platinum/notebook-xps-13-9350-t-oled-sl-gallery-1.psd', 1, 10, 1200000, 1300, 'High-performance laptop with Intel Core i7'),
('iPhone 17 Pro Max', 'https://akcdn.detik.net.id/visual/2025/09/10/iphone-17-pro-dan-pro-max-1757443380647_169.png?w=900&q=80', 1, 15, 1500000, 238, 'Latest iPhone with A16 Bionic chip'),
('Cotton T-Shirt', 'https://sojao.shop/cdn/shop/files/sojao-organic-cotton-men-tee-Olive.jpg?v=1742175474&width=800', 2, 50, 150000, 200, 'Comfortable cotton t-shirt available in various colors'),
('Java Programming Book', 'https://images-cdn.bukuext.com/thumbnail?width=400&type=auto&url=https://cdn.bukuext.com/covers/uxd2zubuguofx3ut0wbsbg7g0dx6uqnwutagg9a5.jpg', 3, 25, 250000, 500, 'Comprehensive guide to Java programming'),
('Samsung S25', 'https://images.samsung.com/id/smartphones/galaxy-s25-ultra/buy/kv_global_PC_v2.jpg?imbypass=true', 4, 8, 350000, 2000, 'Complete set of Samsung S25 features'),
('Apple Watch Series 8', 'https://cdsassets.apple.com/live/SZLF0YNV/images/sp/111848_apple-watch-series8.png', 1, 10, 1000000, 1000, 'Apple Watch Series 8 with 44mm screen'),
('Apple Watch Series 7', 'https://gizmologi.id/wp-content/uploads/2021/09/Apple-Watch-Series-7-003.jpg', 1, 10, 1000000, 1000, 'Apple Watch Series 8 with 44mm screen'),
('Fresh and juicy', 'https://freepngimg.com/save/9557-apple-fruit-transparent/744x744', 3, 40, 76, 1, 'Apple'),
('Woops! There goes the eggs...', 'https://www.nicepng.com/png/full/813-8132637_poiata-bunicii-cracked-egg.png', 1, 90, 43, 9, 'Cracked Eggs'),
('Vivo X200', 'https://asia-exstatic-vivofs.vivo.com/PSee2l50xoirPK7y/product/1734599564326/zip/img/pc/vivo-x200.jpg.webp', 3, 40, 76, 1, 'Vivo X200'),
('Xiaomi 15', 'https://gizmologi.id/wp-content/uploads/2024/10/Xiaomi-15_Thumbnail-860x484.jpeg', 1, 90, 43, 9, 'Xiaomi 15'),
('MacBook M4 Pro Max', 'https://cdnpro.eraspace.com/media/mageplaza/blog/post/m/a/macbook_pro_m4_pro_primary.jpg', 3, 40, 76, 1, 'Apple MacBook M4 Pro Max');

