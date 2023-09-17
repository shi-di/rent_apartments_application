ALTER TABLE client_table ADD COLUMN using_count INT4;


UPDATE client_table SET using_count = 0 WHERE id = 1;
UPDATE client_table SET using_count = 4 WHERE id = 2;
UPDATE client_table SET using_count = 11 WHERE id = 3;
UPDATE client_table SET using_count = 5 WHERE id = 4;
UPDATE client_table SET using_count = 3 WHERE id = 5;
UPDATE client_table SET using_count = 5 WHERE id = 6;
UPDATE client_table SET using_count = 3 WHERE id = 7;
UPDATE client_table SET using_count = 15 WHERE id = 8;
UPDATE client_table SET using_count = 31 WHERE id = 9;
UPDATE client_table SET using_count = 0 WHERE id = 10;

ALTER TABLE client_table ADD COLUMN first_product INT8 REFERENCES product_table(id);


ALTER TABLE client_table ADD COLUMN second_product INT8 REFERENCES product_table(id);