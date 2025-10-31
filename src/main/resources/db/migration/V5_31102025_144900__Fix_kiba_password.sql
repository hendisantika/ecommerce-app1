-- Fix kiba user password
-- Created: 31/10/2025 14:49:00

-- Update kiba user password to correct BCrypt hash for "12345"
-- BCrypt hash for "12345": $2a$10$wpVOWFPmvv1G7XQRoIWGWO047W993jfjMlrg8rwXQsLpClr.1nVs.
UPDATE customer
SET password = '$2a$10$wpVOWFPmvv1G7XQRoIWGWO047W993jfjMlrg8rwXQsLpClr.1nVs.'
WHERE username = 'kiba';
