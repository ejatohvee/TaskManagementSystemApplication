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