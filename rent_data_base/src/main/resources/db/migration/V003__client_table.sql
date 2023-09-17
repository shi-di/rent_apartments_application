CREATE SEQUENCE client_table_sequence START 11 INCREMENT 1;

CREATE TABLE IF NOT EXISTS client_table
(
    id INT8 PRIMARY KEY NOT NULL,
    user_nick_name VARCHAR,
    user_email VARCHAR,
    user_password VARCHAR,
    card_number VARCHAR,
    data_registration TIMESTAMP
);

INSERT INTO client_table(id, user_nick_name, user_email, user_password, card_number, data_registration)
VALUES (1, 'andrms88', 'andr88@mail.ru', 'ryoK8jxc', '1324 6312 9081 2341', CURRENT_DATE),
       (2, 'malina11', 'malomal1@yandex.ru', 'qwpo901c', '4561 3322 8100 3412', CURRENT_DATE),
       (3, 'prostoVo', 'mangood12@mail.ru', 'vbdhrey3X', '5431 5468 9087 1386', CURRENT_DATE),
       (4, 'alyona1990', 'alyo19@gmail.com', 'ldpo91na', '4312 3421 6540 8715', CURRENT_DATE),
       (5, 'sportDi', 'newsportman@mail.ru', 'vnji2098kla', '1109 2341 6784 1230', CURRENT_DATE),
       (6, 'aleks21', 'aleksDev@mail.ru', 'geAleks07', '2406 1249 8721 2310', CURRENT_DATE),
       (7, 'nadejda3', 'esperanso01n@gmail.com', 'saleespernas1x', '2379 6310 2019 2740', CURRENT_DATE),
       (8, 'Leo1989', 'leo_Di@yandex.ru', 'xx1leo$', '7631 0087 3421 0983', CURRENT_DATE),
       (9, 'olga25', 'olgapro@mail.ru', 'lpehfg2', '0931 9844 6467 2341', CURRENT_DATE),
       (10, 'annademidovna3', 'andemid@yandex.ru', 'wewewek18ds', '2092 0022 3941 4402', CURRENT_DATE);
