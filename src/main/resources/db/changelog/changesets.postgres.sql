--liquibase formatted sql

--changeset aleks.aridov:init
create table t_user
(
    id            uuid         not null
        primary key,
    created_at    timestamp,
    updated_at    timestamp,
    platform_id   varchar(255) not null
        constraint t_user_platform_id_unique unique,
    user_name     varchar(255),
    first_name    varchar(255),
    last_name     varchar(255),
    is_bot        boolean,
    language_code varchar(10)
);

create table chat
(
    id          uuid         not null
        primary key,
    created_at  timestamp,
    updated_at  timestamp,
    platform_id varchar(255) not null
        constraint chat_platform_id_unique unique,
    username    varchar(255),
    first_name  varchar(255),
    last_name   varchar(255),
    title       varchar(255),
    type        varchar(255)
);

create table session
(
    id         uuid not null
        primary key,
    created_at timestamp,
    updated_at timestamp,
    chat_id    uuid
        constraint session_chat_id
            references chat,
    status     varchar(20)
);

create index session_chat_id_index
    on session (chat_id);

create table message
(
    id         uuid not null
        primary key,
    created_at timestamp,
    updated_at timestamp,
    text       text,
    from_id    uuid
        constraint message_from_id
            references t_user,
    session_id uuid
        constraint message_session_id
            references session
);

create index message_session_id_index
    on message (session_id);

create index message_from_id_index
    on message (from_id);

--changeset aleks.aridov:message_role
alter table message
    add role varchar(20) not null;