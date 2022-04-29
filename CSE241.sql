drop table "ALB323"."PROP_AMMENITIES" cascade constraints PURGE;
drop table "ALB323"."APP_AMMENITIES" cascade constraints PURGE;
drop table "ALB323"."PROPERTIES" cascade constraints PURGE;
drop table "ALB323"."APPARTMENTS" cascade constraints PURGE;
drop table "ALB323"."RENT_PAYMENT" cascade constraints PURGE;
drop table "ALB323"."RENTS" cascade constraints PURGE;
drop table "ALB323"."LEASE" cascade constraints PURGE;
drop table "ALB323"."TENENT" cascade constraints PURGE;
drop table "ALB323"."VISITED" cascade constraints PURGE;
drop table "ALB323"."PERSPECTIVE" cascade constraints PURGE;
drop table "ALB323"."AMM_PAYMENT" cascade constraints PURGE;



CREATE TABLE Properties (
  address varchar(255),
  buidling_num int,
  buildin_name varchar(255),
  PRIMARY KEY (address)
);

CREATE TABLE Appartments (
  app_num int,
  address varchar(255),
  sq_foot int,
  bedroom int,
  bathroom int,
  rent int,
  pet int,
  PRIMARY KEY (app_num, address)
);

CREATE TABLE Lease (
  lease_id int,
  app_num int,
  address varchar(255),
  term_length int,
  rent int,
  deposit int,
  give_back int,
  PRIMARY KEY (lease_id)
);

CREATE TABLE Rents (
  lease_id int,
  tenent_id int,
  PRIMARY KEY (lease_id, tenent_id)
);

CREATE TABLE Tenent (
  tenent_id int,
  name varchar(255),
  age int,
  social int,
  PRIMARY KEY (tenent_id)
);

CREATE TABLE Rent_Payment (
  lease_id int,
  tenent_id int,
  date_ varchar(255),
  method varchar(255),
  amount int,
  PRIMARY KEY (lease_id, tenent_id, date_)
);

CREATE TABLE Perspective (
  persp_id int,
  name varchar(255),
  age int,
  PRIMARY KEY (persp_id)
);

CREATE TABLE Visited (
  app_num int,
  address varchar(255),
  persp_id int,
  PRIMARY KEY (app_num, address, persp_id)
);

CREATE TABLE Prop_Ammenities (
  address varchar(255) PRIMARY KEY,
  name varchar(255),
  price int
);

CREATE TABLE App_Ammenities (
  app_num int,
  address varchar(255),
  name varchar(255),
  price int,
  PRIMARY KEY (app_num, address)
);

CREATE TABLE Amm_Payment (
  app_num int,
  address varchar(255),
  tenent_id int,
  date_ varchar(255),
  method varchar(255),
  amount int,
  PRIMARY KEY (app_num, address, tenent_id, date_)
);

ALTER TABLE Appartments ADD FOREIGN KEY (address) REFERENCES Properties (address);

ALTER TABLE Lease ADD FOREIGN KEY (app_num, address) REFERENCES Appartments (app_num, address);

ALTER TABLE Rents ADD FOREIGN KEY (lease_id) REFERENCES Lease (lease_id);

ALTER TABLE Rents ADD FOREIGN KEY (tenent_id) REFERENCES Tenent (tenent_id);

ALTER TABLE Rent_Payment ADD FOREIGN KEY (lease_id) REFERENCES Lease (lease_id);

ALTER TABLE Rent_Payment ADD FOREIGN KEY (tenent_id) REFERENCES Tenent (tenent_id);

ALTER TABLE Visited ADD FOREIGN KEY (app_num, address) REFERENCES Appartments (app_num, address);


ALTER TABLE Visited ADD FOREIGN KEY (persp_id) REFERENCES Perspective (persp_id);

ALTER TABLE Prop_Ammenities ADD FOREIGN KEY (address) REFERENCES Properties (address);

ALTER TABLE App_Ammenities ADD FOREIGN KEY (app_num, address) REFERENCES Appartments (app_num, address);

ALTER TABLE Amm_Payment ADD FOREIGN KEY (app_num, address) REFERENCES App_Ammenities (app_num, address);

ALTER TABLE Amm_Payment ADD FOREIGN KEY (address) REFERENCES Prop_Ammenities (address);

ALTER TABLE Amm_Payment ADD FOREIGN KEY (tenent_id) REFERENCES Tenent (tenent_id);



-- Add example data to Properties table
INSERT INTO Properties (address, buidling_num, buildin_name) VALUES ('123 Main Street', 123, 'Main Street Apartments');
INSERT INTO Properties (address, buidling_num, buildin_name) VALUES ('456 Pine Street', 456, 'Pine Street Apartments');
INSERT INTO Properties (address, buidling_num, buildin_name) VALUES ('789 Maple Street', 789, 'Maple Street Apartments');

-- Add example data to Appartments table
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (1, '123 Main Street', 800, 2, 2, 2000, 0);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (2, '123 Main Street', 1000, 3, 3, 3000, 1);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (3, '123 Main Street', 1200, 4, 4, 4000, 0);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (4, '123 Main Street', 1600, 5, 5, 5000, 1);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (5, '123 Main Street', 2000, 6, 6, 6000, 0);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (6, '456 Pine Street', 800, 2, 2, 2000, 0);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (7, '456 Pine Street', 1000, 3, 3, 3000, 1);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (8, '456 Pine Street', 1200, 4, 4, 4000, 0);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (9, '456 Pine Street', 1600, 5, 5, 5000, 1);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (10, '456 Pine Street', 2000, 6, 6, 6000, 0);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (11, '789 Maple Street', 800, 2, 2, 2000, 0);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (12, '789 Maple Street', 1000, 3, 3, 3000, 1);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (13, '789 Maple Street', 1200, 4, 4, 4000, 0);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (14, '789 Maple Street', 1600, 5, 5, 5000, 1);
INSERT INTO Appartments (app_num, address, sq_foot, bedroom, bathroom, rent, pet) VALUES (15, '789 Maple Street', 2000, 6, 6, 6000, 0);

-- Add example data to Tenent table
INSERT INTO Tenent (tenent_id, name, age, social) VALUES (1, 'John Doe', 20, 189145514);
INSERT INTO Tenent (tenent_id, name, age, social) VALUES (2, 'Jane Doe', 21, 189145515);
INSERT INTO Tenent (tenent_id, name, age, social) VALUES (3, 'Jack Doe', 22, 189145516);
INSERT INTO Tenent (tenent_id, name, age, social) VALUES (4, 'Jill Doe', 23, 189145517);
INSERT INTO Tenent (tenent_id, name, age, social) VALUES (5, 'Joe Doe', 24, 189145518);
INSERT INTO Tenent (tenent_id, name, age, social) VALUES (6, 'Jenny Doe', 25, 189145519);



-- Add example data to Lease tabls
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (1, 1, '123 Main Street', 12, 2000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (2, 2, '123 Main Street', 12, 3000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (3, 3, '123 Main Street', 12, 4000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (4, 4, '123 Main Street', 12, 5000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (5, 5, '123 Main Street', 12, 6000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (6, 6, '456 Pine Street', 12, 2000, 500, 0);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (7, 7, '456 Pine Street', 12, 3000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (8, 8, '456 Pine Street', 12, 4000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (9, 9, '456 Pine Street', 12, 5000, 500, 0);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (10, 10, '456 Pine Street', 12, 6000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (11, 11, '789 Maple Street', 12, 2000, 500, 0);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (12, 12, '789 Maple Street', 12, 3000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (13, 13, '789 Maple Street', 12, 4000, 500, 1);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (14, 14, '789 Maple Street', 12, 5000, 500, 0);
INSERT INTO Lease (lease_id, app_num, address, term_length, rent, deposit, give_back) VALUES (15, 15, '789 Maple Street', 12, 6000, 500, 1);

-- Add example data to Rents table
INSERT INTO Rents (lease_id, tenent_id) VALUES (1, 1);
INSERT INTO Rents (lease_id, tenent_id) VALUES (2, 1);
INSERT INTO Rents (lease_id, tenent_id) VALUES (3, 3);
INSERT INTO Rents (lease_id, tenent_id) VALUES (4, 4);
INSERT INTO Rents (lease_id, tenent_id) VALUES (5, 5);
INSERT INTO Rents (lease_id, tenent_id) VALUES (6, 6);
INSERT INTO Rents (lease_id, tenent_id) VALUES (7, 7);
INSERT INTO Rents (lease_id, tenent_id) VALUES (8, 8);
INSERT INTO Rents (lease_id, tenent_id) VALUES (9, 9);
INSERT INTO Rents (lease_id, tenent_id) VALUES (10, 10);


-- Add example data to Rent_Payment table
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (1, 1, '2019-01-01', 'Check', 2000);
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (2, 1, '2019-02-01', 'Check', 3000);
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (3, 3, '2019-03-01', 'Credit Card', 4000);
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (4, 4, '2019-04-01', 'Credit Card', 5000);
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (5, 5, '2019-05-01', 'Credit Card', 6000);
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (6, 6, '2019-06-01', 'Check', 2000);
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (7, 7, '2019-07-01', 'Check', 3000);
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (8, 8, '2019-08-01', 'Credit Card', 4000);
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (9, 9, '2019-09-01', 'Credit Card', 5000);
INSERT INTO Rent_Payment (lease_id, tenent_id, date_, method, amount) VALUES (10, 10, '2019-10-01', 'Credit Card', 6000);

-- Add example data to Prop_Ammenities table
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('123 Main Street', 'Pool', 0);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('123 Main Street', 'Gym', 0);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('123 Main Street', 'Laundry', 0);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('123 Main Street', 'Parking', 50);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('456 Pine Street', 'Pool', 0);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('456 Pine Street', 'Gym', 0);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('456 Pine Street', 'Laundry', 0);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('456 Pine Street', 'Parking', 70);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('789 Maple Street', 'Pool', 0);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('789 Maple Street', 'Gym', 0);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('789 Maple Street', 'Laundry', 0);
INSERT INTO Prop_Ammenities (address, name, price) VALUES ('789 Maple Street', 'Parking', 90);

-- Add exmple data to App_Ammenities table
INSERT INTO App_Ammenities (app_num, address, name, price) VALUES (1, '123 Main Street', 'Hot Tub', 40);
INSERT INTO App_Ammenities (app_num, address, name, price) VALUES (2, '123 Main Street', 'Petting Zoo', 50);
INSERT INTO App_Ammenities (app_num, address, name, price) VALUES (4, '123 Main Street', 'Heated Floor', 40);
INSERT INTO App_Ammenities (app_num, address, name, price) VALUES (4, '123 Main Street', 'Hot Tub', 50);
INSERT INTO App_Ammenities (app_num, address, name, price) VALUES (4, '123 Main Street', 'Elevator', 100);
INSERT INTO App_Ammenities (app_num, address, name, price) VALUES (5, '123 Main Street', 'Your Mom', 500);
INSERT INTO App_Ammenities (app_num, address, name, price) VALUES (6, '456 Pine Street', 'Massage Table', 30);
INSERT INTO App_Ammenities (app_num, address, name, price) VALUES (7, '456 Pine Street', 'Fake Sun', 60);
INSERT INTO App_Ammenities (app_num, address, name, price) VALUES (8, '456 Pine Street', 'Deez Nutz', 660);


-- Add example data to Amm_Payment table
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (1, '123 Main Street', 1, '2019-01-01', 'Check', 40);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (2, '123 Main Street', 1, '2019-02-01', 'Check', 50);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (3, '123 Main Street', 3, '2019-03-01', 'Credit Card', 60);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (4, '123 Main Street', 4, '2019-04-01', 'Credit Card', 70);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (5, '123 Main Street', 5, '2019-05-01', 'Credit Card', 80);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (6, '123 Main Street', 6, '2019-06-01', 'Check', 40);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (7, '123 Main Street', 7, '2019-07-01', 'Check', 50);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (8, '123 Main Street', 8, '2019-08-01', 'Credit Card', 60);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (9, '123 Main Street', 9, '2019-09-01', 'Credit Card', 70);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (10, '123 Main Street', 10, '2019-10-01', 'Credit Card', 80);
INSERT INTO Amm_Payment (app_num, address, tenent_id, date_, method, amount) VALUES (11, '456 Pine Street', 1, '2019-01-01', 'Check', 40);

-- Add example data to Perspective table 
INSERT INTO Perspective (persp_id, name, age) VALUES (1, 'John Doe', 20);
INSERT INTO Perspective (persp_id, name, age) VALUES (2, 'Jane Doe', 21);
INSERT INTO Perspective (persp_id, name, age) VALUES (3, 'Jack Doe', 22);
INSERT INTO Perspective (persp_id, name, age) VALUES (4, 'Jill Doe', 23);
INSERT INTO Perspective (persp_id, name, age) VALUES (5, 'Joe Doe', 24);
INSERT INTO Perspective (persp_id, name, age) VALUES (6, 'Jenny Doe', 25);

-- Add example data to Visited table
INSERT INTO Visited (app_num, address, persp_id) VALUES (1, '123 Main Street', 1);
INSERT INTO Visited (app_num, address, persp_id) VALUES (2, '123 Main Street', 2);
INSERT INTO Visited (app_num, address, persp_id) VALUES (3, '123 Main Street', 3);
INSERT INTO Visited (app_num, address, persp_id) VALUES (4, '123 Main Street', 4);
INSERT INTO Visited (app_num, address, persp_id) VALUES (5, '123 Main Street', 5);
INSERT INTO Visited (app_num, address, persp_id) VALUES (6, '123 Main Street', 6);
INSERT INTO Visited (app_num, address, persp_id) VALUES (7, '123 Main Street', 7);
INSERT INTO Visited (app_num, address, persp_id) VALUES (11, '456 Pine Street', 1);