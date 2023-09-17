CREATE SEQUENCE product_table_sequence START 11 INCREMENT 1;

CREATE TABLE IF NOT EXISTS product_table
(
    id                     INT8 PRIMARY KEY NOT NULL,
    offer                  VARCHAR,
    status_active_inactive BOOLEAN,
    discount_amount        INT4,
    seasonality_of_offers  VARCHAR
);

INSERT INTO product_table(id, offer, status_active_inactive, discount_amount, seasonality_of_offers)
VALUES (1, 'Скидка для новых клиентов', true, 5, 'all'),
       (2, 'Сезонная скидка для гостей культурной столицы', true, 8, 'summer'),
       (3, 'Сезонная скидка для жителей Москвы', false, 10, 'winter'),
       (4, 'Скидка для постоянных клиентов забронировавшие более 10 раз', true, 10, 'all'),
       (5, 'Скидка для постоянных клиентов забронировавшие более 15 раз', true, 15, 'all'),
       (6, 'Сезонная скидка для гостей столицы Москва', true, 8, 'autumn'),
       (7, 'Скидка при оплате картой МИР', true, 5, 'all'),
       (8, 'Скидка при бронировавшие от 5 суток', true, 10, 'all'),
       (9, 'Скидка при бронировавшие от 14 суток', true, 20, 'all'),
       (10, 'Скидка при бронировавшие от 30 суток', true, 25, 'all');