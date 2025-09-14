-- Update user passwords with correct BCrypt hashes
-- Created: 14/09/2025 08:01:42

-- Update admin user password (admin123)
UPDATE customer SET password = '$2a$10$dHeGeEJ2W5kXA/aD7QUkmuRsY5nfC/LUpFH8T57EQDfL5SJ25ZLlm'
WHERE username = 'admin';

-- Update regular user passwords (admin123)
UPDATE customer SET password = '$2a$10$dHeGeEJ2W5kXA/aD7QUkmuRsY5nfC/LUpFH8T57EQDfL5SJ25ZLlm'
WHERE username IN ('gojo', 'yuji');