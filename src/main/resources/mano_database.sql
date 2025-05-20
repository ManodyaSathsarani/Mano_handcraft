CREATE DATABASE manohandcraft;
USE manohandcraft;



CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    phone VARCHAR(20),
    address TEXT
);

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100),
    role VARCHAR(50),
    hire_date DATE,
    phone VARCHAR(20),
    address TEXT
);



CREATE TABLE ingredients (
    ingredient_id INT AUTO_INCREMENT PRIMARY KEY,
    ingredient_name VARCHAR(100),
    unit VARCHAR(50)
);



CREATE TABLE inventory (
    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    ingredient_id INT,
    quantity_in_stock DECIMAL(10,2),
    last_updated DATETIME,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id)
);



CREATE TABLE orderdetails (
    orderdetail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);



CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    employee_id INT,
    order_date DATETIME,
    total_amount DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);



CREATE TABLE payment (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    amount DECIMAL(10,2),
    method VARCHAR(50),
    status VARCHAR(50),
    payment_date DATETIME,
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);



CREATE TABLE product_ingredients (
    product_id INT,
    ingredient_id INT,
    quantity DECIMAL(10,2),
    PRIMARY KEY (product_id, ingredient_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id)
);



CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    category_id INT,
    price DECIMAL(10,2),
    description TEXT,
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);



CREATE TABLE suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(100),
    contact_info TEXT
);


CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(255),
    role VARCHAR(50),
    created_at DATETIME
);

show tables;