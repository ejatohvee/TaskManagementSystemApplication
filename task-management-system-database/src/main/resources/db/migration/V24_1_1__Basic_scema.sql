create schema if not exists tasks_list;

create table tasks_list.t_task(
    id uuid primary key,
    c_title varchar not null,
    c_description varchar,
    c_status int not null,
    c_priority int not null,
    c_author varchar not null,
    c_performer varchar not null
);

create table tasks_list.t_comment(
    id uuid primary key,
    c_body varchar not null,
    c_author varchar not null,
    c_time varchar not null,
    task_id uuid not null references tasks_list.t_task(id)
);

drop table tasks_list.t_comment;

create schema if not exists users_management;

create table users_management.t_users(
    id uuid primary key,
    c_username varchar not null check (length(trim(c_username)) >= 3) unique,
    c_email varchar not null unique,
    c_password varchar not null
);

create table users_management.t_authorities(
    id serial primary key,
    c_authority varchar not null
);

alter table users_management.t_users
    add constraint c_username_length check (length(trim(c_username)) >= 3),
    add constraint c_username_unique UNIQUE (c_username);

create table users_management.user_authority(
    id serial primary key,
    id_authority int not null references users_management.t_authorities (id),
    id_user uuid not null references users_management.t_users (id),
    constraint uk_user_authority unique (id_authority, id_user)
);