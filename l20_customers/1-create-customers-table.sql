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

insert into customers (name, surname, age, phone_number)
values ('alexey', 'Petrov', 21, '71234567890'),
       ('Ivan', 'Semenov', 25, '71234567891'),
       ('Alexey', 'Ivanov', 30, '71234567892'),
       ('Petr', 'Sokolov', 30, '71234567893'),
       ('AleXeY', 'Golubev', 25, '71234567894');
