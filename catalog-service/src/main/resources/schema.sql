DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS category;

USE catalog_service;

CREATE TABLE IF NOT EXISTS book
(
    id               BIGINT         NOT NULL AUTO_INCREMENT,
    title            VARCHAR(255)   NOT NULL,
    author           VARCHAR(255)   NOT NULL,
    isbn             VARCHAR(20)    NOT NULL,
    price            DECIMAL(10, 2) NOT NULL,
    publication_year INT            NOT NULL,
    category         VARCHAR(255)   NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS category
(
    id   BIGINT       NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);