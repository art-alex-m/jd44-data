insert into customers (id, name, surname, age, phone_number)
values (1, 'alexey', 'Petrov', 21, '71234567890'),
       (2, 'Ivan', 'Semenov', 25, '71234567891'),
       (3, 'Alexey', 'Ivanov', 30, '71234567892'),
       (4, 'Petr', 'Sokolov', 30, '71234567893'),
       (5, 'AleXeY', 'Golubev', 25, '71234567894')
on conflict do nothing;

insert into orders (id, customer_id, product_name, amount)
values (1, 1, 'cheesecake', 100),
       (2, 2, 'sugar', 200),
       (3, 3, 'sesame', 300),
       (4, 5, 'beans', 100),
       (5, 2, 'oat', 300),
       (6, 5, 'wafer', 300)
on conflict do nothing;