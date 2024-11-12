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



select * from users;
select * from products;
select * from categories;




