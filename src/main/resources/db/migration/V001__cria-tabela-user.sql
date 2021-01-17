create table tb_user (
	ID serial not null primary key,
	name VARCHAR(60) not null,
	email varchar(255) not null,
	phone varchar(20) not null
);