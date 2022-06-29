
CREATE SCHEMA IF NOT EXISTS bank0;


set search_path to bank0;

CREATE TABLE accounts(
	id serial PRIMARY KEY,
	balance NUMERIC(50,2) DEFAULT 0,
	acc_owner integer NOT NULL,
	active bool DEFAULT FALSE
);

CREATE TYPE user_role AS ENUM ('Admin', 'Customer', 'Employee');
create type application_status as enum ('Approved', 'Waiting', 'Declined')

CREATE TABLE users(
	id serial PRIMARY KEY,
	username varchar(50) NOT NULL UNIQUE,
	pwd varchar(50) NOT NULL,
	user_role user_role NOT NULL
);

ALTER TABLE accounts ADD CONSTRAINT users_fkey FOREIGN KEY (acc_owner) REFERENCES users(id);

CREATE TABLE user_accounts_jt(
	acc_owner integer NOT NULL REFERENCES users(id),
	account int NOT NULL REFERENCES accounts(id)
);


create table Applications (
	id serial primary key,
	username varchar(50) not null,
	application_status application_status not null

);

INSERT INTO users (username, pwd, user_role)
	VALUES ('Larry', 'pass', 'Employee'),
			('Marry', '1234', 'Customer'),
			('Barry', 'pass', 'Customer');

INSERT INTO accounts (balance, acc_owner)
	VALUES (100, 1),
		    (2000, 2);


		   
SELECT * FROM users;

SELECT * FROM accounts;
	