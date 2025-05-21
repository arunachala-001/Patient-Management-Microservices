CREATE TABLE IF NOT EXISTS `users` (
    id BINARY(16) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Insert user if not exists (by email or id)
INSERT INTO `users` (id, email, password, role)
SELECT * FROM (
    SELECT '223e4567-e89b-12d3-a456-426614174006', 'testuser@test.com',
           '$2b$12$7hoRZfJrRKD2nIm2vHLs7OBETy.LWenXXMLKf99W8M4PUwO6KB7fu', 'ADMIN'
) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM `users`
    WHERE id = '223e4567-e89b-12d3-a456-426614174006'
       OR email = 'testuser@test.com'
);



