
create table CONTACT (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	FIRST_NAME VARCHAR(255),
	LAST_NAME VARCHAR(255),
	PHONE_NUMBER CHAR(20)
);

insert into CONTACT (FIRST_NAME, LAST_NAME) values ('Jane', 'Doe');
insert into CONTACT (FIRST_NAME, LAST_NAME) values ('John', 'Deer');
