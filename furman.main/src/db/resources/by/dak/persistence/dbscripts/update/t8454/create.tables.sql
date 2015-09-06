--FURNITURE_CODE_LINK
create table FURNITURE_CODE_LINK (ID bigint generated by default as identity,
KEYWORD VARCHAR(255) not null,
PARENT_ID bigint not null,
CHILD_ID bigint not null,
constraint PK_FURNITURE_CODE_LINK primary key (ID) ,
unique (KEYWORD, PARENT_ID, CHILD_ID)
);
