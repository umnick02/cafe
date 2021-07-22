--liquibase formatted sql

--changeset id:1 author:umnick
create table INGREDIENT
(
    ID BIGINT not null
        primary key,
    CARBS DOUBLE,
    FATS DOUBLE,
    KCAL INTEGER,
    NAME VARCHAR(255) not null
        constraint UK_BCUAJ97Y3IU3T2VJ26JG6HIJJ
            unique,
    PIC VARCHAR(255),
    PROTEIN DOUBLE
);

--changeset id:2 author:umnick
create table ORD
(
    ID BIGINT not null
        primary key,
    ADDRESS VARCHAR(255) not null,
    COMMENT VARCHAR(1024),
    CREATED TIMESTAMP,
    DONE BOOLEAN not null,
    NAME VARCHAR(255),
    PAID BOOLEAN not null,
    PHONE VARCHAR(255) not null,
    TOTAL DOUBLE not null
);

--changeset id:3 author:umnick
create table DISH
(
    ID BIGINT not null
        primary key,
    PIC VARCHAR(255) not null,
    ACTIVE BOOLEAN not null,
    DESCRIPTION VARCHAR(512),
    NAME VARCHAR(255) not null
        constraint UK_R7G2L08WDH3UV3GVURLI4S1BX
            unique,
    PRICE DOUBLE not null
);

--changeset id:4 author:umnick
create table PICTURE
(
    ID BIGINT not null
        primary key,
    PRIORITY INTEGER,
    URL VARCHAR(255),
    DISH_ID BIGINT,
    constraint FKQH0HXQQ76OLXYQNER6HQJHHTE
        foreign key (DISH_ID) references DISH (ID)
);

--changeset id:5 author:umnick
create table DISH_ITEM
(
    ID BIGINT not null
        primary key,
    QUANTITY DOUBLE not null,
    INGREDIENT_ID BIGINT not null
        constraint UK_QLVTR0PRK32CTRG906FU3WD1A
            unique,
    DISH_ID BIGINT,
    constraint FKNLHFG3441TIXGMOKSXRRFFHOK
        foreign key (DISH_ID) references DISH (ID),
    constraint FKO0HG9L3158BSCJKUTMLF55DQL
        foreign key (INGREDIENT_ID) references INGREDIENT (ID)
);

--changeset id:6 author:umnick
create table ORD_ITEM
(
    ID BIGINT not null
        primary key,
    QUANTITY INTEGER not null,
    TOTAL DOUBLE not null,
    DISH_ID BIGINT not null
        constraint UK_H299CI4PRO4E7OU3PQ5FVJATQ
            unique,
    ORDER_ID BIGINT not null,
    constraint FK4K4BBI3CG3KUNBIEQ1EVDCIBS
        foreign key (ORDER_ID) references ORD (ID),
    constraint FKER4D39JMVA0DGXAHIRQBBV5Y9
        foreign key (DISH_ID) references DISH (ID)
);

--changeset id:7 author:umnick
create table DELIVERY_WINDOW
(
    ID BIGINT not null
        primary key,
    DAY_OF_WEEK VARCHAR(255) not null,
    FROM_TIME VARCHAR(255) not null,
    TO_TIME VARCHAR(255) not null,
    ACTIVE BOOLEAN not null
);

--changeset id:8 author:umnick
create table ORD_DELIVERY_WINDOWS
(
    ORDER_ID BIGINT not null,
    DELIVERY_WINDOWS_ID BIGINT not null,
    constraint FK81YNOKRBJMBQ8DIW2DYM192G3
        foreign key (ORDER_ID) references ORD (ID),
    constraint FKCOYRXX2RTKD1J2W1EGVVF2Q4G
        foreign key (DELIVERY_WINDOWS_ID) references DELIVERY_WINDOW (ID)
);
