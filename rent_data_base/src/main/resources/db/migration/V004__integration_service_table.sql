CREATE SEQUENCE integration_service_sequence START 2 INCREMENT 1;

CREATE TABLE IF NOT EXISTS integration_service
(
    id int8 PRIMARY KEY NOT NULL,
    url_location_info VARCHAR,
    application_key VARCHAR
);


INSERT INTO integration_service(id, url_location_info, application_key)
VALUES (1, 'http://api.opencagedata.com/geocode/v1/json?q=%s+%s&no_annotations=1&key=%s', '399c11cca2064008ba42c301af98c906');