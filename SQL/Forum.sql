create table users(
id int(11) not null primary key auto_increment,
login_id varchar(20) not null unique,
password varchar(255) not null,
name varchar(10) not null,
store_id int(11) not null,
department_id int(11) not null,
state tinyint(1) default 0,
insert_date datetime,
update_date datetime
);
    
    
    
create table stores(
id int(11) not null primary key auto_increment,
name varchar(255) not null
);



create table posts(
id int(11) not null primary key auto_increment,
title varchar(50) not null,
text varchar(1000) not null,
user_id int(11) not null,
category varchar(10) not null,
insert_date datetime
);



create table departments(
id int(11) not null primary key auto_increment,
name varchar(255) not null
);


create table comments(
id int(11) not null primary key auto_increment,
post_id int(11) not null,
text varchar(500) not null,
user_id int(11) not null,
insert_date timestamp
);