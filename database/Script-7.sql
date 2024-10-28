-- Таблица для ролей 
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

-- Таблица users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT REFERENCES roles(id) ON DELETE SET NULL
);

-- Таблица categories
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Таблица products
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category_id INT REFERENCES categories(id) ON DELETE SET NULL,
    age INT,
    details TEXT,
    brend VARCHAR(100),
    price BIGINT,
    skin_problem BOOLEAN NOT NULL
);

-- Таблица избранных продуктов (favorites)
CREATE TABLE favorites (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    product_id INT NOT NULL REFERENCES products(id) ON DELETE CASCADE
);

-- Вставк/а данных в таблицу roles для ролей ADMIN и USER
INSERT INTO roles (name) VALUES ('ADMIN'), ('USER');

-- Вставка данных в таблицу categories для категорий OIL, DRY и COMBY
INSERT INTO categories (name) VALUES ('OIL_SKIN'), ('DRY_SKIN'), ('COMBY_SKIN');

-- Вставка данных в таблицу users
INSERT INTO users (email, name, password, role_id) VALUES 
    ('admin@example.com', 'Admin User', 'adminpass', 1),
    ('user1@example.com', 'John Doe', 'password1', 2),
    ('user2@example.com', 'Jane Smith', 'password2', 2),
    ('user3@example.com', 'Alice Brown', 'password3', 2),
    ('user4@example.com', 'Bob White', 'password4', 2);

-- Вставка данных в таблицу products
INSERT INTO products (title, category_id, age, details, brend, price, skin_problem) VALUES 
    ('Hydrating Serum', 1, 25, 'A hydrating serum for oily skin', 'BrandA', 2500, true),
    ('Anti-Aging Cream', 2, 40, 'Cream for dry and aging skin', 'BrandB', 3000, false),
    ('Daily Moisturizer', 3, 30, 'Suitable for all skin types', 'BrandC', 1500, true),
    ('Night Repair Gel', 1, 35, 'Gel for skin repair at night', 'BrandD', 2000, false),
    ('Brightening Toner', 2, 28, 'Toner for brightening dry skin', 'BrandE', 1200, true);

-- Вставка данных в таблицу favorites
INSERT INTO favorites (user_id, product_id) VALUES 
    (2, 1),  -- User John Doe added Hydrating Serum to favorites
    (3, 2),  -- User Jane Smith added Anti-Aging Cream to favorites
    (4, 3),  -- User Alice Brown added Daily Moisturizer to favorites
    (4, 5),  -- User Alice Brown also added Brightening Toner to favorites
    (5, 4);  -- User Bob White added Night Repair Gel to favorites

-- Сначала удаляем таблицу favorites
--DROP TABLE IF EXISTS favorites;
--
---- Затем удаляем таблицу products
--DROP TABLE IF EXISTS products;
--
---- Удаляем таблицу users
--DROP TABLE IF EXISTS users;
--
---- Удаляем таблицу categories
--DROP TABLE IF EXISTS categories;
--
---- Удаляем таблицу roles
--DROP TABLE IF EXISTS roles;
    
