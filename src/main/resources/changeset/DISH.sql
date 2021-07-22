--liquibase formatted sql

--changeset id:9 author:umnick
INSERT INTO DISH (ID, PIC, ACTIVE, DESCRIPTION, NAME, PRICE) VALUES (1, '', true, null, 'шашлык из свинины', 6);
INSERT INTO DISH (ID, PIC, ACTIVE, DESCRIPTION, NAME, PRICE) VALUES (2, '', true, null, 'томатный соус', 0.9);