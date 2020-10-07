DELETE FROM orders;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100;

INSERT INTO users (name, email, password, role)
VALUES ('Manager1', 'manager11@mail.ru', '{noop}password', 'MANAGER'),
       ('Manager2', 'manager22@mail.ru', '{noop}password', 'MANAGER'),
       ('Admin', 'admin@yandex.ru', '{noop}admin', 'ADMIN');

INSERT INTO orders (user_id, date, customer_name, customer_phone, customer_address, product)
VALUES (100, '2020-09-03', 'Иванов Олег Петрович', '89274072569', 'Челябинск', 'MAGNUM_22L'),
       (101, '2020-09-03', 'Терентьев Игорь Сергеевич', '89112345823', 'Москва', 'ESSEN_12L'),
       (100, '2020-09-03', 'Крысанов Андрей Николаевич', '89174036869', 'Пенза', 'ESSEN_20L'),
       (101, '2020-09-04', 'Чинилов Иван Викторович', '89126133167', 'Тюмень', 'MAGNUM_32L'),
       (100, '2020-09-04', 'Панов Сергей Юрьевич', '89184094450', 'Ростов', 'ESSEN_30L'),
       (100, '2020-09-05', 'Зерюкова Наталия Владимировна', '89625061757', 'Дзержинск', 'ESSEN_20L'),
       (100, '2020-09-05', 'Левин Сергей Николаевич', '89831035506', 'Барнаул', 'MAGNUM_32L'),
       (101, '2020-09-05', 'Белов Ефим Федорович', '89252229541', 'Зеленоград', 'ESSEN_12L'),
       (100, '2020-09-06', 'Серегин Максим Сергеевич', '89192056393', 'Орел', 'ESSEN_20L'),
       (101, '2020-09-06', 'Рубец Лариса Анатольевна', '89182877033', 'Тимашевск ', 'ESSEN_30L');
