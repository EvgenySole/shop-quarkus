INSERT INTO categories (categoryname, description) VALUES
    ('Мониторы', 'Устройство вывода изображения');
INSERT INTO categories (categoryname, description) VALUES
    ('Ноутбуки', 'Переносной компактный компьютер');
INSERT INTO categories (categoryname, description) VALUES
    ('ПК', 'Стационарный персональный компьютер');
INSERT INTO categories (categoryname, description) VALUES
    ('Клавиатуры', 'Устройство ввода текста');
INSERT INTO categories (categoryname, description) VALUES
    ('Мыши', 'Устройство ввода и управления');
INSERT INTO categories (categoryname, description) VALUES
    ('Колонки', 'Устройство вывода звука');
INSERT INTO categories (categoryname, description) VALUES
    ('Наушники', 'Устройство вывода звука');
INSERT INTO categories (categoryname, description) VALUES
    ('Вэб-камеры', 'Устройство ввода видеоизображения');
INSERT INTO categories (categoryname, description) VALUES
    ('Принтеры', 'Устройство вывода текстовой и графической информации');
INSERT INTO categories (categoryname, description) VALUES
    ('Сканеры', 'Устройство ввода текстовой и графической информации');


INSERT INTO models (modelname, parameters) VALUES
    ('Монитор 1', 'Диагональ 24", разрешение FullHD, частота 60Hz');
INSERT INTO models (modelname, parameters) VALUES
    ('Монитор 2', 'Диагональ 27", разрешение 2K, частота 120Hz');
INSERT INTO models (modelname, parameters) VALUES
    ('Монитор 3', 'Диагональ 32", разрешение 4K, частота 180Hz');

INSERT INTO models (modelname, parameters) VALUES
    ('Ноутбук 1', 'Диагональ 14", разрешение FullHD, процессор i3');
INSERT INTO models (modelname, parameters) VALUES
    ('Ноутбук 2', 'Диагональ 17", разрешение 2K, процессор i5');
INSERT INTO models (modelname, parameters) VALUES
    ('Ноутбук 3', 'Диагональ 21", разрешение 4K, процессор i7');

INSERT INTO models (modelname, parameters) VALUES
    ('ПК 1', 'Процессор i3, 4 ядра, 8 Гб ОЗУ');
INSERT INTO models (modelname, parameters) VALUES
    ('ПК 2', 'Процессор i5, 6 ядер, 16 Гб ОЗУ');
INSERT INTO models (modelname, parameters) VALUES
    ('ПК 3', 'Процессор i7, 8 ядер, 32 Гб ОЗУ');

INSERT INTO models (modelname, parameters) VALUES
    ('Клавиатура 1', 'Мембранная, проводная');
INSERT INTO models (modelname, parameters) VALUES
    ('Клавиатура 2', 'Мембранная, беспроводная');
INSERT INTO models (modelname, parameters) VALUES
    ('Клавиатура 3', 'Механическая, проводная, с подсветкой');

INSERT INTO models (modelname, parameters) VALUES
    ('Мышь 1', 'Офисная, проводная, 1000 dpi');
INSERT INTO models (modelname, parameters) VALUES
    ('Мышь 2', 'Игровая, проводная, 1600 dpi');
INSERT INTO models (modelname, parameters) VALUES
    ('Мышь 3', 'Игровая, беспроводная, 3200 dpi');

INSERT INTO models (modelname, parameters) VALUES
    ('Колонки 1', 'Мощность 5 Вт, 2 штуки');
INSERT INTO models (modelname, parameters) VALUES
    ('Колонки 2', 'Мощность 15 Вт, 4 штуки');
INSERT INTO models (modelname, parameters) VALUES
    ('Колонки 3', 'Мощность 30 Вт, 5 штук, с сабвуфером');

INSERT INTO models (modelname, parameters) VALUES
    ('Наушники 1', 'Проводные, вакуумные');
INSERT INTO models (modelname, parameters) VALUES
    ('Наушники 2', 'Проводные, с шумоподавлением');
INSERT INTO models (modelname, parameters) VALUES
    ('Наушники 3', 'Беспроводные, с шумоподавлением');

INSERT INTO models (modelname, parameters) VALUES
    ('Вэб-камера 1', 'Качество 480p');
INSERT INTO models (modelname, parameters) VALUES
    ('Вэб-камера 2', 'Качество 1080p');
INSERT INTO models (modelname, parameters) VALUES
    ('Вэб-камера 3', 'Качество 2160p');

INSERT INTO models (modelname, parameters) VALUES
    ('Принтер 1', 'Струйный, формат А4');
INSERT INTO models (modelname, parameters) VALUES
    ('Принтер 2', 'Струйный, формат А3');
INSERT INTO models (modelname, parameters) VALUES
    ('Принтер 3', 'Лазерный, формат А3');

INSERT INTO models (modelname, parameters) VALUES
    ('Сканер 1', 'Безлинзовый, формат А4');
INSERT INTO models (modelname, parameters) VALUES
    ('Сканер 2', 'Безлинзовый, формат А3');
INSERT INTO models (modelname, parameters) VALUES
    ('Сканер 3', 'Линзовый, формат А3');


INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Отличный монитор по выгодной цене из бюджетного сегмента', 7990,
     'https://www.trustedreviews.com/wp-content/uploads/sites/54/2020/09/AOC-24-01.jpg',
     current_timestamp, 1, 1, 10);
INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Отличный монитор по выгодной цене из топового сегмента', 27990,
     'https://www.foroffice.ru/upload/iblock/3c8/DC43_E3.jpg',
     current_timestamp, 1, 3, 15);
INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Производительный ноутбук с тонким дизайном', 84990,
     'https://u-begemota.ru/wa-data/public/shop/products/20/53/108375320/images/93765/93765.970.png',
     current_timestamp, 2, 5, 32);
INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Высокоскоростной компьютер, позволяющий как работать, так и играть', 84990,
     'https://www.foroffice.ru/upload/iblock/3c8/DC43_E3.jpg',
     current_timestamp, 3, 9, 14);
INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Удобная беспроводная клавиатура с большим диапазоном работы', 2590,
     'https://cache-kirov.ru/upload/57c6da0bab0ba.jpg',
     current_timestamp, 4, 11, 7);
INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Красивая и с быстрым откликом клавиатура позволит не пропустить ни единого момента', 4890,
     'https://avatars.mds.yandex.net/get-mpic/4220209/img_id893948961347058998.jpeg/orig',
     current_timestamp, 4, 12, 16);
INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Простая, но надежная мышь прослужит долго и не потребует внимания', 790,
     'https://avatars.mds.yandex.net/i?id=aa361c4c3382cf8f2474a2e800b4953d_l-5042534-images-thumbs&n=13',
     current_timestamp, 5, 13, 4);
INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Беспроводная мышь с высокой четкостью, а также с красивой подсветкой', 2990,
     'https://cdn1.ozone.ru/s3/multimedia-1-q/6937097066.jpg',
     current_timestamp, 5, 14, 23);
INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Принтер для домашнего использования с небольшими форматами документов', 7990,
     'https://avatars.mds.yandex.net/i?id=c77bcdec82f89062b5ceffeb09d47f23_l-5680151-images-thumbs&n=13',
     current_timestamp, 9, 25, 10);
INSERT INTO products (description, price, productimage, productiondate, categoryid, modelnumber, quantity) VALUES
    ('Высококлассный сканер формата до А3, предназначен для компаний', 56490,
     'https://asset.conrad.com/media10/isa/160267/c1/-/en/884056_RB_00_FB/image.jpg',
     current_timestamp, 10, 30, 3);


INSERT INTO statuses (statusname) VALUES ('Создан');
INSERT INTO statuses (statusname) VALUES ('В обработке');
INSERT INTO statuses (statusname) VALUES ('Передан в доставку');
INSERT INTO statuses (statusname) VALUES ('Выполнен');
INSERT INTO statuses (statusname) VALUES ('Ожидается отзыв');

INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Целинная, д. 146', 'jbirnseru@gmail.com',
     'Антонов Виктор Федорович', +73672468627);
INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Моторная, д. 58', 'dftyhdyth@yandex.ru',
     'Рыжков Роман Степанович', +77867533797);
INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Товарная, д. 83', 'yyyrretyseu@mail.ru',
     'Ломтев Семён Георгиевич', +79433678478);
INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Жарева, д. 2', 'miueiyvs@gmail.com',
     'Зубков Захар Данилович', +79477822146);
INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Елкина, д. 34', 'khutruiw@bing.com',
     'Леонов Яков Артемович', +77543566543);
INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Хорошая, д. 94', 'uthvye@mail.com',
     'Охров Виктор Борисович', +77429536523);
INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Лучевая, д. 39', 'dtoiurstuigf@yabdex.ru',
     'Верхний Леонид Владимирович', +78426894591);
INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Экранная, д. 110', 'huuurrvvcc@gmail.com',
     'Жарков Юрий Николаевич', +78239459317);
INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Открытая, д. 44', 'ghhttyrtyrt@bing.com',
     'Вкусно Харитон Юрьевич', +78637429565);
INSERT INTO clients (address, email, name, phonenumber) VALUES
    ('ул. Целинная, д. 146', 'jbirnseru@gmail.com',
     'Бойко Дмитрий Романович', +78659349674);


INSERT INTO orders (orderdate, orderprice, shipdate, clientid, statusid) VALUES
    (current_timestamp, 311740, current_timestamp, 1, 1);
INSERT INTO orders (orderdate, orderprice, shipdate, clientid, statusid) VALUES
    (current_timestamp, 185960, current_timestamp, 3, 2);
INSERT INTO orders (orderdate, orderprice, shipdate, clientid, statusid) VALUES
    (current_timestamp, 51110, current_timestamp, 5, 2);
INSERT INTO orders (orderdate, orderprice, shipdate, clientid, statusid) VALUES
    (current_timestamp, 260550, current_timestamp, 7, 1);
INSERT INTO orders (orderdate, orderprice, shipdate, clientid, statusid) VALUES
    (current_timestamp, 258430, current_timestamp, 9, 3);
INSERT INTO orders (orderdate, orderprice, shipdate, clientid, statusid) VALUES
    (current_timestamp, 395430, current_timestamp, 10, 4);


INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (2, 2, 27990, 1);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (4, 3, 84990, 1);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (7, 1, 790, 1);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (1, 2, 7990, 2);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (4, 2, 84990, 2);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (5, 2, 2590, 3);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (8, 2, 2990, 3);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (9, 5, 7990, 3);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (4, 3, 84990, 4);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (5, 1, 2590, 4);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (8, 1, 2990, 4);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (1, 4, 7990, 5);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (3, 2, 84990, 5);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (10, 1, 56490, 5);
INSERT INTO orderdetails (productid, quantity, unitprice, orderid) VALUES
    (10, 7, 56490, 6);





