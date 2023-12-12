create database productsdb;
use productsdb;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` varchar(512) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`product_id`)
);

CREATE TABLE `product_detail` (
  `product_id` int(11) NOT NULL,
  `part_number` varchar(45) NOT NULL,
  `dimension` varchar(45) NOT NULL,
  `weight` float NOT NULL,
  `manufacturer` varchar(45) NOT NULL,
  `origin` varchar(45) NOT NULL,
  PRIMARY KEY (`product_id`)
);