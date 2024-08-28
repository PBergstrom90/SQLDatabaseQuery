use webshop_handin;

-- Show customers that has bought black Sweatpants in size 38
select distinct customer.name as Customer, sum(adding.quantity) as Amount from customer
inner join places on customer.id = places.customerId
inner join booking on places.bookingId = booking.id
inner join adding on booking.id = adding.bookingId
inner join product on adding.productId = product.id
where product.name = 'Sweatpants' and product.color = 'black' and product.size = '38'
group by customer.name;

-- Show amount of products per category


-- Create a customerlist with the total amount of money spent

