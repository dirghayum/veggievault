CREATE table veggieshop.category(
    id bigint primary key GENERATED ALWAYS AS IDENTITY,
    name varchar(150) unique
);

CREATE TABLE veggieshop.item(
    id bigint primary key GENERATED ALWAYS AS IDENTITY,
    category_id bigint not null,
    name varchar (150) unique,
    CONSTRAINT fk_category FOREIGN KEY(category_id) references veggieshop.category(id)

);