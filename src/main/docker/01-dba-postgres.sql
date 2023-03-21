\set AUTOCOMMIT true

create user dirghayu password 'Welcome1';
create user dmainali password 'Welcome1';
-- create database harvestdb owner = dirghayu;
\connect harvestdb
create schema veggieshop;

grant usage on schema veggieshop to dmainali;
grant all privileges on schema veggieshop to dmainali;
grant select, insert, update, delete on all tables in schema veggieshop to dmainali;
grant execute on all functions in schema veggieshop to dmainali;
alter default privileges in schema veggieshop grant select, insert, update, delete on tables to dmainali;
grant usage, select on all sequences in schema veggieshop to dmainali;