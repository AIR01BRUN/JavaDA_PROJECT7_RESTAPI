CREATE DATABASE IF NOT EXISTS poseiden;
USE poseiden;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE bidlist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    bid_quantity DOUBLE,
    ask_quantity DOUBLE,
    bid DOUBLE,
    ask DOUBLE,
    benchmark VARCHAR(255),
    commentary VARCHAR(255),
    security VARCHAR(255),
    status VARCHAR(255),
    trader VARCHAR(255),
    book VARCHAR(255),
    creation_name VARCHAR(255),
    creation_date TIMESTAMP,
    revision_name VARCHAR(255),
    revision_date TIMESTAMP,
    deal_name VARCHAR(255),
    deal_type VARCHAR(255),
    source_list_id VARCHAR(255),
    side VARCHAR(255)
);

CREATE TABLE trade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    buy_quantity DOUBLE,
    sell_quantity DOUBLE,
    buy_price DOUBLE,
    sell_price DOUBLE,
    benchmark VARCHAR(255),
    trade_date TIMESTAMP,
    security VARCHAR(255),
    status VARCHAR(255),
    trader VARCHAR(255),
    book VARCHAR(255),
    creation_name VARCHAR(255),
    creation_date TIMESTAMP,
    revision_name VARCHAR(255),
    revision_date TIMESTAMP,
    deal_name VARCHAR(255),
    deal_type VARCHAR(255),
    source_list_id VARCHAR(255),
    side VARCHAR(255)
);

CREATE TABLE rulename (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,
    json VARCHAR(50) NOT NULL,
    template VARCHAR(50) NOT NULL,
    sql_str VARCHAR(50) NOT NULL,
    sql_part VARCHAR(50) NOT NULL
);

CREATE TABLE rating (
    id INT AUTO_INCREMENT PRIMARY KEY,
    moodys_rating VARCHAR(50) NOT NULL,
    sand_prating VARCHAR(50) NOT NULL,
    fitch_rating VARCHAR(50) NOT NULL,
    `order` INT NOT NULL
);

CREATE TABLE curvepoint (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curve_id INT,
    as_of_date TIMESTAMP,
    term DOUBLE,
    value DOUBLE,
    creation_date TIMESTAMP
);
