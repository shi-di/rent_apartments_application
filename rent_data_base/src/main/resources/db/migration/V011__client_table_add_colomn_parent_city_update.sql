ALTER TABLE client_table ADD COLUMN parent_city VARCHAR;


UPDATE client_table SET parent_city = 'Санкт-Петербург' WHERE id = 1;
UPDATE client_table SET parent_city = 'Москва' WHERE id = 2;
UPDATE client_table SET parent_city = 'Ярославль' WHERE id = 3;
UPDATE client_table SET parent_city = 'Калининград' WHERE id = 4;
UPDATE client_table SET parent_city = 'Санкт-Петербург' WHERE id = 5;
UPDATE client_table SET parent_city = 'Москва' WHERE id = 6;
UPDATE client_table SET parent_city = 'Пенза' WHERE id = 7;
UPDATE client_table SET parent_city = 'Владивосток' WHERE id = 8;
UPDATE client_table SET parent_city = 'Москва' WHERE id = 9;
UPDATE client_table SET parent_city = 'Новосибирск' WHERE id = 10;