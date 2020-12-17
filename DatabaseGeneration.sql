-- Version 1.0.0
-- ***************************************************;

DROP TABLE `Booking`;



-- ************************************** `Booking`

CREATE TABLE `Booking`
(
 `TableId` int NOT NULL ,
 `Date`    date NOT NULL ,
 `Turn`    enum('l1', 'l2', 'l3', 'd1', 'd2', 'd3') NOT NULL ,
 `Client`  varchar(45) NOT NULL ,

PRIMARY KEY (`TableId`, `Date`, `Turn`),
KEY `fkIdx_49` (`TableId`),
CONSTRAINT `FK_49` FOREIGN KEY `fkIdx_49` (`TableId`) REFERENCES `Table` (`TableId`)
);

DROP TABLE `Dish`;



-- ************************************** `Dish`

CREATE TABLE `Dish`
(
 `FoodId` int NOT NULL ,
 `Type`   enum('starters', 'first_course', 'second_course', 'dessert') NOT NULL ,

PRIMARY KEY (`FoodId`),
KEY `fkIdx_92` (`FoodId`),
CONSTRAINT `FK_92` FOREIGN KEY `fkIdx_92` (`FoodId`) REFERENCES `Food` (`FoodId`)
);

DROP TABLE `DishIngredients`;



-- ************************************** `DishIngredients`

CREATE TABLE `DishIngredients`
(
 `DishId`       int NOT NULL ,
 `IngredientId` int NOT NULL ,
 `Quantity`     float NOT NULL ,

PRIMARY KEY (`DishId`, `IngredientId`),
KEY `fkIdx_78` (`IngredientId`),
CONSTRAINT `FK_78` FOREIGN KEY `fkIdx_78` (`IngredientId`) REFERENCES `Ingredient` (`IngredientId`),
KEY `fkIdx_97` (`DishId`),
CONSTRAINT `FK_97` FOREIGN KEY `fkIdx_97` (`DishId`) REFERENCES `Dish` (`FoodId`)
);

DROP TABLE `Drink`;



-- ************************************** `Drink`

CREATE TABLE `Drink`
(
 `FoodId` int NOT NULL ,

PRIMARY KEY (`FoodId`),
KEY `fkIdx_87` (`FoodId`),
CONSTRAINT `FK_87` FOREIGN KEY `fkIdx_87` (`FoodId`) REFERENCES `Food` (`FoodId`)
);

DROP TABLE `Food`;



-- ************************************** `Food`

CREATE TABLE `Food`
(
 `FoodId` int NOT NULL AUTO_INCREMENT ,
 `Name`   varchar(45) NOT NULL ,
 `Type`   enum('drinks', 'starters', 'first_course', 'second_course', 'dessert') NOT NULL ,
 `Cost`   float NOT NULL ,

PRIMARY KEY (`FoodId`)
);

DROP TABLE `Ingredient`;



-- ************************************** `Ingredient`

CREATE TABLE `Ingredient`
(
 `IngredientId` int NOT NULL AUTO_INCREMENT ,
 `Name`         varchar(45) NOT NULL ,
 `Quantity`     float NOT NULL ,

PRIMARY KEY (`IngredientId`)
);

DROP TABLE `Order`;



-- ************************************** `Order`

CREATE TABLE `Order`
(
 `OrderId`  int NOT NULL AUTO_INCREMENT ,
 `WaiterId` int NOT NULL ,
 `TableId`  int NOT NULL ,
 `State`    enum('open', 'closed', 'payed') NOT NULL ,
 `Datetime` datetime NOT NULL ,

PRIMARY KEY (`OrderId`),
KEY `fkIdx_17` (`WaiterId`),
CONSTRAINT `FK_17` FOREIGN KEY `fkIdx_17` (`WaiterId`) REFERENCES `Waiter` (`WaiterId`),
KEY `fkIdx_33` (`TableId`),
CONSTRAINT `FK_33` FOREIGN KEY `fkIdx_33` (`TableId`) REFERENCES `Table` (`TableId`)
);

DROP TABLE `OrderContent`;



-- ************************************** `OrderContent`

CREATE TABLE `OrderContent`
(
 `OrderId`  int NOT NULL ,
 `FoodId`   int NOT NULL ,
 `Quantity` int NOT NULL ,

PRIMARY KEY (`OrderId`, `FoodId`),
KEY `fkIdx_57` (`OrderId`),
CONSTRAINT `FK_57` FOREIGN KEY `fkIdx_57` (`OrderId`) REFERENCES `Order` (`OrderId`),
KEY `fkIdx_61` (`FoodId`),
CONSTRAINT `FK_61` FOREIGN KEY `fkIdx_61` (`FoodId`) REFERENCES `Food` (`FoodId`)
);

DROP TABLE `Restaurant`;



-- ************************************** `Restaurant`

CREATE TABLE `Restaurant`
(
 `RestaurantId` int NOT NULL AUTO_INCREMENT ,
 `City`         varchar(45) NOT NULL ,

PRIMARY KEY (`RestaurantId`)
);

DROP TABLE `StateTimes`;



-- ************************************** `StateTimes`

CREATE TABLE `StateTimes`
(
 `StartTime` datetime NOT NULL ,
 `TableId`   int NOT NULL ,
 `State`     enum('free', 'reserved', 'busy', 'asking', 'waiting_for_food', 'served', 'waiting_for_bill', 'paying', 'in_preparation') NOT NULL ,

PRIMARY KEY (`StartTime`, `TableId`),
KEY `fkIdx_83` (`TableId`),
CONSTRAINT `FK_83` FOREIGN KEY `fkIdx_83` (`TableId`) REFERENCES `Table` (`TableId`)
);

DROP TABLE `Table`;



-- ************************************** `Table`

CREATE TABLE `Table`
(
 `TableId`      int NOT NULL AUTO_INCREMENT ,
 `RestaurantId` int NOT NULL ,
 `Seats`        int NOT NULL ,

PRIMARY KEY (`TableId`),
KEY `fkIdx_40` (`RestaurantId`),
CONSTRAINT `FK_40` FOREIGN KEY `fkIdx_40` (`RestaurantId`) REFERENCES `Restaurant` (`RestaurantId`)
);

DROP TABLE `User`;



-- ************************************** `User`

CREATE TABLE `User`
(
 `UserId`   int NOT NULL AUTO_INCREMENT ,
 `Username` varchar(45) NOT NULL ,
 `Password` varchar(45) NOT NULL ,
 `Type`     enum('room_head', 'waiter', 'cook', 'bartender', 'owner') NOT NULL ,
 `WaiterId` int NULL ,

PRIMARY KEY (`UserId`),
KEY `fkIdx_106` (`WaiterId`),
CONSTRAINT `FK_106` FOREIGN KEY `fkIdx_106` (`WaiterId`) REFERENCES `Waiter` (`WaiterId`)
);

DROP TABLE `Waiter`;



-- ************************************** `Waiter`

CREATE TABLE `Waiter`
(
 `WaiterId` int NOT NULL AUTO_INCREMENT ,
 `Name`     varchar(45) NOT NULL ,
 `Surname`  varchar(45) NOT NULL ,

PRIMARY KEY (`WaiterId`)
);
