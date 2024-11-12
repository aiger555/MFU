-- Table users
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
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
                          skin_problem BOOLEAN NOT null,
                          favorite BOOLEAN NOT null
);

CREATE TABLE favorites (
                           id SERIAL PRIMARY KEY,
                           user_id INTEGER NOT NULL,
                           product_id INTEGER NOT NULL,
                           favorite BOOLEAN DEFAULT TRUE,
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE cascade);



---- Вставка данных в таблицу пользователей
--INSERT INTO users (email, username, firstname, password, role) VALUES
--('admin@example.com', 'adminuser', 'Admin', 'adminpass', 'ADMIN'),
--('user1@example.com', 'johndoe', 'John', 'password1', 'USER'),
--('user2@example.com', 'janesmith', 'Jane', 'password2', 'USER');



-- Скрипты удаления таблиц для очистки (опционально)
--DROP TABLE IF EXISTS products;
--DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS categories;

--ALTER TABLE products ADD COLUMN favorite BOOLEAN DEFAULT FALSE;
--SELECT p.* FROM products p JOIN favorites f ON p.id = f.product_id WHERE f.user_id = 1 AND f.favorite = true;


select * from users;
select * from products;
select * from categories;
select * from favorites;





