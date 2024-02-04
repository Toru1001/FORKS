-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 07, 2023 at 05:27 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `forks_data`
--

-- --------------------------------------------------------

--
-- Table structure for table `foodproducts`
--

CREATE TABLE `foodproducts` (
  `food_type` varchar(250) NOT NULL,
  `food_id` varchar(250) NOT NULL,
  `food_name` varchar(250) NOT NULL,
  `price` int(13) NOT NULL,
  `icon` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `foodproducts`
--

INSERT INTO `foodproducts` (`food_type`, `food_id`, `food_name`, `price`, `icon`) VALUES
('burger', 'B1', 'Cheese Burger', 45, '/com/forks/products/B1.png'),
('burger', 'B2', 'Chicken Burger', 60, '/com/forks/products/B2.png'),
('burger', 'B3', 'Double Patty', 75, '/com/forks/products/B3.png'),
('burger', 'B4', 'Spicy Beef Burger', 50, '/com/forks/products/B4.png'),
('Chicken', 'C1', 'One chicken w/ rice', 80, '/com/forks/products/C1.2.png'),
('Chicken', 'C2', 'Two chicken w/ rice', 130, '/com/forks/products/C2.2.png'),
('Sides', 'CHS', 'Chocolate sundae', 45, '/com/forks/products/CHS.png'),
('Sides', 'CS', 'Caramel Vanilla', 45, '/com/forks/products/CS.png'),
('Drink', 'D1', 'Coca Cola', 25, '/com/forks/products/coke something.png'),
('Drink ', 'D2', 'Sprite', 25, '/com/forks/products/sprite.png'),
('Drink', 'D3', 'Fanta', 25, '/com/forks/products/fanta.png'),
('Drink', 'D4', 'Iced Tea', 25, '/com/forks/products/iced tea.png'),
('Drink', 'D5', 'Coke float ', 50, '/com/forks/products/CF2.png'),
('Sides', 'F1', 'Plain fries', 35, '/com/forks/products/F1.png'),
('Sides', 'F2', 'Cheese flavored fries', 50, '/com/forks/products/F2.png'),
('Chicken', 'M1', 'C1 + drink', 102, '/com/forks/products/M1.png'),
('Chicken', 'M2', 'C1 + F1 + Drink', 135, '/com/forks/products/M2.png'),
('Chicken', 'M3', 'C2 + Drink', 150, '/com/forks/products/M3.png'),
('Chicken', 'M4', 'C2 + F1 + Drink', 185, '/com/forks/products/M4.png'),
('Pasta', 'P1', 'Carbonara', 95, '/com/forks/products/P1.png'),
('Pasta', 'P2', 'P1 + F1 + Drink', 150, '/com/forks/products/P2.png'),
('Pasta', 'P3', 'C1 + D5', 130, '/com/forks/products/P3.png'),
('Pasta ', 'S1', 'Solo Spaghetti ', 85, '/com/forks/products/S1.1.png'),
('Pasta ', 'S2', 'Solo Spaghetti w/ Drink', 100, '/com/forks/products/S2.png'),
('Pasta', 'S3', 'S1 + D5 + Chicken', 145, '/com/forks/products/S3.png'),
('Sides ', 'SS', 'Strawberry drizzled Vanilla sundae', 45, '/com/forks/products/VS.png'),
('burger', 'X1', 'B1 + Plain Fries', 65, '/com/forks/products/X1.png'),
('burger', 'X2', 'B2 + Cheese Fries', 98, '/com/forks/products/X1.png'),
('burger', 'X3', 'B3 + F1 + Drink', 120, '/com/forks/products/X3.png'),
('burger', 'X4', 'B4 + F2 + Drink', 100, '/com/forks/products/X4.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `foodproducts`
--
ALTER TABLE `foodproducts`
  ADD PRIMARY KEY (`food_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
