drop table "ALB323"."PROP_AMENITIES" cascade constraints PURGE;
drop table "ALB323"."APP_AMENITIES" cascade constraints PURGE;
drop table "ALB323"."PROPERTIES" cascade constraints PURGE;
drop table "ALB323"."APARTMENTS" cascade constraints PURGE;
drop table "ALB323"."RENT_PAYMENT" cascade constraints PURGE;
drop table "ALB323"."RENTS" cascade constraints PURGE;
drop table "ALB323"."LEASE" cascade constraints PURGE;
drop table "ALB323"."TENANT" cascade constraints PURGE;
drop table "ALB323"."VISITED" cascade constraints PURGE;
drop table "ALB323"."PERSPECTIVE" cascade constraints PURGE;
drop table "ALB323"."AMM_PAYMENT" cascade constraints PURGE;



CREATE TABLE Properties (
  address varchar(25),
  buidling_num int,
  buildin_name varchar(255),
  PRIMARY KEY (address)
);

CREATE TABLE Apartments (
  app_num int,
  address varchar(25),
  sq_foot int,
  bedroom int,
  bathroom int,
  rent int,
  pet int CHECK(pet in (0,1)),
  PRIMARY KEY (app_num, address)
);

CREATE TABLE Lease (
  lease_id int generated always as identity,
  app_num int NOT NULL,
  address varchar(25) NOT NULL,
  term_length int,
  rent int,
  deposit int,
  give_back int CHECK(give_back in (0,1)),
  PRIMARY KEY (lease_id)
);

CREATE TABLE Rents (
  lease_id int,
  tenant_id int,
  PRIMARY KEY (lease_id, tenant_id)
);

CREATE TABLE Tenant (
  tenant_id int generated always as identity,
  first_name varchar(25) NOT NULL,
  last_name varchar(25) NOT NULL,
  age int,
  social int,
  PRIMARY KEY (tenant_id)
);

CREATE TABLE Rent_Payment (
  lease_id int,
  tenant_id int,
  date_ varchar(255),
  method varchar(255) CHECK(method in ('cash', 'check', 'credit', 'due')),
  amount int NOT NULL,
  PRIMARY KEY (lease_id, tenant_id, date_, amount)
);

CREATE TABLE Perspective (
  persp_id int generated always as identity,
  first_name varchar(25) NOT NULL,
  last_name varchar(25) NOT NULL,
  age int,
  PRIMARY KEY (persp_id)
);

CREATE TABLE Visited (
  app_num int,
  address varchar(25),
  persp_id int,
  PRIMARY KEY (app_num, address, persp_id)
);

CREATE TABLE Prop_Amenities (
  id int generated always as identity,
  address varchar(25),
  name varchar(25),
  price int,
  PRIMARY KEY (id)
);

CREATE TABLE App_Amenities (
  app_num int,
  address varchar(25),
  name varchar(25),
  PRIMARY KEY (app_num, address)
);

CREATE TABLE Amm_Payment (
  am_id int,
  tenant_id int,
  address varchar(25),
  date_ varchar(255),
  method varchar(255) CHECK(method in ('cash', 'check', 'credit', 'due')),
  amount int NOT NULL,
  PRIMARY KEY (am_id, tenant_id, date_)
);

ALTER TABLE Apartments ADD FOREIGN KEY (address) REFERENCES Properties (address);

ALTER TABLE Lease ADD FOREIGN KEY (app_num, address) REFERENCES Apartments (app_num, address);

ALTER TABLE Rents ADD FOREIGN KEY (lease_id) REFERENCES Lease (lease_id);

ALTER TABLE Rents ADD FOREIGN KEY (tenant_id) REFERENCES Tenant (tenant_id);

ALTER TABLE Rent_Payment ADD FOREIGN KEY (lease_id) REFERENCES Lease (lease_id);

ALTER TABLE Rent_Payment ADD FOREIGN KEY (tenant_id) REFERENCES Tenant (tenant_id);

ALTER TABLE Visited ADD FOREIGN KEY (app_num, address) REFERENCES Apartments (app_num, address);

ALTER TABLE Visited ADD FOREIGN KEY (persp_id) REFERENCES Perspective (persp_id);

ALTER TABLE Prop_Amenities ADD FOREIGN KEY (address) REFERENCES Properties (address);

ALTER TABLE App_Amenities ADD FOREIGN KEY (app_num, address) REFERENCES Apartments (app_num, address);

ALTER TABLE Amm_Payment ADD FOREIGN KEY (am_id) REFERENCES Prop_Amenities (id);

ALTER TABLE Amm_Payment ADD FOREIGN KEY (tenant_id) REFERENCES Tenant (tenant_id);

INSERT INTO RENT_PAYMENT VALUES (21, 3, '20220101', 'due', 91267);