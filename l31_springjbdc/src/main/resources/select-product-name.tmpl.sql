select product_name
from orders o
         inner join customers s on s.id = o.customer_id
where s.name ilike :user_name;