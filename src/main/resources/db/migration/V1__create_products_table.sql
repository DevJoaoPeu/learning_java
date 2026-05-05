CREATE TABLE products (
    id bigserial primary key,
    name varchar not null,
    price double precision not null,
    quantity int not null
)