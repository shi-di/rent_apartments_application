Информация о приложении
Приложение Rent apartments предназначено для бронирования апартаментов, а также регистрации новых апартаментов для сдачи в аренду, поиска свободных апартаментов по геолокации пользователя, а также оставлять отзывы и вести глобальный рейтинг апартаментов.

Приложение представляет собой REST API с микросервисной архитектурой на базе SpringBoot, версия 3.1.2. Микросервисы зарегистрированы на Eureka Server (модуль rent-server), маршрутизация запросов производится через API Gateway (модуль api-gateway), в связи с этим остальные модули используют нулевые порты.

Взаимодействие между микросервисами производится с помощью Resttemplate. В микросервисах используются реляционные базы данных PostgreSQL, взаимодействие с которыми производится посредством Spring Data JPA, реализующим Hibernate. 

Заполнение первичной информации в базы данных производится из SQL-скриптов с помощью Flyway migration. Обмен значениями полей между сущностями Entity и DTO производится с помощью Mapstruct. Также в микросервисах написаны интеграционные и Unit тесты.

Приложение состоит из следующих модулей:

rent-server - в нем развернут Eureka Server;

api - gateway - в нем развернут API Gateway;

rent-apartment - выполняет регистрацию и авторизацию новых пользователей, регистрацию новых апартаментов, получение списка квартир свободных для бронирования апартаментов и возможность оставлять по желанию отзывы и ставить оценку в виде рейтинга а так же бронировать апартаменты;

rent-product - производит обработку бронирования апартаментов для предоставления скидки пользователю и отправки письма на почту.

rent-data-base – содержит информацию о пользователях, апартаментах, историю бронирования, рейтинг апартаментов, продуктовых скидках пользователю а так же интеграционные данные (погоды, геолокации и продуктового модуля)

Описание работы приложения

Процесс работы приложения состоит из нескольких этапов:

1й этап- регистрация нового пользователя: 

при запросе на http://localhost:9099/application/registration_user, на RegistrationController сервиса rent-apartment от клиента поступает данные, содержащие следующие поля:

 userNickName - имя клиента

 userEmail - почта 
 
 userPassword - пароль

 numberCard - номер карты

 parentCity - родной город

![Скриншот 12-09-2023 11 12 38](https://github.com/shi-di/rent_apartments_application/assets/145255560/08cecbf9-594b-4903-85df-86ec91368e89)

Если на почту был зарегистрирован пользователь, то предлагается выбрать другую почту.

![Скриншот 12-09-2023 11 14 30](https://github.com/shi-di/rent_apartments_application/assets/145255560/57f560b9-df27-4c44-8e70-1e0457889f3c)

Когда пользователь зарегистрировался, все данные сохраняются в базу данных.

![Скриншот 12-09-2023 11 15 20](https://github.com/shi-di/rent_apartments_application/assets/145255560/24c0723e-7459-4a91-9509-567e7fa97ae5)

2й этап- авторизация пользователя: 

при запросе на http://localhost:9099/application/authorization_user, на RegistrationController сервиса rent-apartment от клиента поступает данные, содержащие следующие поля:

userEmail - почта клиента

userPassword – пароль

![Скриншот 12-09-2023 11 16 11](https://github.com/shi-di/rent_apartments_application/assets/145255560/86178923-f24b-45a2-9442-3530701f8212)

Если пользователь предоставит не зарегистрированную почту на сервисе, то выйдет сообщение

![Скриншот 12-09-2023 11 17 01](https://github.com/shi-di/rent_apartments_application/assets/145255560/81043881-38d9-4f6a-b8a3-33c13d356b2c)

3й этап – регистрация апартаментов или получение полного списка свободных апартаментов для бронирования: 

при запросе на http://localhost:9099/registration-apartment, на RentApartmentController, сервиса rent-apartment от клиента поступает данные, содержащие следующие поля:

city - город

street - улица

apartmentsNumber – номер апартаментов

roomAmount – количество комнат

price – цена за сутки

![Скриншот 12-09-2023 11 17 35](https://github.com/shi-di/rent_apartments_application/assets/145255560/c9434d84-905e-4d46-9cf9-7beac4df1bd3)

Когда пользователь зарегистрировал апартаменты, все данные сохраняются в базу данных в таблицы apartments_table

![Скриншот 12-09-2023 11 18 24](https://github.com/shi-di/rent_apartments_application/assets/145255560/aab391f9-57a8-4555-8a44-a38bf337d401)

и address_table

![Скриншот 12-09-2023 11 19 10](https://github.com/shi-di/rent_apartments_application/assets/145255560/92b61770-75ea-4201-b4ce-3c7dcc37c9ff)

Для получение полного списка свободных апартаментов для бронирования по геолокации, где находится пользователь: 

при запросе на http://localhost:9099/get-apartments, на RentApartmentController, сервиса rent-apartment от клиента поступает данные, содержащая следующие поля в виде широты и долготы:

latitude - широта

longitude – долгота

С помощью интеграции RestTemplate сервиса http://api.opencagedata.com/geocode получаем геолокацию пользователя и выгружаем все свободные апартаменты из базы данных
по городу с дополнительной информации о погоде (так же применяем RestTemplate сервиса https://api.weather.yandex.ru)

![Скриншот 12-09-2023 11 20 43](https://github.com/shi-di/rent_apartments_application/assets/145255560/9b1db5e8-6050-4f82-a43c-e5d9d12fc2a7)

4й этап - бронирование апартаментов: 

при запросе на http://localhost:9099/application/booking-apartment?id=8&startBookingDate=2023-09-10T13:00:00&endBookingDate=2023-10-15T14:00:00, на RegistrationController сервиса rent-apartment от клиента поступает данные, содержащие следующие аргументы:

id – id апартаментов

startBookingDate – дата начала бронирования

endBookingDate – дата окончания бронирования

![Скриншот 12-09-2023 11 21 46](https://github.com/shi-di/rent_apartments_application/assets/145255560/cd86845c-ecf0-4464-a887-984c6431d3f4)

Когда пользователь забронировал апартаменты, все данные сохраняются в базу данных в таблицу apartments_table в колонке available значение true изменяется на false (тем самым квартира для бронирования не доступна) в колонке booking_to указанно до какого дня апартаменты заняты.

![Скриншот 12-09-2023 11 22 48](https://github.com/shi-di/rent_apartments_application/assets/145255560/67419133-586d-4204-a2e0-78d164530f01)

При бронировании апартаментов для пользователя высчитывается скидка из продуктового модуля rent_product

С помощью интеграции RestTemplate интегрируемся в модуль http://localhost:9099/product-discount?id=%s
В модуле rent_product в каталоге scheduler, название класса EventManagerScheduler производится распределение скидок каждые 1.5 минуты, например: всем новым пользователям присваивается скидка 5%, осенняя сезонная скидка для гостей столицы в период 01.09.2023 по 30.11.2023 присваивается 8%, но если пользователь забронировал более чем на 30 суток, то он получает максимальную скидку в 25%.

Таблица product_table

![Скриншот 12-09-2023 11 26 26](https://github.com/shi-di/rent_apartments_application/assets/145255560/3f167da9-6439-48c8-b470-096a381184c4)

После бронировании апартаментов пользователю отправляется письмо на почту через EmailSender, каталог service, класс EmailSenderServiceImpl.

![Скриншот 12-09-2023 11 27 09](https://github.com/shi-di/rent_apartments_application/assets/145255560/04d45794-82b3-48f5-b3d8-3e2f53eabaec)

![Скриншот 12-09-2023 11 28 04](https://github.com/shi-di/rent_apartments_application/assets/145255560/2aa6d445-3a02-4016-8583-70585108a3bc)

5й этап - пользователь может оставить отзыв и поставить оценку: 

при запросе на http://localhost:9099/application/rating-apartments, на RegistrationController сервиса rent-apartment от клиента поступает данные, содержащие следующие аргументы:

id – id апартаментов

apartmentId – id апартаментов

rating – оценка апартаментов

comment – отзыв

![Скриншот 12-09-2023 11 28 55](https://github.com/shi-di/rent_apartments_application/assets/145255560/9f01af58-16ec-441d-a121-11667b85a686)


Когда пользователь написал отзыв и поставил оценку, все данные сохраняются в базу данных в таблицу rating_table

![Скриншот 12-09-2023 11 29 43](https://github.com/shi-di/rent_apartments_application/assets/145255560/38578aae-cc54-44f4-bfd9-6a85f1ed7e6e)

Так же в таблице apartments_table в колонку global_rating присваивается глобальный средний рейтинг

![Скриншот 12-09-2023 11 30 30](https://github.com/shi-di/rent_apartments_application/assets/145255560/dbed303e-202d-47e4-93b7-8fe643c3d7ba)

Все данные сохраняются в БД в таблицу booking_history в колонках start_booking_date (дата начала бронирования), end_booking_date (дата окончания бронирования), apartment_id (id апартаментов), client_id (id пользователя), product_id (id предоставленной скидки при бронировании)

![Скриншот 12-09-2023 11 31 09](https://github.com/shi-di/rent_apartments_application/assets/145255560/d4ca442c-2ee5-40a6-a492-c2c4eef50451)







