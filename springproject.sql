-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Ноя 13 2021 г., 15:48
-- Версия сервера: 10.4.11-MariaDB
-- Версия PHP: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `springproject`
--

-- --------------------------------------------------------

--
-- Структура таблицы `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `post_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `comments`
--

INSERT INTO `comments` (`id`, `item_id`, `comment`, `user_id`, `post_date`) VALUES
(37, 21, '            sd', 3, '2021-06-13 09:43:22'),
(51, 19, 'soglasen', 5, '2021-06-13 09:59:51'),
(55, 25, '            asdas', 3, '2021-06-16 13:33:23');

-- --------------------------------------------------------

--
-- Структура таблицы `countries`
--

CREATE TABLE `countries` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `countries`
--

INSERT INTO `countries` (`id`, `code`, `name`) VALUES
(1, 'USA', 'United States of America'),
(2, 'KAZ', 'Kazakhstan'),
(3, 'RUS', 'Russian'),
(4, 'ITA', 'Italya'),
(5, 'CHI', 'China'),
(6, 'KRA', 'Korea');

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `cardnumber` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `expiration` varchar(255) DEFAULT NULL,
  `items_id` bigint(20) DEFAULT NULL,
  `users_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_MODERATOR');

-- --------------------------------------------------------

--
-- Структура таблицы `t_categories`
--

CREATE TABLE `t_categories` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `t_categories`
--

INSERT INTO `t_categories` (`id`, `name`, `icon`) VALUES
(1, 'TV', 'fas fa-tv'),
(2, 'HOME', 'fas fa-home'),
(3, 'Furniture', 'fas fa-couch'),
(4, 'Electronics', 'fas fa-atom'),
(5, 'Telephones', 'fas fa-mobile'),
(6, 'Computers', 'fa-laptop-code');

-- --------------------------------------------------------

--
-- Структура таблицы `t_items`
--

CREATE TABLE `t_items` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `t_items`
--

INSERT INTO `t_items` (`id`, `amount`, `name`, `price`, `country_id`, `images`) VALUES
(25, 32, 'IPhone 12 Pro MAX', 650000, 1, 'b0760842c2af15277b834a61034a565f64658096'),
(26, 55, 'Samsung S21 Ultra', 7500000, 6, 'f6822406bac93b438a8a97a69c678c744149cea5'),
(27, 32, 'Electronic HHR', 67000, 6, '6f516b222bcfd7fdfefc4e1245fc7f7224e774c8'),
(28, 56, 'Electronic YY2', 42500, 3, '8008f7fbe7837f59e54b88225ce6f74f8bf5b4c8'),
(29, 55, 'Furniture RX', 44555, 5, 'b052b18fc1e954c2d1faf8af7a80fa5ffed446ea'),
(30, 56, 'Furniture OOSA', 60000, 2, '0eab8daf5ff0abcedd052d53e26caf4366325f7e'),
(31, 78, 'RX COMP', 56566, 1, 'f26e9dbc3995d5a2107286811a9d707c504e4041'),
(32, 76, 'UU2 COMPUTER', 35, 4, '7fb944f5ab1916ce567d16034e73e816e2225ba1'),
(33, 67, 'TV LG MRX', 150000, 1, '1c7c3913b126d1df77ca9b3b9cb627091c65e661');

-- --------------------------------------------------------

--
-- Структура таблицы `t_items_categories`
--

CREATE TABLE `t_items_categories` (
  `shop_items_id` bigint(20) NOT NULL,
  `categories_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `t_items_categories`
--

INSERT INTO `t_items_categories` (`shop_items_id`, `categories_id`) VALUES
(25, 5),
(26, 5),
(27, 4),
(28, 4),
(29, 3),
(30, 3),
(31, 6),
(32, 6),
(33, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `avatar`, `email`, `full_name`, `password`) VALUES
(3, 'e05d592d732f3216d3182253f596e1c08a368cba', 'siko@mail.ru', 'Syrym Khakimzhan', '$2y$12$eeVGbak3N4FwR7Nma2j2UO/VdQnShi3BoJYoph0fi6HzdpVF3YmUm'),
(4, '910c2c3dc8522b7e8e0117ec26ed25bac884029c', 'nurbak@mail.ru', 'Nurbak Amantay', '$2a$10$AoziT5lOyh1Ie4TSa57Tsuj8C/Bh08516R9Ke3nyhvxLlB9PZFbIO'),
(5, 'b839a25fb1dc8d85eec8f3d87ebc63c806a77eae', 'ayan@mail.ru', 'Ayan Abdygalimov', '$2a$10$IVzNzL0Wbovnm4YY8XFr7.42of57hT7FgsxZZ45zNP/d5QQLaIu52');

-- --------------------------------------------------------

--
-- Структура таблицы `users_items`
--

CREATE TABLE `users_items` (
  `users_id` bigint(20) NOT NULL,
  `items_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `users_orders`
--

CREATE TABLE `users_orders` (
  `users_id` bigint(20) NOT NULL,
  `orders_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `users_roles`
--

CREATE TABLE `users_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users_roles`
--

INSERT INTO `users_roles` (`users_id`, `roles_id`) VALUES
(3, 1),
(3, 2),
(3, 3),
(4, 2),
(5, 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`);

--
-- Индексы таблицы `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8tphwc42l5l8w9d1x4wcm4rad` (`items_id`),
  ADD KEY `FKe6k45xxoin4fylnwg2jkehwjf` (`users_id`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_categories`
--
ALTER TABLE `t_categories`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `t_items`
--
ALTER TABLE `t_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp23caxhaylkiybubsn9kv9jat` (`country_id`);

--
-- Индексы таблицы `t_items_categories`
--
ALTER TABLE `t_items_categories`
  ADD KEY `FKcbumi5w6c6k140k2qm0jjf4lw` (`categories_id`),
  ADD KEY `FKc44404ytvlhomhp7rxpr87gjw` (`shop_items_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users_items`
--
ALTER TABLE `users_items`
  ADD PRIMARY KEY (`users_id`,`items_id`),
  ADD KEY `FKrfvdtwadlfu0f67uhbc4wtixm` (`items_id`);

--
-- Индексы таблицы `users_orders`
--
ALTER TABLE `users_orders`
  ADD PRIMARY KEY (`users_id`,`orders_id`),
  ADD KEY `FK2lnf5jw8p8q0ytkr8dp0mlx6` (`orders_id`);

--
-- Индексы таблицы `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`users_id`,`roles_id`),
  ADD KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT для таблицы `countries`
--
ALTER TABLE `countries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `t_categories`
--
ALTER TABLE `t_categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `t_items`
--
ALTER TABLE `t_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK8tphwc42l5l8w9d1x4wcm4rad` FOREIGN KEY (`items_id`) REFERENCES `t_items` (`id`),
  ADD CONSTRAINT `FKe6k45xxoin4fylnwg2jkehwjf` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `t_items`
--
ALTER TABLE `t_items`
  ADD CONSTRAINT `FKp23caxhaylkiybubsn9kv9jat` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`);

--
-- Ограничения внешнего ключа таблицы `t_items_categories`
--
ALTER TABLE `t_items_categories`
  ADD CONSTRAINT `FKc44404ytvlhomhp7rxpr87gjw` FOREIGN KEY (`shop_items_id`) REFERENCES `t_items` (`id`),
  ADD CONSTRAINT `FKcbumi5w6c6k140k2qm0jjf4lw` FOREIGN KEY (`categories_id`) REFERENCES `t_categories` (`id`);

--
-- Ограничения внешнего ключа таблицы `users_items`
--
ALTER TABLE `users_items`
  ADD CONSTRAINT `FKjes50fhqrxusip32tfwk24srr` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKrfvdtwadlfu0f67uhbc4wtixm` FOREIGN KEY (`items_id`) REFERENCES `t_items` (`id`);

--
-- Ограничения внешнего ключа таблицы `users_orders`
--
ALTER TABLE `users_orders`
  ADD CONSTRAINT `FK2lnf5jw8p8q0ytkr8dp0mlx6` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FK75npb8mwvkbqaiytagtvir73m` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKml90kef4w2jy7oxyqv742tsfc` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
