-- liquibase formatted sql

-- changeset artalexm:insert-demo-persons
insert into persons
values ('Alex', 'Fox', 21, '79090000001', 'moscow'),
       ('Nick', 'Raven', 30, '79090000002', 'newyork'),
       ('Tom', 'Cat', 35, '79090000003', 'kazan'),
       ('Jerry', 'Mouse', 35, '79090000004', 'tombov'),
       ('Ponny', 'Shakal', 25, '79090000005', 'moscow');