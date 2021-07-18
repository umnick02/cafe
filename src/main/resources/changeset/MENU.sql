--liquibase formatted sql

--changeset id:14 author:umnick
INSERT INTO MENU (ID, PIC, ACTIVE, DESCRIPTION, NAME, PRICE) VALUES (592, null, true, null, 'Шашлык из свинины с томатным соусом', 6.9);
INSERT INTO MENU (ID, PIC, ACTIVE, DESCRIPTION, NAME, PRICE) VALUES (593, null, true, null, null, null);
INSERT INTO MENU (ID, PIC, ACTIVE, DESCRIPTION, NAME, PRICE) VALUES (594, null, false, null, null, 0.9);