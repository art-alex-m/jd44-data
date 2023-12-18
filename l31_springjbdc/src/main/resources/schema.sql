create table customers
(
    id           serial primary key,
    name         varchar(20) not null,
    surname      varchar(30) not null,
    age          int         not null
        constraint customers_age_positive check ( age > 0 ),
    phone_number varchar(20)
        constraint customers_phone_onlydigits check ( phone_number ~ '^\d+$' )
);

create table orders
(
    id           serial primary key,
    date         timestamp default current_timestamp,
    customer_id  integer not null,
    product_name varchar(50),
    amount       int4    not null
        constraint orders_amount_positive check ( amount > 0 ),

    constraint orders_to_customers_fk
        foreign key (customer_id)
            references customers (id)
            on delete cascade
            on update cascade
);