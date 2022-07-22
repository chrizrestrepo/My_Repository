
insert into clients(client_id, identification, first_name, last_name, email) values (0, '123', 'cristian ', 'restrepo', 'crestrepo@gamil.com');
insert into clients(client_id, identification, first_name, last_name, email) values (1, '456', 'Hernan', 'gonzales', 'herzales@gamil.com');
insert into clients(client_id, identification, first_name, last_name, email) values (2, '789', 'veronica', 'giraldo', 'vgiraldo@gamil.com');
insert into clients(client_id, identification, first_name, last_name, email) values (3, '098', 'hamilton', 'restrepo', 'hrestrepo@gamil.com');
insert into clients(client_id, identification, first_name, last_name, email) values (4, '987', 'alex', 'gonzales', 'alezales@gamil.com');
insert into clients(client_id, identification, first_name, last_name, email) values (5, '876', 'danny', 'gonzales', 'danzales@gamil.com');

insert into products(product_id, product_name, price, create_date) values (0, 'panasonic 43"', 1200000.00, NOW());
insert into products(product_id, product_name, price, create_date) values (1, 'Camara Canon 43 mega pixeles', 2000000.00, NOW());
insert into products(product_id, product_name, price, create_date) values (2, 'Monitor Dell 27"', 900000.00, NOW());
insert into products(product_id, product_name, price, create_date) values (3, 'PS5 Horizon package"', 3500000.00, NOW());
insert into products(product_id, product_name, price, create_date) values (4, 'HeadPhones Corsair"', 400000.00, NOW());

insert into bills(bill_id, create_date, create_hour, buy_description, buy_observation, client_fk) values (0, NOW(), NOW(), 'Electronic Bill From Kinetika', 'N/A', 0);
insert into bill_items(item_id, amount, product_fk, bill_fk) values (RANDOM_UUID(), 1, 0, 0);
insert into bill_items(item_id, amount, product_fk, bill_fk) values (RANDOM_UUID(), 1, 3, 0);

insert into bills(bill_id, create_date, create_hour, buy_description, buy_observation, client_fk) values (1, NOW(), NOW(), 'Electronic Bill From Kinetika', 'N/A', 0);
insert into bill_items(item_id, amount, product_fk, bill_fk) values (RANDOM_UUID(), 2, 4, 1);
insert into bill_items(item_id, amount, product_fk, bill_fk) values (RANDOM_UUID(), 2, 2, 1);

insert into bills(bill_id, create_date, create_hour, buy_description, buy_observation, client_fk) values (2, NOW(), NOW(), 'Electronic Bill From Kinetika', 'N/A', 2);
insert into bill_items(item_id, amount, product_fk, bill_fk) values (RANDOM_UUID(), 1, 1, 2);
