create table ORDER_GROUP(ID bigint generated by default as identity,
NAME VARCHAR(255) not null,
constraint PK_ORDER_GROUP primary key (ID)
);