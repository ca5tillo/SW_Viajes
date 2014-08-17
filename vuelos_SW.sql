-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 09-07-2014 a las 19:37:23
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `vuelos_SW`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotelesBD`
--

CREATE TABLE IF NOT EXISTS `hotelesBD` (
  `idhotel` int(100) NOT NULL AUTO_INCREMENT,
  `ubicacion` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `numeroDeHabitaciones` int(100) NOT NULL,
  `numHabitacionesLibres` int(100) NOT NULL,
  `costoXdia` double NOT NULL,
  `fecha` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `estrellas` int(20) NOT NULL,
  PRIMARY KEY (`idhotel`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=20 ;

--
-- Volcado de datos para la tabla `hotelesBD`
--

INSERT INTO `hotelesBD` (`idhotel`, `ubicacion`, `nombre`, `numeroDeHabitaciones`, `numHabitacionesLibres`, `costoXdia`, `fecha`, `estrellas`) VALUES
(1, 'Alemania', 'Sofitel Berlin Kurfürstendamm', 40, 24, 1020.89, '2014-07-17', 5),
(2, 'Alemania', 'Regent Hotel', 20, 7, 1889, '2014-07-17', 4),
(3, 'Alemania', 'TRYP München City Center Hotel', 22, 3, 798.8, '2014-07-17', 4),
(4, 'Alemania', 'Hilton Munich Park', 20, 12, 2567, '2014-07-17', 5),
(5, 'México', 'Oasis Palm', 21, 4, 1890, '2014-07-17', 4),
(6, 'México', 'Smart The Lounge Hotel', 17, 5, 798.9, '2014-07-17', 3),
(7, 'México', 'Cristal Grand Reforma', 15, 9, 2568, '2014-07-17', 2),
(8, 'México', 'Camino Real', 42, 6, 1020.91, '2014-07-17', 2),
(9, 'México', 'Hotel Imperial Reforma', 22, 16, 1891, '2014-07-17', 3),
(10, 'Alemania', 'Arcadia Hotel Hannover', 24, 0, 798.1, '2014-07-17', 4),
(11, 'Alemania', 'Novotel Hannover', 22, 9, 2569, '2014-07-17', 5),
(12, 'Alemania', 'Maritim Grand Hotel', 43, 17, 1020.92, '2014-07-17', 4),
(13, 'Alemania', 'Van Der Valk Hotel', 23, 8, 1892, '2014-07-17', 6),
(14, 'Alemania', 'Althoff Hotel Furstenhof', 25, 19, 798.11, '2014-07-17', 5),
(15, 'Rusia', 'Renaissence Baltic Hotel', 14, 1, 2570, '2014-07-17', 5),
(16, 'Rusia', '5th Corner', 44, 22, 1020.93, '2014-07-17', 6),
(17, 'Rusia', 'Rusia', 24, 0, 1893, '2014-07-17', 4),
(18, 'Rusia ', 'Sokos Hotel Olympia Graden', 26, 19, 798.12, '2014-07-17', 5),
(19, 'Rusia', 'Nevsky Hotel', 26, 9, 2571, '2014-07-17', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelos`
--

CREATE TABLE IF NOT EXISTS `vuelos` (
  `idvuelo` int(255) NOT NULL AUTO_INCREMENT,
  `aerolinea` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `origen` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `destino` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `fechaS` date NOT NULL,
  `horaS` time(6) NOT NULL,
  `duracionVuelo` time(6) NOT NULL,
  `costo` double NOT NULL,
  PRIMARY KEY (`idvuelo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=104 ;

--
-- Volcado de datos para la tabla `vuelos`
--

INSERT INTO `vuelos` (`idvuelo`, `aerolinea`, `origen`, `destino`, `fechaS`, `horaS`, `duracionVuelo`, `costo`) VALUES
(1, 'aerolinea_B', 'Afganistán', 'Albania', '2014-07-01', '08:00:00.000000', '03:00:00.000000', 1052.85),
(2, 'aerolinea_B', 'Albania', 'Alemania', '2014-07-01', '12:00:00.000000', '03:00:00.000000', 2759.2),
(3, 'aerolinea_A', 'Albania', 'Alemania', '2014-07-01', '07:00:00.000000', '03:00:00.000000', 1759.2),
(4, 'aerolinea_C', 'Albania', 'Alemania', '2014-07-06', '09:00:00.000000', '03:00:00.000000', 1569.2),
(5, 'Aerolinea_B', 'Albania', 'Afganistán', '2014-07-02', '08:00:00.000000', '03:00:03.000000', 1840.4),
(6, 'Aerolinea_B', 'Antigua y Barbuda', 'Antillas Holandesas', '2014-07-01', '08:00:00.000000', '03:00:00.000000', 2759.2),
(7, 'aerolinea_A', 'Antillas Holandesas', 'Argelia', '2014-07-01', '09:00:00.000000', '08:00:00.000000', 1759.2),
(8, 'Aerolinea_B', 'Argelia', 'Armenia', '2014-07-01', '10:00:00.000000', '04:00:00.000000', 1569.2),
(9, 'aerolinea_C', 'Austria', 'Argelia', '2014-07-01', '11:00:00.000000', '05:00:00.000000', 1840.4),
(10, 'aerolinea_D', 'Bangladesh', 'Argentina', '2014-07-01', '838:59:59.000000', '02:00:00.000000', 2759.3),
(11, 'aerolinea_F', 'Bélgica', 'Australia', '2014-07-01', '03:00:00.000000', '03:00:00.000000', 1759.3),
(12, 'aerolinea_G', 'Argentina', 'Austria', '2014-07-02', '08:00:00.000000', '06:00:00.000000', 1569.3),
(13, 'aerolinea_H', 'Argelia', 'Bélgica', '2014-07-02', '09:00:00.000000', '01:00:00.000000', 1840.5),
(14, 'aerolinea_I', 'Belice', 'Bangladesh', '2014-07-02', '06:00:00.000000', '03:00:00.000000', 2759.4),
(15, 'Aerolinea_B', 'Australia', 'Argentina', '2014-07-02', '08:00:00.000000', '08:00:00.000000', 1759.4),
(16, 'aerolinea_A', 'Bermudas', 'Australia', '2014-07-03', '09:00:00.000000', '04:00:00.000000', 1569.4),
(17, 'Aerolinea_B', 'Armenia', 'Belice', '2014-07-03', '10:00:00.000000', '05:00:00.000000', 1840.6),
(18, 'aerolinea_C', 'Bermudas', 'Bangladesh', '2014-07-03', '11:00:00.000000', '02:00:00.000000', 2759.5),
(19, 'aerolinea_D', 'Bélgica', 'Armenia', '2014-07-03', '838:59:59.000000', '03:00:00.000000', 2759.2),
(20, 'aerolinea_F', 'Belice', 'Bangladesh', '2014-07-03', '03:00:00.000000', '06:00:00.000000', 1759.2),
(21, 'aerolinea_G', 'Bangladesh', 'Austria', '2014-07-04', '08:00:00.000000', '01:00:00.000000', 1569.2),
(22, 'aerolinea_H', 'Australia', 'Bangladesh', '2014-07-04', '09:00:00.000000', '03:00:00.000000', 1840.4),
(23, 'aerolinea_I', 'Bolivia', 'Australia', '2014-07-04', '06:00:00.000000', '08:00:00.000000', 2759.3),
(24, 'Aerolinea_B', 'Bielorrusia', 'Belice', '2014-07-04', '03:00:00.000000', '04:00:00.000000', 1759.3),
(25, 'aerolinea_A', 'Bolivia', 'Bermudas', '2014-07-04', '07:00:00.000000', '05:00:00.000000', 1569.3),
(26, 'Aerolinea_B', 'Austria', 'Bélgica', '2014-07-04', '07:00:00.000000', '02:00:00.000000', 1840.5),
(27, 'aerolinea_C', 'Austria', 'Bielorrusia', '2014-07-01', '08:00:00.000000', '03:00:00.000000', 2759.4),
(28, 'aerolinea_D', 'Bermudas', 'Austria', '2014-07-01', '09:00:00.000000', '06:00:00.000000', 1759.4),
(29, 'aerolinea_F', 'Belice', 'Austria', '2014-07-01', '10:00:00.000000', '01:00:00.000000', 1569.4),
(30, 'aerolinea_G', 'Bielorrusia', 'Bolivia', '2014-07-01', '11:00:00.000000', '03:00:00.000000', 1840.6),
(31, 'aerolinea_H', 'Bélgica', 'Bangladesh', '2014-07-01', '838:59:59.000000', '08:00:00.000000', 2759.5),
(32, 'aerolinea_I', 'Austria', 'Bélgica', '2014-07-01', '03:00:00.000000', '04:00:00.000000', 2759.2),
(33, 'Aerolinea_B', 'Belice', 'Austria', '2014-07-02', '08:00:00.000000', '05:00:00.000000', 1759.2),
(34, 'aerolinea_A', 'Bielorrusia', 'Belice', '2014-07-02', '09:00:00.000000', '02:00:00.000000', 1569.2),
(35, 'Aerolinea_B', 'Bolivia', 'Bielorrusia', '2014-07-02', '06:00:00.000000', '03:00:00.000000', 1840.4),
(36, 'aerolinea_C', 'Bielorrusia', 'Bosnia', '2014-07-02', '08:00:00.000000', '06:00:00.000000', 2759.3),
(37, 'aerolinea_D', 'Bielorrusia', 'Belice', '2014-07-03', '09:00:00.000000', '01:00:00.000000', 1759.3),
(38, 'aerolinea_F', 'Botswana', 'Bolivia', '2014-07-03', '10:00:00.000000', '03:00:00.000000', 1569.3),
(39, 'aerolinea_G', 'Belice', 'Bielorrusia', '2014-07-03', '11:00:00.000000', '08:00:00.000000', 1840.5),
(40, 'aerolinea_H', 'Bosnia', 'Bermudas', '2014-07-03', '838:59:59.000000', '04:00:00.000000', 2759.4),
(41, 'aerolinea_I', 'Botswana', '', '2014-07-03', '03:00:00.000000', '05:00:00.000000', 1759.4),
(42, 'Aerolinea_B', 'Bermudas', 'Bielorrusia', '2014-07-04', '08:00:00.000000', '02:00:00.000000', 1569.4),
(43, 'aerolinea_A', 'Brasil', 'Bosnia', '2014-07-04', '09:00:00.000000', '03:00:00.000000', 1840.6),
(44, 'Aerolinea_B', 'Bielorrusia', 'Bermudas', '2014-07-04', '06:00:00.000000', '06:00:00.000000', 2759.5),
(45, 'aerolinea_C', 'Brasil', 'Bolivia', '2014-07-04', '03:00:00.000000', '01:00:00.000000', 2759.2),
(46, 'aerolinea_D', 'Bosnia', 'Botswana', '2014-07-04', '07:00:00.000000', '03:00:00.000000', 1759.2),
(47, 'aerolinea_F', 'Bolivia', 'Brasil', '2014-07-04', '07:00:00.000000', '08:00:00.000000', 1569.2),
(48, 'aerolinea_G', 'Brunei', 'Bosnia', '2014-07-01', '08:00:00.000000', '04:00:00.000000', 1840.4),
(49, 'aerolinea_H', 'Bolivia', 'Botswana', '2014-07-01', '09:00:00.000000', '05:00:00.000000', 2759.3),
(50, 'aerolinea_I', 'Brunei', 'Bolivia', '2014-07-01', '10:00:00.000000', '02:00:00.000000', 1759.3),
(51, 'Aerolinea_B', 'Brunei', 'Brasil', '2014-07-01', '11:00:00.000000', '03:00:00.000000', 1569.3),
(52, 'aerolinea_A', 'Bolivia', 'Botswana', '2014-07-01', '838:59:59.000000', '06:00:00.000000', 1840.5),
(53, 'Aerolinea_B', 'Bosnia', 'Bolivia', '2014-07-01', '03:00:00.000000', '01:00:00.000000', 2759.4),
(54, 'aerolinea_C', 'Botswana', 'Brunei', '2014-07-02', '08:00:00.000000', '03:00:00.000000', 1759.4),
(55, 'aerolinea_D', 'Brasil', 'Bosnia', '2014-07-02', '09:00:00.000000', '08:00:00.000000', 1569.4),
(56, 'aerolinea_F', 'Bolivia', 'Botswana', '2014-07-02', '06:00:00.000000', '04:00:00.000000', 1840.6),
(57, 'aerolinea_G', 'Bosnia', 'Brasil', '2014-07-02', '08:00:00.000000', '05:00:00.000000', 2759.5),
(58, 'aerolinea_H', 'Brasil', 'Botswana', '2014-07-03', '09:00:00.000000', '02:00:00.000000', 2759.2),
(59, 'aerolinea_I', 'Brunei', 'Brasil', '2014-07-03', '10:00:00.000000', '03:00:00.000000', 1759.2),
(60, 'Aerolinea_B', 'Burundi', 'Brunei', '2014-07-03', '11:00:00.000000', '06:00:00.000000', 1569.2),
(61, 'aerolinea_A', 'Austria', 'Burundi', '2014-07-03', '838:59:59.000000', '01:00:00.000000', 1840.4),
(62, 'Aerolinea_B', 'Brasil', 'Brunei', '2014-07-03', '03:00:00.000000', '08:00:00.000000', 2759.3),
(63, 'aerolinea_C', 'Austria', 'Belice', '2014-07-04', '08:00:00.000000', '04:00:00.000000', 1759.3),
(64, 'aerolinea_D', 'Austria', 'Burundi', '2014-07-04', '09:00:00.000000', '05:00:00.000000', 1569.3),
(65, 'aerolinea_F', 'Belice', 'Austria', '2014-07-04', '06:00:00.000000', '02:00:00.000000', 1840.5),
(66, 'aerolinea_G', 'Bután', 'Belice', '2014-07-04', '03:00:00.000000', '03:00:00.000000', 2759.4),
(67, 'aerolinea_H', 'Austria', 'Bután', '2014-07-04', '07:00:00.000000', '06:00:00.000000', 1759.4),
(68, 'aerolinea_I', 'Burundi', 'Austria', '2014-07-04', '07:00:00.000000', '01:00:00.000000', 1569.4),
(69, 'Aerolinea_B', 'Belice', 'Bután', '2014-07-01', '08:00:00.000000', '03:00:00.000000', 1840.6),
(70, 'aerolinea_A', 'Burundi', 'Bután', '2014-07-01', '09:00:00.000000', '08:00:00.000000', 2759.5),
(71, 'Aerolinea_B', 'Burundi', 'Bolivia', '2014-07-01', '10:00:00.000000', '04:00:00.000000', 2759.2),
(72, 'aerolinea_C', 'Bután', 'Burundi', '2014-07-01', '11:00:00.000000', '05:00:00.000000', 1759.2),
(73, 'aerolinea_D', 'Bolivia', 'Burundi', '2014-07-01', '838:59:59.000000', '02:00:00.000000', 1569.2),
(74, 'aerolinea_F', 'Bután', 'Bolivia', '2014-07-01', '03:00:00.000000', '03:00:00.000000', 1840.4),
(75, 'aerolinea_G', 'Burundi', 'Bután', '2014-07-02', '08:00:00.000000', '06:00:00.000000', 2759.3),
(76, 'aerolinea_C', 'Afganistán', 'Albania', '2014-07-01', '08:00:00.000000', '03:00:00.000000', 152.85),
(77, 'aerolinea_D', 'Albania', 'Alemania', '2014-07-01', '12:00:00.000000', '03:00:00.000000', 1959.2),
(78, 'aerolinea_B', 'Albania', 'Alemania', '2014-07-01', '07:00:00.000000', '03:00:00.000000', 2759.2),
(79, 'aerolinea_A', 'Albania', 'Alemania', '2014-07-06', '09:00:00.000000', '03:00:00.000000', 2579.2),
(80, 'Aerolinea_B', 'Albania', 'Afganistán', '2014-07-02', '08:00:00.000000', '03:00:03.000000', 1140.4),
(81, 'aerolinea_C', 'Afganistán', 'Albania', '2014-07-01', '08:00:00.000000', '03:00:00.000000', 152.85),
(82, 'aerolinea_D', 'Albania', 'Alemania', '2014-07-01', '12:00:00.000000', '03:00:00.000000', 1959.2),
(83, 'aerolinea_B', 'Albania', 'Alemania', '2014-07-01', '07:00:00.000000', '03:00:00.000000', 2759.2),
(84, 'aerolinea_A', 'Albania', 'Alemania', '2014-07-06', '09:00:00.000000', '03:00:00.000000', 2579.2),
(85, 'Aerolinea_B', 'Albania', 'Afganistán', '2014-07-02', '08:00:00.000000', '03:00:03.000000', 1140.4),
(86, 'aerolinea_B', 'Alemania', 'Albania', '2014-07-07', '08:00:00.000000', '03:00:00.000000', 1522.85),
(87, 'aerolinea_A', 'Albania', 'Afganistán', '2014-07-08', '12:00:00.000000', '03:00:00.000000', 2959.2),
(88, 'Aerolinea_B', 'Albania', 'Afganistán', '2014-07-09', '07:00:00.000000', '03:00:00.000000', 1009.2),
(89, 'Aerolinea_V', 'Alemania', 'Afganistán', '2014-07-09', '07:00:00.000000', '03:00:00.000000', 9009.2),
(90, '', 'Angola', 'Afganistán', '2014-07-01', '03:00:00.000000', '03:00:00.000000', 2749.9),
(91, 'aerolinea_Y', 'Afganistán', 'Angola', '2014-07-04', '08:00:55.000000', '03:22:11.000000', 0),
(92, '', 'Angola', 'Afganistán', '2014-07-01', '03:00:00.000000', '03:00:00.000000', 2749.9),
(93, 'aerolinea_Y', 'Afganistán', 'Angola', '2014-07-04', '08:00:55.000000', '03:22:11.000000', 43435),
(94, '', 'Angola', 'Afganistán', '2014-07-01', '03:00:00.000000', '03:00:00.000000', 2749.9),
(95, 'aerolinea_Y', 'Afganistán', 'Angola', '2014-07-04', '08:00:55.000000', '03:22:11.000000', 4435.89),
(96, 'Aerolinea_B', 'Albania', 'Afganistán', '2014-07-09', '07:00:00.000000', '03:00:00.000000', 1009.2),
(97, 'Aerolinea_V', 'Alemania', 'Afganistán', '2014-07-09', '07:00:00.000000', '03:00:00.000000', 9009.2),
(98, 'aerolinea_B', 'Angola', 'Albania', '2014-07-01', '07:00:00.000000', '03:00:00.000000', 1522.86),
(99, 'aerolinea_A', 'Albania', 'Angola', '2014-07-06', '09:00:00.000000', '03:00:00.000000', 2959.3),
(100, 'Aerolinea_B', 'Angola', 'Afganistán', '2014-07-02', '08:00:00.000000', '03:00:03.000000', 1009.3),
(101, 'aerolinea_B', 'Afganistán', 'Angola', '2014-07-07', '08:00:00.000000', '03:00:00.000000', 9009.3),
(102, 'aerolinea_A', 'Alemania', 'Angola', '2014-07-08', '12:00:00.000000', '03:00:00.000000', 1522.87),
(103, 'Aerolinea_B', 'Angola', 'Alemania', '2014-07-09', '07:00:00.000000', '03:00:00.000000', 2959.4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
