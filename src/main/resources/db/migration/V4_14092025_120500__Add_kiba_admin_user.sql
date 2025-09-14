-- Add kiba admin user
-- Created: 14/09/2025 12:05:00

-- Add kiba user with admin privileges (password: 12345)
-- BCrypt hash for "12345": $2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.
INSERT
IGNORE INTO customer (username, email, password, role, address) VALUES
('kiba', 'kiba@ecommerce.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'ROLE_ADMIN', 'Kiba Admin Address');