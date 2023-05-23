-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: May 20, 2023 at 02:25 PM
-- Server version: 5.7.34
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_app_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `user_id`) VALUES
(38, 38),
(40, 40),
(41, 41),
(42, 42),
(43, 43),
(44, 44);

-- --------------------------------------------------------

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_item` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `size` varchar(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `topping` varchar(60) NOT NULL DEFAULT '"EMPTY"',
  `notes` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart_item`
--

INSERT INTO `cart_item` (`id`, `user_id`, `cart_id`, `product_id`, `quantity`, `size`, `price`, `topping`, `notes`) VALUES
(6, 38, 38, 8, 1, 'L', 40000, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `is_disable` tinyint(1) DEFAULT '0',
  `image` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `is_disable`, `image`) VALUES
(1, 'Cà Phê Truyền Thống', 0, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680886636/AppCoffee/Caphett_godfsq.png'),
(2, 'Cà Phê Pha Máy', 0, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680886636/AppCoffee/Caphephamay_rgapbz.png'),
(3, 'Trà', 0, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680886637/AppCoffee/Tra_esuysd.png'),
(4, 'Phindi', 0, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680886636/AppCoffee/Phidi_ln6oa7.png'),
(5, 'Đá xay', 0, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680886636/AppCoffee/Daxay_eldmbh.png'),
(6, 'Thức uống khác', 0, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680886637/AppCoffee/Douongkhac_knvjda.png');

-- --------------------------------------------------------

--
-- Table structure for table `favorite_product`
--

CREATE TABLE `favorite_product` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `favorite_product`
--

INSERT INTO `favorite_product` (`id`, `user_id`, `product_id`) VALUES
(4, 40, 2),
(5, 40, 4),
(7, 38, 1),
(8, 38, 2),
(9, 38, 8);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `sub_title` varchar(150) DEFAULT NULL,
  `image` varchar(150) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `is_seen` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `user_id`, `title`, `sub_title`, `image`, `description`, `time`, `type`, `is_seen`) VALUES
(25, 38, 'Xác nhận đơn hàng', 'Đơn hàng 48851 của bạn đã được xác nhận', '', 'Dong Nai', '2023-05-20 20:52:40', 1, 0),
(26, 38, 'Giao hàng thành công', 'Đơn hàng có mã 48851 của bạn đã được giao thành công', '', 'Dong Nai', '2023-05-20 20:53:21', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `shipping_address` varchar(90) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `promotion_id` int(11) DEFAULT NULL,
  `payment_method_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `order_date`, `shipping_address`, `total`, `status`, `promotion_id`, `payment_method_id`) VALUES
(179, 38, '2023-05-20 20:55:00', 'Dong Nai', 114000, 2, 3, 3),
(180, 38, '2023-05-20 20:55:00', '227 Nguyen Van Cu', 34000, -1, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `order_product`
--

CREATE TABLE `order_product` (
  `id` int(11) NOT NULL,
  `note` varchar(45) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `topping` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_product`
--

INSERT INTO `order_product` (`id`, `note`, `order_id`, `product_id`, `quantity`, `price`, `size`, `topping`) VALUES
(92, '', 179, 8, 1, 40000, 'L', ''),
(93, '', 179, 4, 2, 112000, 'M', '-Đào Miếng'),
(94, '', 180, 8, 1, 40000, 'L', '');

-- --------------------------------------------------------

--
-- Table structure for table `payment_method`
--

CREATE TABLE `payment_method` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment_method`
--

INSERT INTO `payment_method` (`id`, `name`) VALUES
(1, 'Thanh toán trực tiếp'),
(2, 'Momo'),
(3, 'Zalopay'),
(4, 'Credit Card');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `size` varchar(1) DEFAULT NULL,
  `price_S` double DEFAULT NULL,
  `price_M` double DEFAULT NULL,
  `price_L` double DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `is_disable` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `size`, `price_S`, `price_M`, `price_L`, `image`, `status`, `category_id`, `update_date`, `release_date`, `sales`, `is_disable`) VALUES
(1, 'Cà Phê Đá', 'Với vị đắng đặc trưng của cà phê kết hợp với sự mát lạnh của đá, cà phê đá là một lựa chọn thưởng thức sảng khoái và tiếp thêm năng lượng trong những ngày nóng.', 'M', 25000, 35000, 45000, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680964418/AppCoffee/product01_tzhg0o.png', 0, 1, '2020-12-21 12:18:33', '2020-12-21 12:18:33', 12, 0),
(2, 'Trà sen', 'Trà sen thức uống là sự kết hợp tuyệt vời giữa hoa sen tươi và lá sen non, tạo nên một ly trà thơm ngon, mát lạnh và tinh tế, mang đến cho bạn trải nghiệm thư giãn và cảm nhận sự tươi mới của thiên nhiên.', 'M', 20000, 30000, 40000, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680964418/AppCoffee/product02_dc0ifb.png', 0, 3, '2023-04-08 14:37:41', '2023-04-08 14:37:41', 10, 0),
(3, 'Cà phê sữa', 'Với hương vị đắng ngọt hài hòa, cà phê sữa mang đến sự thưởng thức thú vị và đầy năng lượng, là lựa chọn hoàn hảo để bắt đầu ngày mới hoặc thưởng thức trong suốt cả ngày', 'M', 35000, 45000, 55000, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680964419/AppCoffee/product03_ftlmxd.png', 0, 2, '2023-04-08 14:37:41', '2023-04-08 14:37:41', 10, 0),
(4, 'Trà đào Cam sả', 'Kết hợp giữa hương vị đào ngọt ngào, vị cam tươi mát và hương thơm của lá sả, trà đào cam sả là một thức uống thú vị và sảng khoái, làm dịu cơn khát và mang lại cảm giác sảng khoái trong ngày nóng hổi.', 'M', 36000, 46000, 56000, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680964419/AppCoffee/product04_vwfc1h.png', 0, 3, '2023-04-08 14:37:41', '2023-04-08 14:37:41', 10, 0),
(5, 'Trà bí', 'Trà bí là một loại trà độc đáo được làm từ lá bí, mang đến hương vị thảo mộc tự nhiên và tinh tế.', 'M', 23000, 33000, 43000, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680964419/AppCoffee/product05_zbrrkx.png', 0, 4, '2023-05-16 10:22:00', '2023-04-08 14:37:41', 10, 0),
(6, 'Trà sữa', 'Với hương vị đặc trưng, cân đối giữa đắng nhẹ của trà và ngọt mềm của sữa, trà sữa là một lựa chọn thưởng thức thú vị và phổ biến trong nhiều quán trà và cửa hàng đồ uống.', 'M', 20000, 20000, 20000, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680964419/AppCoffee/product06_ppjyov.png', 0, 6, '2023-04-08 14:37:41', '2023-04-08 14:37:41', 10, 0),
(7, 'Sữa đá', 'Với vị ngọt của sữa kết hợp với sự mát lạnh của đá, sữa đá là một lựa chọn thú vị và thỏa mãn trong những ngày nóng, mang lại cảm giác sảng khoái và làm dịu cơn khát.', 'M', 22000, 32000, 42000, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680964420/AppCoffee/product07_plcpf0.png', 0, 1, '2023-04-08 14:37:41', '2023-04-08 14:37:41', 10, 0),
(8, 'Bạc sỉu đá', 'Bạc sỉu thức uống của kẻ nặng tình', 'M', 20000, 30000, 40000, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1680964420/AppCoffee/product08_badclo.png', 0, 4, '2023-04-08 14:37:41', '2023-04-08 14:37:41', 10, 0),
(10, 'Ca phe ngon', 'adssss', 'M', 10000, 15000, 20000, 'https://res.cloudinary.com/drtiuuibf/image/upload/v1684591258/AppCoffee/fy5ajpypuj3irnb9fec2.png', 1, 1, '2023-05-20 21:00:53', '2023-05-20 21:00:53', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `discount` decimal(10,0) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `image` longtext NOT NULL,
  `is_disable` tinyint(1) NOT NULL DEFAULT '0',
  `quantity` int(11) NOT NULL,
  `code` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id`, `name`, `description`, `discount`, `start_date`, `end_date`, `image`, `is_disable`, `quantity`, `code`) VALUES
(1, 'Ưu đãi tháng 4', 'Nhiều ưu đãi trong tháng 4.Mua cà phê nào!!', '15', '2023-12-04 12:18:33', '2023-04-04 12:18:33', 'https://res.cloudinary.com/drtiuuibf/image/upload/v1681014686/AppCoffee/voucher03_bijqn5.jpg', 0, 10, NULL),
(2, 'Ưu đãi tháng 5', 'Nhiều ưu đãi trong tháng 5.Mua cà phê nào!!', '35', '2023-12-04 12:18:33', '2023-04-04 12:18:33', 'https://res.cloudinary.com/drtiuuibf/image/upload/v1681014685/AppCoffee/voucher01_zzubal.jpg', 0, 10, NULL),
(3, 'Ưu đãi tháng 6', 'Nhiều ưu đãi trong tháng 6.Mua cà phê nào!!', '25', '2023-12-04 12:18:33', '2023-04-04 12:18:33', 'https://res.cloudinary.com/drtiuuibf/image/upload/v1681014686/AppCoffee/voucher03_bijqn5.jpg', 0, 10, NULL),
(7, 'Khuyen mai 25', 'Giam gia 20', '15', '2023-05-20 00:00:00', '2023-05-20 00:00:00', 'https://res.cloudinary.com/drtiuuibf/image/upload/v1684591411/AppCoffee/lg8lphcodo2m9wg6babf.webp', 0, 1, 'qq');

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `user_image` varchar(100) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_disable` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`id`, `user_id`, `user_name`, `user_image`, `product_id`, `score`, `comment`, `create_at`, `is_disable`) VALUES
(3, 40, 'bhcdgage', 'https://cdn-icons-png.flaticon.com/512/552/552721.png', 1, 4.5, 'Sản phẩm ngon', '2023-05-13 16:41:07', 0),
(4, 40, 'bhcdgage', NULL, 2, 4.5, 'đf', '2023-05-13 16:58:01', 0),
(5, 38, 'Bui Quang Thanh', 'https://lh3.googleusercontent.com/a/AGNmyxYMDEEWHxPEQHL_RWca4Ow0gCwUN2fjeVYTd5LX=s96-c', 2, 4.5, 'San pham ngon', '2023-05-16 07:16:32', 0);

-- --------------------------------------------------------

--
-- Table structure for table `refund_order`
--

CREATE TABLE `refund_order` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `token` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `refund_order`
--

INSERT INTO `refund_order` (`id`, `order_id`, `token`) VALUES
(26, 137, ''),
(27, 138, ''),
(28, 139, ''),
(29, 140, ''),
(30, 172, '230520000006955'),
(31, 173, '230520000006875'),
(32, 174, '230520000006876'),
(33, 175, '230520000006959'),
(34, 176, '230520000006878'),
(35, 177, '230520000007140'),
(36, 178, '230520000007145'),
(37, 179, '230520000007240'),
(38, 180, '230520000007241');

-- --------------------------------------------------------

--
-- Table structure for table `token_firebase`
--

CREATE TABLE `token_firebase` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `token` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `token_firebase`
--

INSERT INTO `token_firebase` (`id`, `user_id`, `token`) VALUES
(9, 40, 'fvlFmGw8Ts2zkGsVc5xybp:APA91bFF5bEdWOS_K5br5SSPIAr_77122mzDbrhKDh00fj1oFgsChj7uOIiPLUCXS83CmKIC9J8PMLv4uZCS3Kxd61BEaRmVcLrfPOhgGqZoK_tPluBgxr7Lte2a6AFJLwBcVHJpTiUV'),
(10, 38, 'fvlFmGw8Ts2zkGsVc5xybp:APA91bFF5bEdWOS_K5br5SSPIAr_77122mzDbrhKDh00fj1oFgsChj7uOIiPLUCXS83CmKIC9J8PMLv4uZCS3Kxd61BEaRmVcLrfPOhgGqZoK_tPluBgxr7Lte2a6AFJLwBcVHJpTiUV'),
(11, 41, 'dqkOHpW9RhibJ26RgNvUWN:APA91bHpuw1JJNVrzOLvwfVoq9tUuWrimUZXG9CJ1r8NfjVgRqMpdli3QbIfAaUzBXBzkZ7yJFajqlLS8jksoV-JWWBECavWRWaL9AUNk2dwTJR2jZkSSAIvs8bIQ46tsn4k3yH2Ky-m'),
(12, 46, 'eosu3RSVRD2KGsU9WGTQ0L:APA91bEYv-rH-cmtFUVExk9IbSg8kD40fxuQf083k5X1lslB9P3RR9Xu295blw-1hKodfq28D74ba3BFHU6SxB9l0MXC3k4T5kt0wXcXmO8T9Wh_6wXQU3Z53o3DSPQHbWCPeNIZgx_-');

-- --------------------------------------------------------

--
-- Table structure for table `topping`
--

CREATE TABLE `topping` (
  `category_id` int(11) NOT NULL,
  `name` varchar(60) NOT NULL,
  `price` double NOT NULL,
  `checked` tinyint(1) NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `topping`
--

INSERT INTO `topping` (`category_id`, `name`, `price`, `checked`, `id`) VALUES
(1, 'Thạch Cà Phê', 10000, 0, 1),
(3, 'Đào Miếng', 10000, 0, 4),
(3, 'Hạt Sen', 10000, 0, 6),
(3, 'thach rau cau', 5000, 0, 7);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(45) DEFAULT NULL,
  `avatar` longtext,
  `role` varchar(45) DEFAULT '0',
  `is_disable` varchar(45) DEFAULT '0',
  `otp` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `gender`, `email`, `phone`, `password`, `date_of_birth`, `address`, `avatar`, `role`, `is_disable`, `otp`) VALUES
(38, 'Bui Quang Thanh', NULL, 'buiquangthanh1709@gmail.com', NULL, '$2b$10$XX1pI3YnwZnzJW4sQsrgLevyGZ1MnkOExd9T6ACAqzcoDV/6I47Ca', '2023-12-04 12:18:33', NULL, 'https://lh3.googleusercontent.com/a/AGNmyxYMDEEWHxPEQHL_RWca4Ow0gCwUN2fjeVYTd5LX=s96-c', '0', '0', NULL),
(40, 'Thanh Quang', 'Nam', 'thanhihi@gmail.com', '0368826352', '$2b$06$PZka9kfuhn6cAGujz4QWn.NorXnHBDBRf.uYQqazdgYM.tisAdJha', '2002-09-17 00:00:00', 'Thu Duc', NULL, '0', '0', ''),
(41, 'dgbfcfec', NULL, 'admin', 'admin', '$2b$06$FcLN2HZMnhOo8Mn5TpWer.WggXPxs5KRgqhLcTvy2NtEQ0.I2hl8u', '2023-12-04 12:18:33', NULL, NULL, '1', '0', ''),
(42, 'gdaddcaa', NULL, 'thanh@gmail.com', NULL, '$2b$10$bAKx8dtzNHDcc4IQRBMD1e7iV3xR52uCfDFr5cVVODQKLGqRCe6va', '2023-12-04 12:18:33', NULL, NULL, '0', '0', NULL),
(43, 'hdabeafh', NULL, 'jijilee1208@gmail.com', NULL, '$2b$10$zf77IGMSFXBNMVqiQNUrte9YTicAwvED4TT9u.SqxO1BtvDYsf4U2', '2023-12-04 12:18:33', NULL, NULL, '0', '0', NULL),
(44, 'gfbcehaf', NULL, 'thanhhihi@gmail.com', NULL, '$2b$06$tEkBGOUyBwTOCJk8/PjC1e9WkwBbEjvWuuvGVupMt48hiNfpiHdyq', '2023-05-20 16:41:34', NULL, NULL, '0', '0', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cart_user_idx` (`user_id`);

--
-- Indexes for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cartitem_cart_idx` (`cart_id`),
  ADD KEY `fk_cartitem_user_idx` (`user_id`),
  ADD KEY `fk_foreign_cart_item_product` (`product_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `favorite_product`
--
ALTER TABLE `favorite_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_order_user_idx` (`user_id`),
  ADD KEY `fk_order_promotion_idx` (`promotion_id`),
  ADD KEY `fk_order_paymentmethod_idx` (`payment_method_id`);

--
-- Indexes for table `order_product`
--
ALTER TABLE `order_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_oderproduct_product_idx` (`product_id`),
  ADD KEY `fk_oderproduct_order_idx` (`order_id`);

--
-- Indexes for table `payment_method`
--
ALTER TABLE `payment_method`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product_category_idx` (`category_id`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_rating_user_idx` (`user_id`),
  ADD KEY `fk_rating_product_idx` (`product_id`);

--
-- Indexes for table `refund_order`
--
ALTER TABLE `refund_order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `token_firebase`
--
ALTER TABLE `token_firebase`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `topping`
--
ALTER TABLE `topping`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_foreign_key_name` (`category_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `favorite_product`
--
ALTER TABLE `favorite_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=181;

--
-- AUTO_INCREMENT for table `order_product`
--
ALTER TABLE `order_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT for table `payment_method`
--
ALTER TABLE `payment_method`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `refund_order`
--
ALTER TABLE `refund_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `token_firebase`
--
ALTER TABLE `token_firebase`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `topping`
--
ALTER TABLE `topping`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD CONSTRAINT `fk_cartitem_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  ADD CONSTRAINT `fk_cartitem_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fk_foreign_cart_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_order_paymentmethod` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
  ADD CONSTRAINT `fk_order_promotion` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`),
  ADD CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `order_product`
--
ALTER TABLE `order_product`
  ADD CONSTRAINT `fk_oderproduct_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `fk_oderproduct_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `fk_rating_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `fk_rating_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `topping`
--
ALTER TABLE `topping`
  ADD CONSTRAINT `fk_foreign_key_name` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
