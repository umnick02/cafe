--liquibase formatted sql

--changeset id:13 author:umnick
INSERT INTO PICTURE (ID, PRIORITY, URL, DISH_ID) VALUES (1, 1, 'shashlik-mix.jpg', 1);
INSERT INTO PICTURE (ID, PRIORITY, URL, DISH_ID) VALUES (2, 2, 'shashlik-mix-2.jpg', 1);
INSERT INTO PICTURE (ID, PRIORITY, URL, DISH_ID) VALUES (3, 3, 'shashlik-mix-3.jpg', 1);
INSERT INTO PICTURE (ID, PRIORITY, URL, DISH_ID) VALUES (4, 1, 'sauce.jpg', 2);
INSERT INTO PICTURE (ID, PRIORITY, URL, DISH_ID) VALUES (5, 2, 'sauce-2.jpg', 2);
INSERT INTO PICTURE (ID, PRIORITY, URL, DISH_ID) VALUES (6, 4, 'chicken.jpg', 1);
