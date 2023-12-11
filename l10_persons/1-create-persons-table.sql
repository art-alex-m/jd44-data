create table PERSONS
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

insert into persons
values ('Alex', 'Fox', 21, '79090000001', 'moscow'),
       ('Nick', 'Raven', 30, '79090000002', 'newyork'),
       ('Tom', 'Cat', 35, '79090000003', 'kazan'),
       ('Jerry', 'Mouse', 35, '79090000004', 'tombov'),
       ('Ponny', 'Shakal', 25, '79090000005', 'moscow');
