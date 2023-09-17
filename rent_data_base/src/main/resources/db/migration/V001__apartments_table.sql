CREATE SEQUENCE apartments_table_sequence START 11 INCREMENT 1;

CREATE TABLE IF NOT EXISTS apartments_table
(
    id INT8 PRIMARY KEY NOT NULL,
    room_amount VARCHAR,
    price VARCHAR,
    date_lot_registration TIMESTAMP
);

INSERT INTO apartments_table(id, room_amount, price, date_lot_registration)
VALUES (1, 2, 7000, CURRENT_DATE),
       (2, 1, 4500, CURRENT_DATE),
       (3, 1, 3800, CURRENT_DATE),
       (4, 2, 6000, CURRENT_DATE),
       (5, 3, 9800, CURRENT_DATE),
       (6, 1, 4000, CURRENT_DATE),
       (7, 1, 5300, CURRENT_DATE),
       (8, 3, 9500, CURRENT_DATE),
       (9, 3, 8700, CURRENT_DATE),
       (10, 2, 7200, CURRENT_DATE);

