create table CATEGORY (ID bigint generated by default as identity,
NAME VARCHAR(255) not null,
DESCRIPTION VARCHAR(2147483647),
FILE_UUID VARCHAR(255),
constraint PK_CATEGORY primary key (ID),
);