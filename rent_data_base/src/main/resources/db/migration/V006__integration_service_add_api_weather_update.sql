INSERT INTO integration_service(id, url_location_info, application_key, service_name)
VALUES (2, 'https://api.weather.yandex.ru/v2/informers?lat=%s&lon=%s&lang=ru_RU',
        'eae6a43d-4ebe-4b21-95e5-5d1087187470', 'weather.yandex.ru'),
        (3, 'http://localhost:8087/product-discount', null, 'mcs.product.discount');