CREATE TABLE dish (
	dishID int NOT NULL AUTO_INCREMENT,
	dish_name varchar(30) NOT NULL,
	dish_price float,
	PRIMARY KEY (dishID)
);

CREATE TABLE Orders(
	orderID int NOT NULL AUTO_INCREMENT,
	dishID int NOT NULL,
	cust_email VARCHAR(30) NOT NULL,
	qty int NOT NULL,
	PRIMARY KEY (orderID),
	FOREIGN KEY (dishID) REFERENCES dish(dishID)
);