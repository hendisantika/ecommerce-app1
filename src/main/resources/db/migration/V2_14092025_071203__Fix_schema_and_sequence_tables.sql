-- Fix schema issues and add sequence tables
-- Created: 14/09/2025 07:12:03

-- Create sequence tables for GenerationType.AUTO entities
CREATE TABLE IF NOT EXISTS cart_seq (
    next_val BIGINT
);

CREATE TABLE IF NOT EXISTS category_seq (
    next_val BIGINT
);

CREATE TABLE IF NOT EXISTS product_seq (
    next_val BIGINT
);

-- Initialize sequence values based on existing data
INSERT IGNORE INTO cart_seq (next_val) VALUES (1);
INSERT IGNORE INTO category_seq (next_val) VALUES (15);  -- Starting from 15 since we have 14 categories
INSERT IGNORE INTO product_seq (next_val) VALUES (8);   -- Starting from 8 since we have 7 products

-- Fix the product data inconsistencies
DELETE FROM product WHERE name = 'Fresh and juicy' OR name = 'Woops! There goes the eggs...';

-- Insert corrected product data
INSERT IGNORE INTO product (name, image, category_id, quantity, price, weight, description) VALUES
('Apple', 'https://freepngimg.com/save/9557-apple-fruit-transparent/744x744', 6, 40, 76, 1, 'Fresh and juicy apples'),
('Cracked Eggs', 'https://www.nicepng.com/png/full/813-8132637_poiata-bunicii-cracked-egg.png', 10, 90, 43, 9, 'Woops! There goes the eggs...');

-- Update sequence table based on actual product count
UPDATE product_seq SET next_val = (SELECT MAX(product_id) + 1 FROM product);
UPDATE category_seq SET next_val = (SELECT MAX(category_id) + 1 FROM category);