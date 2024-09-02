drop database if exists webshop;
create database webshop;
use webshop;

-- Setup user for the webshop
drop user if exists 'webshopuser'@'localhost';
CREATE USER 'webshopuser'@'localhost' IDENTIFIED BY 'secretpassword07!';
grant all privileges on *.* TO 'webshopuser'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- DATA TABLES

create table customer
(id int not null auto_increment primary key,
name varchar(30) not null,
address varchar(30) not null,
created timestamp default CURRENT_TIMESTAMP,
lastUpdate timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP);

-- An additional index for customer names.
create index customerIndex on Customer(name);

create table product
(id int not null auto_increment primary key,
name varchar(30) not null,
size varchar(30) not null,
color varchar(30) not null,
price int not null,
created timestamp default CURRENT_TIMESTAMP,
lastUpdate timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP);

-- An additional index for product prices.
create index priceIndex on Product(price);

create table booking
(id int not null auto_increment primary key,
created timestamp default CURRENT_TIMESTAMP,
lastUpdate timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP);

create table category
(id int not null auto_increment primary key,
name varchar(30) not null,
created timestamp default CURRENT_TIMESTAMP,
lastUpdate timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP);

-- MAPPING TABLES

-- Table to link customers and bookings
create table places
(id int not null auto_increment primary key,
customerId int not null,
bookingId int not null,
foreign key (customerId) references customer(id) on delete cascade,
foreign key (bookingId) references booking(id) on delete cascade);

-- Table to link bookings and products with quantities
create table adding
(id int not null auto_increment primary key,
bookingId int not null,
productId int not null,
quantity int not null default 1, 
foreign key (bookingId) references booking(id) on delete cascade,
foreign key (productId) references product(id) on delete cascade);

-- Table to link products and categories
create table identifies
(id int not null auto_increment primary key,
productId int not null,
categoryId int not null,
foreign key (productId) references product(id) on delete cascade,
foreign key (categoryId) references category(id) on delete cascade);

INSERT INTO customer (name, address) VALUES
('Lasse Eriksson', 'Stockholm'),
('Fredrik Larsson', 'Eskilstuna'),
('Frida Fransson', 'Sundsvall'),
('Hans Boström', 'Umeå'),
('Greger Abrahamsson', 'Ystad'),
('Amanda Björkkvist', 'Malmö');

INSERT INTO booking (created, lastUpdate) VALUES
(NOW(), NOW()), -- Booking 1 (Current time)
(NOW(), NOW()), -- Booking 2 (Current time)
(NOW(), NOW()), -- Booking 3 (Current time)
(NOW(), NOW()), -- Booking 4 (Current time)
(NOW(), NOW()), -- Booking 5 (Current time)
(NOW(), NOW()), -- Booking 6 (Current time)
(NOW(), NOW()), -- Booking 7 (Current time)
(NOW(), NOW()); -- Booking 8 (Current time)

INSERT INTO product (name, size, color, price) VALUES
('T-Shirt','M','Red',200),
('Jeans','L','Black',400),
('Sports Jacket','S','Black',400),
('Dress','M','White',500),
('Socks','S','Black',150),
('Sweatshirt','M','Green',300),
('Sweetpants','38','Black',250),
('Tanktop','M','White',100),
('Shorts','L','Orange',250);

INSERT INTO category (name) VALUES
('Shirts'),
('Pants'),
('Sportswear'),
('Dresses'),
('Underwear'),
('Casual');

INSERT INTO identifies (productId, categoryId) VALUES
('1','1'), -- T-Shirt categorised as 'Shirts'
('1','6'), -- T-Shirt categorised as 'Casual'
('2','2'), -- Jeans categorised as 'Pants'
('3','3'), -- Sports Jacket categorised as 'Sportswear'
('3','6'), -- Sports Jacket categorised as 'Casual'
('4','4'), -- Dress categorised as 'Dresses'
('5','5'), -- Socks categorised as 'Underwear'
('6','1'), -- Sweatshirt categorised as 'Shirts'
('6','6'), -- Sweatshirt categorised as 'Casual'
('7','2'), -- Sweatpants categorised as 'Pants'
('7','6'), -- Sweatpants categorised as 'Casual'
('8','5'), -- Tanktop categorised as 'Underwear'
('8','6'), -- Tanktop categorised as 'Casual'
('9','2'), -- Shorts categorised as 'Pants'
('9','3'), -- Shorts categorised as 'Sports Wear'
('9','6'); -- Shorts categorised as 'Casual'

INSERT INTO places (customerId, bookingId) VALUES
(1,1), -- Customer 1 made Booking 1
(1,2), -- Customer 1 made Booking 2
(2,3), -- Customer 2 made Booking 3
(3,4), -- Customer 3 made Booking 4
(4,5), -- Customer 4 made Booking 5
(5,6), -- Customer 5 made Booking 6
(6,7), -- Customer 6 made Booking 7
(6,8); -- Customer 6 made Booking 8

INSERT INTO adding (bookingId, productId, quantity) VALUES
(1,1,2), -- Booking 1 includes 2 T-shirts
(1,3,1), -- Booking 1 includes 1 Sports Jacket
(2,5,1), -- Booking 2 includes 1 pair of Socks
(3,7,2), -- Booking 3 includes 2 pars of Sweatpants
(4,2,1), -- Booking 4 includes 1 pair of Jeans
(4,4,1), -- Booking 4 includes 1 Dress
(5,9,1), -- Booking 5 includes 1 pair of Shorts
(6,8,1), -- Booking 6 includes 1 Tanktop
(7,6,2), -- Booking 7 includes 2 Sweatshirts
(8,5,3), -- Booking 8 includes 3 pairs of Socks
(8,7,1); -- Booking 8 includes 1 pair of Sweatpants