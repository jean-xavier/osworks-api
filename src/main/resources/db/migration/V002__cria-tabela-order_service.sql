create table tb_order_service (
	id serial not null primary key,
	user_id integer not null,
	description varchar(200) not null,
	price real not null,
	status varchar(20) not null,
	date_has_opened date not null,
	date_has_finished date
);

alter table tb_order_service
add constraint fk_order_service_user
foreign key (user_id) references tb_user(id);