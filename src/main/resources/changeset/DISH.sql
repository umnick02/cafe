--liquibase formatted sql

--changeset id:11 author:umnick
INSERT INTO DISH (ID, PIC, DESCRIPTION, NAME, PRICE) VALUES (585, '', null, 'шашлык из свинины', 6);
INSERT INTO DISH (ID, PIC, DESCRIPTION, NAME, PRICE) VALUES (587, '', null, 'томатный соус', 0.9);