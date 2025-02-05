create database books_system;

\connect books_system;

create table books (
  id bigint primary key generated always as identity,
  name character varying(255) not null,

  CONSTRAINT books_name_uniq UNIQUE (name)
);

insert into books (name) values ('The little prince'), ('Flow'), ('Atomic Habits');