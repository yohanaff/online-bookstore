USE catalog_service;

INSERT INTO category (name)
VALUES ('FICTION'),
       ('NON_FICTION'),
       ('MYSTERY'),
       ('FANTASY'),
       ('SCIENCE_FICTION'),
       ('BIOGRAPHY'),
       ('HISTORY'),
       ('ROMANCE'),
       ('CHILDRENS');

INSERT INTO book (title, author, isbn, price, publication_year, category)
VALUES ('The Catcher in the Rye', 'J.D. Salinger', '9780316769488', 10.99, 1951, 'FICTION'),
       ('To Kill a Mockingbird', 'Harper Lee', '9780060935467', 12.99, 1960, 'FICTION'),
       ('1984', 'George Orwell', '9780451524935', 9.99, 1949, 'SCIENCE_FICTION'),
       ('Brave New World', 'Aldous Huxley', '9780060850524', 11.99, 1932, 'SCIENCE_FICTION'),
       ('Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', '9780062316097', 14.99, 2011, 'HISTORY');
