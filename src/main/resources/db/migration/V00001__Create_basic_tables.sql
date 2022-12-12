CREATE TABLE users (
    id VARCHAR(60) PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    activated BOOLEAN DEFAULT false
);

CREATE TABLE roles (
    id VARCHAR(60) PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL UNIQUE,
    role_description VARCHAR(255)
);

CREATE TABLE user_roles (
    user_id VARCHAR(60) NOT NULL constraint user_roles_user_fk references users,
    role_id VARCHAR(60) NOT NULL constraint user_roles_role_fk references roles,
    primary key (user_id, role_id)
);

CREATE TABLE products (
    id VARCHAR(60) PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL UNIQUE,
    product_description VARCHAR(255),
    product_price DOUBLE PRECISION NOT NULL,
    product_amount_available INTEGER NOT NULL
);

CREATE TABLE categories (
    id VARCHAR(60) PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL UNIQUE,
    category_description VARCHAR(255)
);

CREATE TABLE product_categories (
    product_id VARCHAR(60) NOT NULL constraint product_categories_product_fk references products(id),
    category_id VARCHAR(60) NOT NULL constraint product_categories_category_fk references categories(id),
    primary key (product_id, category_id)
);

CREATE TABLE orders (
    id VARCHAR(60) PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    customer_address VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    customer_phone VARCHAR(255) NOT NULL,
    create_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    order_num INTEGER NOT NULL UNIQUE
);

CREATE TABLE order_details (
    id VARCHAR(60) PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    quantity INTEGER NOT NULL,
    order_id VARCHAR(60) NOT NULL constraint order_details_order_fk references orders(id),
    product_id VARCHAR(60) NOT NULL constraint order_details_product_fk references products(id)
);
