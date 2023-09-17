ALTER TABLE integration_service ADD COLUMN service_name VARCHAR;


UPDATE integration_service SET service_name = 'opencagedata.com' WHERE id = 1;