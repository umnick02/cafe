--liquibase formatted sql

--changeset id:12 author:umnick
INSERT INTO DELIVERY_WINDOW (ID, DAY_OF_WEEK, FROM_TIME, TO_TIME, ACTIVE) VALUES (1, 'MONDAY', '10:00', '12:00', true);
INSERT INTO DELIVERY_WINDOW (ID, DAY_OF_WEEK, FROM_TIME, TO_TIME, ACTIVE) VALUES (2, 'MONDAY', '12:00', '14:00', false);
INSERT INTO DELIVERY_WINDOW (ID, DAY_OF_WEEK, FROM_TIME, TO_TIME, ACTIVE) VALUES (3, 'TUESDAY', '12:00', '14:00', true);