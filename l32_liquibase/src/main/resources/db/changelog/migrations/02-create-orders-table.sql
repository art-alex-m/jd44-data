--liquibase formatted sql

--changeset artalexm:create-orders-table
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

--changeset artalexm:insert-demo-orders
insert into orders (customer_id, product_name, amount)
values (1, 'cheesecake', 100),
       (2, 'sugar', 200),
       (3, 'sesame', 300),
       (5, 'beans', 100),
       (2, 'oat', 300),
       (5, 'wafer', 300);
