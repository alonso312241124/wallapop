-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 28-11-2024 a las 03:09:10
-- Versión del servidor: 8.0.34-0ubuntu0.22.04.1
-- Versión de PHP: 8.1.2-1ubuntu2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `wallapop`
--
CREATE DATABASE IF NOT EXISTS `wallapop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `wallapop`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anuncios`
--

CREATE TABLE `anuncios` (
  `id` bigint NOT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `anuncios`
--

INSERT INTO `anuncios` (`id`, `descripcion`, `fecha_creacion`, `precio`, `titulo`, `usuario_id`) VALUES
(26, 'Se vende televisión de 70 pulgadas OLED en perfecto estado', '2024-11-28 02:31:37.063570', 400, 'TV 70\" OLED', 1),
(28, 'Se vende sofa en perfectas condiciones por falta de uso, el transporte está incluido en el precio', '2024-11-28 02:40:06.068031', 1000, 'Sofá', 1),
(29, 'Se vende Google Pixel 8a, como se puede ver en las fotos, está en perfecto estado', '2024-11-28 02:43:55.034787', 350, 'Pixel 8a', 2),
(30, 'Elegante mesa rectangular de madera de roble, ideal tanto para oficinas como para el hogar debido a su robustez', '2024-11-28 02:52:21.016221', 500, 'Mesa', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `foto_anuncio`
--

CREATE TABLE `foto_anuncio` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `anuncio_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `foto_anuncio`
--

INSERT INTO `foto_anuncio` (`id`, `nombre`, `anuncio_id`) VALUES
(46, '95b4b874-f932-416a-a7a8-e1eb61195af1.jpg', 26),
(47, '415abe51-3670-480a-a834-60cd674e3d21.jpeg', 26),
(50, 'a403686b-72d9-4d14-9d22-da947ccf1446.jpg', 28),
(51, 'f7b7b8e7-c3f1-417c-b309-778c255041db.jpg', 28),
(52, '7f1a9df8-2215-44e7-9519-b5f341716935.jpg', 29),
(53, 'cbe6b1a4-f581-4f76-9c79-1f8ba3d9e591.jpg', 29),
(54, '76350db2-8272-48c3-86d3-0602f7e1a829.jpg', 30),
(55, 'c5c16c8e-5725-4282-956e-7f93649f5df2.jpg', 30),
(56, 'e3b6d522-881e-4a18-97e0-2ee7f59df9f2.jpg', 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `poblacion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `email`, `nombre`, `password`, `poblacion`, `telefono`) VALUES
(1, 'alonso@gmail.com', 'alonso', '$2y$10$Jzcmxbq3zqZFOKaANgsxJ.BUyC8OI5O7LcFum6kDiQZFfS/W/kTgC', 'Población', '123'),
(2, 'admin@gmail.com', 'admin', '$2a$12$2hhPG.7TmvbHDs3.1EaU4ehBgSQES.d5H2QE7JjE55WRl3SoLMzDG', 'X', '123'),
(3, 'registro@gmail.com', 'PruebaRegistro', '$2a$10$j1Wn.cmxydQ0H4sAbcqPiuzqeh4YUKqlBsxoiUTtSCvmoP8Mibc2G', 'X', '123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `anuncios`
--
ALTER TABLE `anuncios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnonliymh7f3v70k87lf8p22jm` (`usuario_id`);

--
-- Indices de la tabla `foto_anuncio`
--
ALTER TABLE `foto_anuncio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg9w60yh700b3xtvc8m7fngvxo` (`anuncio_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `anuncios`
--
ALTER TABLE `anuncios`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `foto_anuncio`
--
ALTER TABLE `foto_anuncio`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `anuncios`
--
ALTER TABLE `anuncios`
  ADD CONSTRAINT `FKnonliymh7f3v70k87lf8p22jm` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `foto_anuncio`
--
ALTER TABLE `foto_anuncio`
  ADD CONSTRAINT `FKg9w60yh700b3xtvc8m7fngvxo` FOREIGN KEY (`anuncio_id`) REFERENCES `anuncios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
