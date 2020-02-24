drop table sales;
drop table stock;
drop table supply;
drop table items;
drop table dept;


create table dept(dept_id number(2) primary key, dep_name varchar(20) not null);
create table items(dept_id number(2),item_id number(5) primary key, item_name varchar(20) not null, c_price number(5) not null, s_price number(5),foreign key(dept_id) references dept(dept_id));
create table supply(sup_id number(5) primary key,sup_date date not null, item_id number(5) not null, sup_qty number(5) not null, man_date date not null, exp_date date,foreign key(item_id) references items(item_id));
create table stock(item_id number(5) primary key, qty number(5),foreign key(item_id) references items(item_id));
create table sales(sale_id number(5), sale_date date not null, item_id number(5), sale_qty number(3) not null ,foreign key(item_id) references items(item_id));
