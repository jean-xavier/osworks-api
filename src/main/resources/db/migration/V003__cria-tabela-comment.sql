create table tb_comment(
	id serial not null primary key,
	order_service_id int not null,
	description varchar(200) not null,
	date_has_sent date not null
);

alter table tb_comment add constraint fk_comment_tb_order_service
foreign key (order_service_id) references tb_order_service(id);