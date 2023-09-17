ALTER TABLE integration_service ADD COLUMN key_name VARCHAR;


UPDATE integration_service SET key_name = 'X-Yandex-API-Key' WHERE id = 2;