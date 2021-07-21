--liquibase formatted sql

--changeset id:14 author:umnick
INSERT INTO MENU (ID, PIC, ACTIVE, DESCRIPTION, NAME, PRICE, PRIORITY) VALUES (592, null, true, null, 'Шашлык из свинины с томатным соусом', 6.9, 1);
INSERT INTO MENU (ID, PIC, ACTIVE, DESCRIPTION, NAME, PRICE, PRIORITY) VALUES (593, null, true, null, null, null, 2);
INSERT INTO MENU (ID, PIC, ACTIVE, DESCRIPTION, NAME, PRICE, PRIORITY) VALUES (594, null, false, null, null, 0.9, 3);