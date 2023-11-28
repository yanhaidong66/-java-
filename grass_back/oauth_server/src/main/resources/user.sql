create database oauth;
use oauth;
create table t_user
(
    user_id            int auto_increment not null primary key,
    user_username      varchar(32)        not null unique ,
    user_password      varchar(32)        not null,
    user_emile         varchar(64)        not null,
    user_create_time   datetime default current_timestamp,
    user_modified_time datetime default current_timestamp on update current_timestamp
);
create index ind_username
on t_user(user_username);


