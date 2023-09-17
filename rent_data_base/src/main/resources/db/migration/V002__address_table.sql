CREATE SEQUENCE address_table_sequence START 11 INCREMENT 1;

CREATE TABLE IF NOT EXISTS address_table
(
    id                 INT8 PRIMARY KEY NOT NULL,
    city               VARCHAR,
    street             VARCHAR,
    apartments_number VARCHAR,
    id_apartment      INT8 REFERENCES apartments_table (id)
);

INSERT INTO address_table(id, city, street, apartments_number, id_apartment)
VALUES (1, 'Moscow', 'Pyatnickaya', '8', 1),
       (2, 'Moscow', 'Tverskaya', '77', 2),
       (3, 'Moscow', 'Akademicheskaya', '4', 3),
       (4, 'Moscow', 'Leningradskaya', '22', 4),
       (5, 'Moscow', 'Stroginskaya', '2', 5),
       (6, 'Moscow', 'Mitinskaya', '35', 6),
       (7, 'Moscow', 'Stroginskaya', '65', 7),
       (8, 'Moscow', 'Stroginskaya', '4', 8),
       (9, 'Moscow', 'Stroginskaya', '84', 9),
       (10, 'Moscow', 'Stroginskaya', '8', 10);

