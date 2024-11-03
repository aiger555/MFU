-- Таблица ролей
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(10) NOT NULL UNIQUE
);

-- Таблица пользователей
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       firstname VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role_id INT REFERENCES roles(id) ON DELETE SET NULL
);

-- Таблица категорий
CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(50) NOT NULL UNIQUE
);

-- Таблица продуктов
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

-- Таблица избранных продуктов
CREATE TABLE favorites (
                           id SERIAL PRIMARY KEY,
                           user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                           product_id INT NOT NULL REFERENCES products(id) ON DELETE CASCADE
);

-- Вставка данных в таблицу ролей
INSERT INTO roles (name) VALUES ('ADMIN'), ('USER');

-- Вставка данных в таблицу категорий
INSERT INTO categories (name) VALUES ('OIL_SKIN'), ('DRY_SKIN'), ('COMBY_SKIN');

-- Вставка данных в таблицу пользователей
INSERT INTO users (email, username, firstname, password, role_id) VALUES
                                                                      ('admin@example.com', 'admin', 'Admin User', 'adminpass', 1),
                                                                      ('user1@example.com', 'john_doe', 'John', 'password1', 2),
                                                                      ('user2@example.com', 'jane_smith', 'Jane', 'password2', 2),
                                                                      ('user3@example.com', 'alice_brown', 'Alice', 'password3', 2),
                                                                      ('user4@example.com', 'bob_white', 'Bob', 'password4', 2);

-- Вставка данных в таблицу продуктов
INSERT INTO products (title, category_id, age, details, brend, price, skin_problem) VALUES
                                                                                        ('Hydrating Serum', 1, 25, 'A hydrating serum for oily skin', 'BrandA', 2500, true),
                                                                                        ('Anti-Aging Cream', 2, 40, 'Cream for dry and aging skin', 'BrandB', 3000, false),
                                                                                        ('Daily Moisturizer', 3, 30, 'Suitable for all skin types', 'BrandC', 1500, true),
                                                                                        ('Night Repair Gel', 1, 35, 'Gel for skin repair at night', 'BrandD', 2000, false),
                                                                                        ('Brightening Toner', 2, 28, 'Toner for brightening dry skin', 'BrandE', 1200, true);

-- Вставка данных в таблицу избранных продуктов
INSERT INTO favorites (user_id, product_id) VALUES
                                                (2, 1),  -- Пользователь John Doe добавил Hydrating Serum в избранное
                                                (3, 2),  -- Пользователь Jane Smith добавил Anti-Aging Cream в избранное
                                                (4, 3),  -- Пользователь Alice Brown добавил Daily Moisturizer в избранное
                                                (4, 5),  -- Пользователь Alice Brown также добавил Brightening Toner в избранное
                                                (5, 4);  -- Пользователь Bob White добавил Night Repair Gel в избранное

-- Скрипты удаления таблиц для очистки (опционально)
--DROP TABLE IF EXISTS favorites;
--DROP TABLE IF EXISTS products;
--DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS categories;
--DROP TABLE IF EXISTS roles;
