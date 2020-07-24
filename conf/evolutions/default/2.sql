-- Users schema

-- !Ups
insert into brands (name,origin) values ('BMW','Germany');
insert into brands (name,origin) values ('Toyota','Japan');
insert into brands (name,origin) values ('Honda','Japan');
insert into brands (name,origin) values ('Dodje','USA');

insert into models (name,startYear,endYear) values ('X5', 2001, 2007);
insert into models (name,startYear,endYear) values ('Rav4', 2005, 2008);
insert into models (name,startYear,endYear) values ('Civic', 1998, 2010);
insert into models (name,startYear,endYear) values ('Viper', 1999, 2004);

insert into products (id,brand,model,startYear,mileage,price) values (10, 'Dodje', 'Viper', 2000, 100, 40000);
insert into products (id,brand,model,startYear,mileage,price) values (11, 'Dodje', 'Viper', 2001, 0, 100000);
insert into products (id,brand,model,startYear,mileage,price) values (12, 'Honda', 'Civic', 1999, 130, 8000);
insert into products (id,brand,model,startYear,mileage,price) values (13, 'Toyota', 'Rav4', 2006, 4000, 32000);

-- !Downs

delete from brands;
delete from models;
delete from products;
