drop table users if exists

create table users (id bigint generated by default as identity, name varchar(50), primary key (id))
