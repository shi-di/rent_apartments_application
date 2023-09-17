CREATE SEQUENCE booking_history_sequence START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS booking_history
(
    id                 INT8 PRIMARY KEY NOT NULL,
    start_booking_date TIMESTAMP,
    end_booking_date   TIMESTAMP,
    apartment_id       INT8 REFERENCES apartments_table (id),
    client_id          INT8 REFERENCES client_table (id),
    product_id         INT8 REFERENCES product_table (id)
);