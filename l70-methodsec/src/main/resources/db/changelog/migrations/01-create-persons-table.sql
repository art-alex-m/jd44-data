-- liquibase formatted sql

-- changeset artalexm:create-persons-table
create table persons
(
    name           varchar(20) not null,
    surname        varchar(30) not null,
    age            int         not null
        constraint persons_age_positive check ( age > 0 ),
    phone_number   varchar(15)
        constraint persons_phone_onlydigits check ( phone_number ~ '^\d+$' ),
    city_of_living varchar(100),

    constraint persons_pk primary key (name, surname, age)
);
