DROP TABLE IF EXISTS inventory;

USE inventory_service;

CREATE TABLE IF NOT EXISTS inventory
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    book_id     BIGINT       NOT NULL,
    quantity    INT          NOT NULL,
    last_update TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE (book_id)
);