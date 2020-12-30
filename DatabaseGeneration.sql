-- Version 2.1.0
-- ***************************************************;

-- ************************************** DROPS

DROP TABLE OrderContent;
DROP TABLE OrderRestaurant;
DROP TABLE DishIngredients;
DROP TABLE Booking;
DROP TABLE User;
DROP TABLE Waiter;
DROP TABLE Ingredient;
DROP TABLE Drink;
DROP TABLE Dish;
DROP TABLE Food;
DROP TABLE StateTimes;
DROP TABLE TableRestaurant;
DROP TABLE Restaurant;


-- ************************************** Waiter

CREATE TABLE Waiter (
    WaiterId int NOT NULL AUTO_INCREMENT ,
    Name varchar(45) NOT NULL ,
    PRIMARY KEY (WaiterId)
);

INSERT INTO Waiter (WaiterId, name) VALUES (1, 'Juan');
INSERT INTO Waiter (WaiterId, name) VALUES (2, 'Cristina');
INSERT INTO Waiter (WaiterId, name) VALUES (3, 'Marta');
INSERT INTO Waiter (WaiterId, name) VALUES (4, 'Marcos');
INSERT INTO Waiter (WaiterId, name) VALUES (5, 'Luis');


-- ************************************** Ingredient

CREATE TABLE Ingredient (
    IngredientId int NOT NULL AUTO_INCREMENT ,
    Name varchar(45) NOT NULL ,
    InStorage float unsigned NOT NULL ,
    PRIMARY KEY (IngredientId) 
);

INSERT INTO Ingredient (IngredientId, Name, InStorage) VALUES (1, 'Salt', 5);
INSERT INTO Ingredient (IngredientId, Name, InStorage) VALUES (2, 'Pepper', 25);
INSERT INTO Ingredient (IngredientId, Name, InStorage) VALUES (3, 'Olive oil', 14);
INSERT INTO Ingredient (IngredientId, Name, InStorage) VALUES (4, 'Vegetable oil', 90);
INSERT INTO Ingredient (IngredientId, Name, InStorage) VALUES (5, 'Sugar', 150);


-- ************************************** Food

CREATE TABLE Food (
	FoodId int NOT NULL AUTO_INCREMENT ,
	Name varchar(45) NOT NULL ,
	Type enum('drinks', 'starters', 'first_course', 'second_course', 'dessert') NOT NULL ,
	Cost float NOT NULL ,
	PRIMARY KEY (FoodId)
);

INSERT INTO Food (FoodId, Name, Type, Cost) VALUES (1, 'Water', 'drinks', 2);
INSERT INTO Food (FoodId, Name, Type, Cost) VALUES (2, 'Soda', 'drinks', 2.30);
INSERT INTO Food (FoodId, Name, Type, Cost) VALUES (3, 'Wine', 'drinks', 6.50);
INSERT INTO Food (FoodId, Name, Type, Cost) VALUES (4, 'Rice', 'first_course', 10.75);
INSERT INTO Food (FoodId, Name, Type, Cost) VALUES (5, 'Waffle', 'dessert', 5.50);


-- ************************************** Drink

CREATE TABLE Drink (
	FoodId int NOT NULL ,
	InStorage float unsigned NOT NULL ,
	PRIMARY KEY (FoodId),
    FOREIGN KEY (FoodId) REFERENCES Food(FoodId)
);

INSERT INTO Drink (FoodId, InStorage) VALUES (1, 200);
INSERT INTO Drink (FoodId, InStorage) VALUES (2, 85);
INSERT INTO Drink (FoodId, InStorage) VALUES (3, 63);




-- ************************************** Dish

CREATE TABLE Dish (
	FoodId int NOT NULL ,
	PRIMARY KEY (FoodId),
	FOREIGN KEY (FoodId) REFERENCES Food(FoodId)
);

INSERT INTO Dish (FoodId) VALUES (4);
INSERT INTO Dish (FoodId) VALUES (5);


-- ************************************** Restaurant

CREATE TABLE Restaurant (
	RestaurantId int NOT NULL AUTO_INCREMENT ,
	City varchar(45) NOT NULL ,
	PRIMARY KEY (RestaurantId)
);

INSERT INTO Restaurant (RestaurantId, City) VALUES (1, 'Madrid');
INSERT INTO Restaurant (RestaurantId, City) VALUES (2, 'London');
INSERT INTO Restaurant (RestaurantId, City) VALUES (3, 'Paris');
INSERT INTO Restaurant (RestaurantId, City) VALUES (4, 'New York');
INSERT INTO Restaurant (RestaurantId, City) VALUES (5, 'Barcelona');


-- ************************************** TableRestaurant

CREATE TABLE TableRestaurant (
	TableId int NOT NULL AUTO_INCREMENT ,
	RestaurantId int NOT NULL ,
	Seats int unsigned NOT NULL ,
	PRIMARY KEY (TableId ),
    FOREIGN KEY (RestaurantId) REFERENCES  Restaurant(RestaurantId),
	CONSTRAINT Seats CHECK ( Seats = 2 OR Seats = 4 OR Seats = 6 )
);

INSERT INTO TableRestaurant (TableId, RestaurantId, Seats) VALUES (1, 1, 4);
INSERT INTO TableRestaurant (TableId, RestaurantId, Seats) VALUES (2, 1, 6);
INSERT INTO TableRestaurant (TableId, RestaurantId, Seats) VALUES (3, 1, 2);
INSERT INTO TableRestaurant (TableId, RestaurantId, Seats) VALUES (4, 2, 2);
INSERT INTO TableRestaurant (TableId, RestaurantId, Seats) VALUES (5, 2, 4);


-- ************************************** StateTimes

CREATE TABLE StateTimes (
	StartTime datetime NOT NULL ,
	TableId int NOT NULL ,
	State enum('free', 'reserved', 'busy', 'asking', 'waiting_for_food', 'served', 'waiting_for_bill', 'paying', 'in_preparation') NOT NULL ,
	PRIMARY KEY (StartTime, TableId),
    FOREIGN KEY (TableId) REFERENCES  TableRestaurant(TableId)
);

INSERT INTO StateTimes (StartTime, TableId, State) VALUES ('2020-12-14 13:00:01', 1, 'reserved');
INSERT INTO StateTimes (StartTime, TableId, State) VALUES ('2020-12-15 11:32:01', 2, 'busy');
INSERT INTO StateTimes (StartTime, TableId, State) VALUES ('2020-12-16 21:45:14', 3, 'waiting_for_food');
INSERT INTO StateTimes (StartTime, TableId, State) VALUES ('2020-12-11 22:00:44', 4, 'served');
INSERT INTO StateTimes (StartTime, TableId, State) VALUES ('2020-12-20 22:33:29', 5, 'free');


-- ************************************** User

CREATE TABLE User (
	UserId int NOT NULL AUTO_INCREMENT ,
	Username varchar(45) NOT NULL ,
	Password varchar(45) NOT NULL ,
	Type enum('room_head', 'waiter', 'cook', 'bartender', 'owner') NOT NULL ,
	WaiterId int NULL ,
	PRIMARY KEY (UserId),
	FOREIGN KEY (WaiterId) REFERENCES  Waiter(WaiterId)
);

INSERT INTO User (UserId, Username, Password, Type, WaiterId) VALUES (1, 'User1', '123', 'owner', 2);
INSERT INTO User (UserId, Username, Password, Type, WaiterId) VALUES (2, 'User2', '321', 'bartender', 4);
INSERT INTO User (UserId, Username, Password, Type, WaiterId) VALUES (3, 'User3', '456', 'cook', 5);
INSERT INTO User (UserId, Username, Password, Type, WaiterId) VALUES (4, 'User4', '789', 'waiter', 2);
INSERT INTO User (UserId, Username, Password, Type, WaiterId) VALUES (5, 'User5', '258', 'waiter', 1);


-- ************************************** Booking

CREATE TABLE Booking (
	TableId int NOT NULL ,
	Date date NOT NULL ,
	Turn enum('l1', 'l2', 'l3', 'd1', 'd2', 'd3') NOT NULL ,
	Client varchar(45) NOT NULL ,	
    PRIMARY KEY (TableId, Date, Turn),
	FOREIGN KEY (TableId) REFERENCES  TableRestaurant(TableId)
);

INSERT INTO Booking (TableId, Date, Turn, Client) VALUES (1, '2020-11-29', 'l2', 'David');
INSERT INTO Booking (TableId, Date, Turn, Client) VALUES (2, '2020-10-25', 'l1', 'Sandra');
INSERT INTO Booking (TableId, Date, Turn, Client) VALUES (3, '2020-11-24', 'l3', 'Ana');
INSERT INTO Booking (TableId, Date, Turn, Client) VALUES (4, '2020-12-10', 'l3', 'Prado');
INSERT INTO Booking (TableId, Date, Turn, Client) VALUES (5, '2020-11-27', 'd2', 'Pedro');


-- ************************************** DishIngredients

CREATE TABLE DishIngredients (
	DishId int NOT NULL ,
	IngredientId int NOT NULL ,
	Quantity float unsigned NOT NULL ,
	PRIMARY KEY (DishId, IngredientId),
    FOREIGN KEY (IngredientId) REFERENCES Ingredient(IngredientId),
    FOREIGN KEY (DishId) REFERENCES Food(FoodId)
);

INSERT INTO DishIngredients (DishId, IngredientId, Quantity) VALUES (5, 5, 25);
INSERT INTO DishIngredients (DishId, IngredientId, Quantity) VALUES (4, 4, 50);


-- ************************************** OrderRestaurant

CREATE TABLE OrderRestaurant (
	OrderId	int NOT NULL AUTO_INCREMENT ,
	WaiterId int NOT NULL ,
	TableId int NOT NULL ,
	State enum('open', 'closed', 'payed') NOT NULL ,
	Datetime datetime NOT NULL ,
    PaymentMethod varchar(45) NULL ,
	PRIMARY KEY (OrderId),
    FOREIGN KEY (WaiterId) REFERENCES Waiter(WaiterId),
    FOREIGN KEY (TableId) REFERENCES TableRestaurant(TableId)
);


INSERT INTO OrderRestaurant (OrderId, WaiterId, TableId, State, Datetime, PaymentMethod) VALUES (1, 2, 1, 'open','2020-12-14 13:15:11', 'cash');
INSERT INTO OrderRestaurant (OrderId, WaiterId, TableId, State, Datetime, PaymentMethod) VALUES (2, 2, 2, 'closed','2020-12-16 21:45:14', 'credit card');
INSERT INTO OrderRestaurant (OrderId, WaiterId, TableId, State, Datetime, PaymentMethod) VALUES (3, 1, 3, 'closed','2020-12-11 22:15:52', 'credit card');
INSERT INTO OrderRestaurant (OrderId, WaiterId, TableId, State, Datetime, PaymentMethod) VALUES (4, 4, 4, 'closed','2020-12-14 22:36:17', 'credit car');
INSERT INTO OrderRestaurant (OrderId, WaiterId, TableId, State, Datetime, PaymentMethod) VALUES (5, 4, 5, 'closed','2020-12-20 22:36:55', 'cash');

-- ************************************** OrderContent

CREATE TABLE OrderContent (
	OrderId int NOT NULL ,
	FoodId int NOT NULL ,
	Quantity int unsigned NOT NULL ,
	TimeReady datetime NULL ,
	TimeDelivered datetime NULL ,
	PRIMARY KEY (OrderId, FoodId),
	FOREIGN KEY (OrderId) REFERENCES OrderRestaurant(OrderId),
    FOREIGN KEY (FoodId) REFERENCES Food(FoodId)
);

INSERT INTO OrderContent (OrderId, FoodId, Quantity, TimeReady, TimeDelivered) VALUES (1, 2, 3, '2020-12-14 13:35:22','2020-12-14 13:40:25');
INSERT INTO OrderContent (OrderId, FoodId, Quantity, TimeReady, TimeDelivered) VALUES (2, 1, 4, '2020-12-16 21:55:03','2020-12-14 14:02:07');
INSERT INTO OrderContent (OrderId, FoodId, Quantity, TimeReady, TimeDelivered) VALUES (3, 1, 2, '2020-12-11 22:21:42','2020-12-11 22:33:26');
INSERT INTO OrderContent (OrderId, FoodId, Quantity, TimeReady, TimeDelivered) VALUES (4, 5, 2, '2020-12-14 22:42:45','2020-12-14 22:55:01');
INSERT INTO OrderContent (OrderId, FoodId, Quantity, TimeReady, TimeDelivered) VALUES (5, 4, 1, '2020-12-20 22:50:16','2020-12-20 23:06:04');
