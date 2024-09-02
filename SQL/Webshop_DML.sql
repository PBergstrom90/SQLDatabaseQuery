use webshop;

-- Show customers that has bought black Sweatpants in size 38
select distinct customer.name as Customer, sum(adding.quantity) as Amount from customer
inner join places on customer.id = places.customerId
inner join booking on places.bookingId = booking.id
inner join adding on booking.id = adding.bookingId
inner join product on adding.productId = product.id
where product.name = 'Sweatpants' and product.color = 'black' and product.size = '38'
group by customer.name
order by Amount desc;

-- Show amount of products per category
select category.name as Category, count(product.id) as NumberOfProducts
from category
inner join identifies on category.id = identifies.categoryId
inner join product on identifies.productId = product.id
group by category.name
order by NumberOfProducts desc;

-- Create a customerlist with the total amount of money spent
select customer.name as Customer, sum(product.price * adding.quantity) as TotalSpent
from customer
inner join places on customer.id = places.customerId
inner join booking on places.bookingId = booking.id
inner join adding on booking.id = adding.bookingId
inner join product on adding.productId = product.id
group by customer.name
order by TotalSpent desc;

-- Show Top 5 best selling products
select product.name as Product, sum(adding.quantity) as TotalSold
from product
inner join adding on product.id = adding.productId
group by product.name
order by TotalSold desc
limit 5;
