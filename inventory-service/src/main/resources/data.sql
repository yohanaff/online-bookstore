INSERT INTO inventory(book_id, quantity)
SELECT * FROM (SELECT 1 AS book_id, 100 AS quantity) AS tmp
WHERE NOT EXISTS (
    SELECT book_id FROM inventory WHERE book_id = 1
) LIMIT 1;

INSERT INTO inventory(book_id, quantity)
SELECT * FROM (SELECT 2 AS book_id, 200 AS quantity) AS tmp
WHERE NOT EXISTS (
    SELECT book_id FROM inventory WHERE book_id = 2
) LIMIT 1;

INSERT INTO inventory(book_id, quantity)
SELECT * FROM (SELECT 3 AS book_id, 150 AS quantity) AS tmp
WHERE NOT EXISTS (
    SELECT book_id FROM inventory WHERE book_id = 3
) LIMIT 1;