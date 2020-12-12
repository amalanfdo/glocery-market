CREATE TABLE grocery_store(grocery_id int PRIMARY KEY auto_increment,
	gstnumber varchar(255) not null UNIQUE,
	name varchar(255) not null,
	input_user_id varchar(255) not null,
	input_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);

CREATE UNIQUE INDEX idx_gstnumber on grocery_store(gstnumber);

CREATE TABLE category(category_id int PRIMARY kEY auto_increment,
	grocery_id int,
	category_name varchar(255) not null,
	input_user_id varchar(255) not null,
	input_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(grocery_id) References grocery_store(grocery_id)
);

CREATE UNIQUE INDEX idx_category on category(grocery_id,category_id);

CREATE TABLE product(product_id int primary key auto_increment,
	category_id int,
	product_name varchar(50),
	product_description varchar(255),
	product_img varchar(255),
	price float(8),
	rating int,
	stock int,
	stock_limit int,
	product_update_type int,
	input_user_id varchar(255) not null,
	input_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_update_user_id varchar(255) not null,
	last_updatedatetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(category_id) on category(category)
);

CREATE UNIQUE INDEX idx_product on product(category_id,product_id);

CREATE TABLE stock_history(stock_id int primary key ,
	product_id int,
	product_name varchar(255),
	stock int,
	stock_update_type int,
	stock_operation_type int,
	input_user_id varchar(50),
	input_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE porduct_order(product_order_id int primary key auto_increment,
	product_id int,
	order_id int,
	order_quantity int,
	price_each float(8),
	input_user_id varchar(255) not null,
	input_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	// add for
);

CREATE UNIQUE INDEX idx_porduct_order on porduct_order(product_id,order_id);

CREATE TABLE order_detail(order_detail_id int Primary key,
	invoice_number varchar(255),
	user_id int,
	order_status int check (order_status<=10),
	payment_detail_id int,
	shipping_address_id int,
	shipping_date DATE,
	feedback varchar(255),
	order_type int,
	input_user_id varchar(255) not null,
	input_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_update_user_id varchar(255) not null,
	last_updatedatetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create UNIQUE INDEX idx_order_detail on order_detail(user_id,order_id);

CREATE TABLE shipping_address(shipping_address_id int,
	user_id int,
	area varchar(255),
	city varchar(255),
	state varchar(255),
	country varchar(255),
	postal_code varchar(255),
);

CREATE TABLE payment_detail(payment_detail_id int ,
		user_id varchar(255),
 		payment_type int,
 		payment_no varchar(255)
 		input_user_id varchar(255) not null,
		input_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE table user(user_id int PRIMARY key auto_increment,
	mail_id varchar(255),
	first_name varchar(255),
	last_name varchar(255),
	password varchar(255),
	user_status varchar(255),
	is_admin_user varchar(255),
	age varchar(255),
	dateofbirth varchar(255),
	input_user_id varchar(255) not null,
	input_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_update_user_id varchar(255) not null,
	last_updatedatetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- mainly for checkout operation
create view invoice_detail as
select po.product_id as product_id,product_name,product_description,order_quantity,price_each, (order_quantity*price_each) as total_amount , product_img from user u 
inner join order_detail od on (u.user_id = order_detail.user_id) 
inner join product_order po on (od.order_id = po.order_id) 
inner join product p on (od.product_id = p.product_id)  


-- we can also develop batch processing for customer regular purchase , bulk product update , update stock detail
-- with common batch processing table.
-- tables need for batch processing batch_execution_history_table
