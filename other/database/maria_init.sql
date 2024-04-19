-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/

-- Server version: 10.6.17-MariaDB-log
-- PHP Version: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `siimon`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `user_name`, `password`, `created`, `deleted`) VALUES
(1, '?????????????', '?????????????????', '2023-01-09 19:12:20', 0);


-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `correct` tinyint(1) NOT NULL DEFAULT 0,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `disabled` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `question_id`, `answer`, `correct`, `deleted`, `disabled`) VALUES
(1, 1, 'olema', 0, 0, 0),
(2, 1, 'oleb', 0, 0, 0),
(3, 1, 'on', 1, 0, 0),
(4, 1, 'olla', 0, 0, 0),
(5, 2, 'ehitajaks', 0, 0, 0),
(6, 2, 'ehitajale', 0, 0, 0),
(7, 2, 'ehitajana', 1, 0, 0),
(8, 2, 'ehitaja', 0, 0, 0),
(9, 3, 'autoprandus', 0, 0, 0),
(10, 3, 'kino', 1, 0, 0),
(11, 3, 'teater', 0, 0, 0),
(12, 3, 'apteek', 0, 0, 0),
(13, 4, '8  kuud', 0, 0, 0),
(14, 4, '8 tundi', 1, 0, 0),
(15, 4, '8 nädalat', 0, 0, 0),
(16, 4, '8 päeva', 0, 0, 0),
(17, 5, 'Venemaalt', 1, 0, 0),
(18, 5, 'Venemaa', 0, 0, 0),
(19, 5, 'Venemaas', 0, 0, 0),
(20, 5, 'Venemaast', 0, 0, 0),
(21, 6, 'pikk', 0, 0, 0),
(22, 6, 'lai', 0, 0, 0),
(23, 6, 'lühike', 0, 0, 0),
(24, 6, 'suur', 1, 0, 0),
(25, 7, 'trammis', 0, 0, 0),
(26, 7, 'trammiga', 1, 0, 0),
(27, 7, 'trammilt', 0, 0, 0),
(28, 7, 'trammi pealt', 0, 0, 0),
(29, 8, 'armastad tantsida', 1, 0, 0),
(30, 8, 'armastad tantsima', 0, 0, 0),
(31, 8, 'armastab tantsida', 0, 0, 0),
(32, 8, 'armast tantsimaas', 0, 0, 0),
(33, 9, 'kolm', 0, 0, 0),
(34, 9, 'kolme', 0, 0, 0),
(35, 9, 'kolmekesi', 1, 0, 0),
(36, 9, 'kolmanda', 0, 0, 0),
(37, 10, 'kohvikusse', 0, 0, 0),
(38, 10, 'kodus', 1, 0, 0),
(39, 10, 'koju', 0, 0, 0),
(40, 10, 'kohviku', 0, 0, 0),
(41, 11, 'esimese märts', 0, 0, 0),
(42, 11, 'esimene märtsi', 0, 0, 0),
(43, 11, 'esimese märtsi', 0, 0, 0),
(44, 11, 'esimene märts', 1, 0, 0),
(45, 12, 'käid iga päev tööle', 0, 0, 0),
(46, 12, 'lähed iga päev töölt', 0, 0, 0),
(47, 12, 'lähed iga päev tööle ', 1, 0, 0),
(48, 12, 'lähed iga päev töösse', 0, 0, 0),
(49, 13, 'kasutati', 0, 0, 0),
(50, 13, 'kasutatakse', 1, 0, 0),
(51, 13, 'kasutavad', 0, 0, 0),
(52, 13, 'kasutasid', 0, 0, 0),
(53, 14, 'söödi', 0, 0, 0),
(54, 14, 'söövad', 0, 0, 0),
(55, 14, 'on söödud', 1, 0, 0),
(56, 14, 'söön', 0, 0, 0),
(57, 15, 'juulis', 1, 0, 0),
(58, 15, 'juulist', 0, 0, 0),
(59, 15, 'juulil', 0, 0, 0),
(60, 15, 'juulilt', 0, 0, 0),
(61, 16, 'spordikaupade', 0, 0, 0),
(62, 16, 'mänguasjade', 0, 0, 0),
(63, 16, 'kodumasinate', 1, 0, 0),
(64, 16, 'fotokaupade', 0, 0, 0),
(65, 17, 'Kes', 0, 0, 0),
(66, 17, 'Kas', 0, 0, 0),
(67, 17, 'Kelle', 0, 0, 0),
(68, 17, 'Kus', 1, 0, 0),
(69, 18, 'elas', 0, 0, 0),
(70, 18, 'elavad', 0, 0, 0),
(71, 18, 'elatakse', 0, 0, 0),
(72, 18, 'elasid', 1, 0, 0),
(73, 19, 'tervislik', 0, 0, 0),
(74, 19, 'kasulik', 1, 0, 0),
(75, 19, 'kahjulik', 0, 0, 0),
(76, 19, 'kasukas', 0, 0, 0),
(77, 20, 'ees', 1, 0, 0),
(78, 20, 'ette', 0, 0, 0),
(79, 20, 'eest', 0, 0, 0),
(80, 20, 'eel', 0, 0, 0),
(81, 21, 'peal', 0, 0, 0),
(82, 21, 'edasi', 0, 0, 0),
(83, 21, 'mööda', 1, 0, 0),
(84, 21, 'kaasa', 0, 0, 0),
(85, 22, 'helistada', 0, 0, 0),
(86, 22, 'öelda', 0, 0, 0),
(87, 22, 'ütelda', 0, 0, 0),
(88, 22, 'rääkida', 1, 0, 0),
(89, 23, 'sees', 0, 0, 0),
(90, 23, 'ümber', 0, 0, 0),
(91, 23, 'peal', 1, 0, 0),
(92, 23, 'kohal', 0, 0, 0),
(93, 24, 'pika', 0, 0, 0),
(94, 24, 'pikam', 0, 0, 0),
(95, 24, 'pikem', 1, 0, 0),
(96, 24, 'pikad', 0, 0, 0),
(97, 25, 'tööle', 0, 0, 0),
(98, 25, 'töölt', 1, 0, 0),
(99, 25, 'töös', 0, 0, 0),
(100, 25, 'tööst', 0, 0, 0),
(101, 26, 'võivad juua', 0, 0, 0),
(102, 26, 'võime juua', 1, 0, 0),
(103, 26, 'võime jooma', 0, 0, 0),
(104, 26, 'võivad jooma', 0, 0, 0),
(105, 27, 'toadesse', 0, 0, 0),
(106, 27, 'toasse', 0, 0, 0),
(107, 27, 'tubadesse', 1, 0, 0),
(108, 27, 'toas', 0, 0, 0),
(109, 28, 'jooksed', 0, 0, 0),
(110, 28, 'jookske', 0, 0, 0),
(111, 28, 'jookse', 1, 0, 0),
(112, 28, 'jookseb', 0, 0, 0),
(113, 29, 'Kellel', 1, 0, 0),
(114, 29, 'Kelles', 0, 0, 0),
(115, 29, 'Kellest', 0, 0, 0),
(116, 29, 'Kelleta', 0, 0, 0),
(117, 30, 'Kus', 0, 0, 0),
(118, 30, 'Kuhu', 1, 0, 0),
(119, 30, 'Kust', 0, 0, 0),
(120, 30, 'Mida', 0, 0, 0),
(121, 31, 'majja', 1, 0, 0),
(122, 31, 'majas', 0, 0, 0),
(123, 31, 'majast', 0, 0, 0),
(124, 31, 'majale', 0, 0, 0),
(125, 32, 'rahvuslill', 0, 0, 0),
(126, 32, 'rahvuslind', 1, 0, 0),
(127, 32, 'rahvalaul', 0, 0, 0),
(128, 32, 'rahvalind', 0, 0, 0),
(129, 33, 'uusi voodisid', 0, 0, 0),
(130, 33, 'uusi voodid', 0, 0, 0),
(131, 33, 'uued voodid', 0, 0, 0),
(132, 33, 'uusi voodeid', 1, 0, 0),
(133, 34, 'suurem', 1, 0, 0),
(134, 34, 'suurim', 0, 0, 0),
(135, 34, 'suuram', 0, 0, 0),
(136, 34, 'kõige suurem', 0, 0, 0),
(137, 35, 'joonud', 0, 0, 0),
(138, 35, 'jõin', 0, 0, 0),
(139, 35, 'joodud', 1, 0, 0),
(140, 35, 'juua', 0, 0, 0),
(141, 36, 'lugemata', 1, 0, 0),
(142, 36, 'loevad', 0, 0, 0),
(143, 36, 'loetakse', 0, 0, 0),
(144, 36, 'lugesin', 0, 0, 0),
(145, 37, 'soovitad tellida', 0, 0, 0),
(146, 37, 'soovitate tellida', 1, 0, 0),
(147, 37, 'soovitavad tellida', 0, 0, 0),
(148, 37, 'soovitate tellima', 0, 0, 0),
(149, 38, 'uuega kleidiga', 0, 0, 0),
(150, 38, 'uue kleidiga', 1, 0, 0),
(151, 38, 'uueta kleidita', 0, 0, 0),
(152, 38, 'uued kleidid', 0, 0, 0),
(153, 39, 'lumi', 1, 0, 0),
(154, 39, 'vesi', 0, 0, 0),
(155, 39, 'jõgi', 0, 0, 0),
(156, 39, 'vihm', 0, 0, 0),
(157, 40, 'kingitust', 0, 0, 0),
(158, 40, 'kingituse', 1, 0, 0),
(159, 40, 'kingitus', 0, 0, 0),
(160, 40, 'kingituste', 0, 0, 0),
(161, 41, 'käisid', 0, 0, 0),
(162, 41, 'käivad', 0, 0, 0),
(163, 41, 'käiakse', 0, 0, 0),
(164, 41, 'käidi', 1, 0, 0),
(165, 42, 'minule', 0, 0, 0),
(166, 42, 'mind', 1, 0, 0),
(167, 42, 'minu', 0, 0, 0),
(168, 42, 'mulle', 0, 0, 0),
(169, 43, 'korjama', 0, 0, 0),
(170, 43, 'korjasime', 0, 0, 0),
(171, 43, 'korjamas', 1, 0, 0),
(172, 43, 'korjame', 0, 0, 0),
(173, 44, 'olid läinud', 1, 0, 0),
(174, 44, 'on läinud', 0, 0, 0),
(175, 44, 'läksid', 0, 0, 0),
(176, 44, 'lähevad', 0, 0, 0),
(177, 45, 'sportlast', 0, 0, 0),
(178, 45, 'sportlane', 1, 0, 0),
(179, 45, 'sportlase', 0, 0, 0),
(180, 45, 'sportlasi', 0, 0, 0),
(181, 46, 'graafikut', 0, 0, 0),
(182, 46, 'graafiku', 1, 0, 0),
(183, 46, 'graafikute', 0, 0, 0),
(184, 46, 'graafikuid', 0, 0, 0),
(185, 47, 'müüvad', 0, 0, 0),
(186, 47, 'müüsid', 0, 0, 0),
(187, 47, 'müüakse', 1, 0, 0),
(188, 47, 'müünud', 0, 0, 0),
(189, 48, 'seitsmes', 0, 0, 0),
(190, 48, 'seitsmendas', 1, 0, 0),
(191, 48, 'seitsme', 0, 0, 0),
(192, 48, 'seitset', 0, 0, 0),
(193, 49, 'olid', 0, 0, 0),
(194, 49, 'on', 0, 0, 0),
(195, 49, 'peavad', 1, 0, 0),
(196, 49, 'hoiavad', 0, 0, 0),
(197, 50, 'joon', 0, 0, 0),
(198, 50, 'jõin', 0, 0, 0),
(199, 50, 'joonud', 1, 0, 0),
(200, 50, 'joodud', 0, 0, 0),
(201, 51, 'mul on palav', 0, 0, 0),
(202, 51, 'mul on ükskõik', 1, 0, 0),
(203, 51, 'mul on jahedavõitu', 0, 0, 0),
(204, 51, 'mul on kasukas seljas', 0, 0, 0),
(205, 52, 'juurde', 0, 0, 0),
(206, 52, 'juurest', 0, 0, 0),
(207, 52, 'juures', 1, 0, 0),
(208, 52, 'juurel', 0, 0, 0),
(209, 53, 'ei näe', 0, 0, 0),
(210, 53, 'ei saa rääkida', 0, 0, 0),
(211, 53, 'ei oska laulda', 0, 0, 0),
(212, 53, 'ei kuule', 1, 0, 0),
(213, 54, 'leitud', 0, 0, 0),
(214, 54, 'kukkunud', 0, 0, 0),
(215, 54, 'hukkunud', 0, 0, 0),
(216, 54, 'kadunud', 1, 0, 0),
(217, 55, 'rahulikult', 0, 0, 0),
(218, 55, 'rahulik', 1, 0, 0),
(219, 55, 'rahulikuna', 0, 0, 0),
(220, 55, 'rahulikus', 0, 0, 0),
(221, 56, 'töötanud', 1, 0, 0),
(222, 56, 'töötama', 0, 0, 0),
(223, 56, 'töötada', 0, 0, 0),
(224, 56, 'töötatud', 0, 0, 0),
(225, 57, 'nendel', 0, 0, 0),
(226, 57, 'nendelt ', 0, 0, 0),
(227, 57, 'neid', 0, 0, 0),
(228, 57, 'neile', 1, 0, 0),
(229, 58, 'kirjutamine', 0, 0, 0),
(230, 58, 'kirjutamisest', 1, 0, 0),
(231, 58, 'kirjutada', 0, 0, 0),
(232, 58, 'kirjutanud', 0, 0, 0),
(233, 59, 'sinule', 0, 0, 0),
(234, 59, 'sinu', 0, 0, 0),
(235, 59, 'sinuga', 0, 0, 0),
(236, 59, 'sinust', 1, 0, 0),
(237, 60, 'ette', 0, 0, 0),
(238, 60, 'eest', 1, 0, 0),
(239, 60, 'ees', 0, 0, 0),
(240, 60, 'eel', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `part_id` int(11) NOT NULL,
  `lang_level_id` int(11) DEFAULT NULL,
  `min_result_percent` float(5,2) NOT NULL,
  `max_result_percent` float(5,2) NOT NULL,
  `next_part` tinyint(1) NOT NULL DEFAULT 0,
  `deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`id`, `part_id`, `lang_level_id`, `min_result_percent`, `max_result_percent`, `next_part`, `deleted`) VALUES
(1, 1, 1, 0.00, 39.00, 0, 0),
(2, 1, 2, 40.00, 79.00, 0, 0),
(3, 1, 21, 80.00, 100.00, 1, 0),
(4, 2, 3, 0.00, 39.00, 0, 0),
(5, 2, 4, 40.00, 79.00, 0, 0),
(6, 2, 22, 80.00, 100.00, 1, 0),
(7, 3, 22, 0.00, 79.00, 0, 0),
(8, 3, 23, 80.00, 100.00, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `lang_level`
--

CREATE TABLE `lang_level` (
  `id` int(11) NOT NULL,
  `value` varchar(25) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `lang_level`
--

INSERT INTO `lang_level` (`id`, `value`, `deleted`) VALUES
(1, 'A1', 0),
(2, 'A2', 0),
(3, 'B1', 0),
(4, 'B2', 0),
(5, 'C1', 0),
(6, 'C2', 0),
(7, 'general', 0),
(9, 'A1.1', 0),
(10, 'A1.2', 0),
(11, 'A2.1', 0),
(12, 'A2.2', 0),
(13, 'B1.1', 0),
(14, 'B1.2', 0),
(15, 'B2.1', 0),
(16, 'B2.2', 0),
(17, 'C1.1', 0),
(18, 'C1.2', 0),
(19, 'C2.1', 0),
(20, 'C2.2', 0),
(21, 'A', 0),
(22, 'B', 0),
(23, 'C', 0);

-- --------------------------------------------------------

--
-- Table structure for table `part`
--

CREATE TABLE `part` (
  `id` int(11) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `max_time` time DEFAULT NULL,
  `step` int(11) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `disabled` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `part`
--

INSERT INTO `part` (`id`, `description`, `max_time`, `step`, `deleted`, `disabled`) VALUES
(1, 'A-tasemeosa.', '00:10:00', 1, 0, 0),
(2, 'B-tasemeosa.', '00:15:00', 2, 0, 0),
(3, 'C-tasemeosa', '00:05:00', 3, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `part_id` int(11) NOT NULL,
  `max_time` time DEFAULT NULL,
  `question` varchar(25000) NOT NULL,
  `points` float(5,2) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `disabled` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `part_id`, `max_time`, `question`, `points`, `deleted`, `disabled`) VALUES
(1, 1, NULL, 'Minu nimi ___ Mart.', 1.00, 0, 0),
(2, 1, NULL, 'Ma töötan ___ .', 1.00, 0, 0),
(3, 1, NULL, 'Koht, kus saab vaadata filme.', 1.00, 0, 0),
(4, 1, NULL, 'Kui pikk on teie tööpäev?', 1.00, 0, 0),
(5, 1, NULL, 'Kust te pärit olete?', 1.00, 0, 0),
(6, 1, NULL, 'Missugune on teie perekond?', 1.00, 0, 0),
(7, 1, NULL, 'Millega te sõidate tööle?', 1.00, 0, 0),
(8, 1, NULL, 'Kas sa ___ ?', 1.00, 0, 0),
(9, 1, NULL, 'Mitmekesi te selles majas elate?', 1.00, 0, 0),
(10, 1, NULL, 'Kus te sööte lõunat? Me sööme lõunat ___', 1.00, 0, 0),
(11, 1, NULL, 'Mis kuupäev täna on? Täna on ___ .', 1.00, 0, 0),
(12, 1, NULL, 'Kas sa ___ ?', 1.00, 0, 0),
(13, 1, NULL, 'Arvutit ___ tänapäeval palju.', 1.00, 0, 0),
(14, 1, NULL, 'Õunad ___ .', 1.00, 0, 0),
(15, 1, NULL, 'Meil on puhkus ___ .', 1.00, 0, 0),
(16, 1, NULL, 'Tahan osta tolmuimejat. Kus on ___ kauplus?', 1.00, 0, 0),
(17, 1, NULL, '___ elab teie õde?', 1.00, 0, 0),
(18, 1, NULL, 'Eelmisel aastal nad ___ Paides.', 1.00, 0, 0),
(19, 1, NULL, 'Söö rohkem puu- ja juurvilja. See on ___ sinu tervisele.', 1.00, 0, 0),
(20, 1, NULL, 'Maja ___ on kauplus.', 1.00, 0, 0),
(21, 2, NULL, 'Me sõidame bussiga ___ teed.', 1.00, 0, 0),
(22, 2, NULL, 'Kas ma saaksin ___ härra Tammega?', 1.00, 0, 0),
(23, 2, NULL, 'Tort on taldriku ___ .', 1.00, 0, 0),
(24, 2, NULL, 'Õde on ___ kui vend.', 1.00, 0, 0),
(25, 2, NULL, 'Ta peab lahkuma ___ kell viis.', 1.00, 0, 0),
(26, 2, NULL, 'Sooja ilmaga me ___ limonaadi.', 1.00, 0, 0),
(27, 2, NULL, 'Turistid võivad minna oma ___ .', 1.00, 0, 0),
(28, 2, NULL, 'Ära ___ üle tee!', 1.00, 0, 0),
(29, 2, NULL, '___ on kõige rohkem raha?', 1.00, 0, 0),
(30, 2, NULL, '___ teevad linnud oma pesa?', 1.00, 0, 0),
(31, 2, NULL, 'Unustasin mütsi ___ .', 1.00, 0, 0),
(32, 2, NULL, 'Suitsupääsuke on Eesti ___ .', 1.00, 0, 0),
(33, 2, NULL, 'Haiglas oli palju ___ .', 1.00, 0, 0),
(34, 2, NULL, 'Rakvere on suur linn, aga Tartu on ___ .', 1.00, 0, 0),
(35, 2, NULL, 'Piim on juba ___ .', 1.00, 0, 0),
(36, 2, NULL, 'Läksin raamatut ___ tööle.', 1.00, 0, 0),
(37, 2, NULL, 'Mida te ___ ?', 1.00, 0, 0),
(38, 2, NULL, 'Läksin peole ___ .', 1.00, 0, 0),
(39, 2, NULL, 'Õues mäena, toas veena?', 1.00, 0, 0),
(40, 2, NULL, 'Ostsin emale ___ ja läksin kohe koju.', 1.00, 0, 0),
(41, 2, NULL, 'Vanasti ___ jala, nüüd sõidetakse autoga.', 1.00, 0, 0),
(42, 2, NULL, 'Aita ___ !', 1.00, 0, 0),
(43, 2, NULL, 'Käisime eile metsas maasikaid ___ .', 1.00, 0, 0),
(44, 2, NULL, 'Kui lapsed ___ , hakkasime tuba koristama.', 1.00, 0, 0),
(45, 2, NULL, 'Sellest poisist kasvas ___ .', 1.00, 0, 0),
(46, 2, NULL, 'Dispetšer koostas ___ eelmisel nädalal.', 1.00, 0, 0),
(47, 2, NULL, 'Kus ___ ilusaid lilli?', 1.00, 0, 0),
(48, 2, NULL, 'Ma istun ___ reas.', 1.00, 0, 0),
(49, 2, NULL, 'Minu vanavanemad ___ lambaid.', 1.00, 0, 0),
(50, 2, NULL, 'Eile ma ei ___ ühtegi tassi kohvi', 1.00, 0, 0),
(51, 3, NULL, '\"Mul ei ole sellest sooja ega külma\". See tähendab, et ___ .', 1.00, 0, 0),
(52, 3, NULL, 'Vanaema käis arsti ___ .', 1.00, 0, 0),
(53, 3, NULL, 'Kurt on inimene, kes ___ .', 1.00, 0, 0),
(54, 3, NULL, 'Ma ei leia oma rahakotti, see on ___ .', 1.00, 0, 0),
(55, 3, NULL, 'Täna oli bussis ___ .', 1.00, 0, 0),
(56, 3, NULL, 'Kas te olete õpetajana ___ ?', 1.00, 0, 0),
(57, 3, NULL, 'Klassijuhataja ütles ___ , et nad aitaksid teda.', 1.00, 0, 0),
(58, 3, NULL, 'Kas sa oled ___ väsinud?', 1.00, 0, 0),
(59, 3, NULL, 'Ta on targem kui sina. Ta on ___ targem.', 1.00, 0, 0),
(60, 3, NULL, 'Täname testi täitmise ___ !', 1.00, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `id` int(11) NOT NULL,
  `test_parts_id` int(11) NOT NULL,
  `lang_level_id` int(11) DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  `when_taken` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `result` varchar(255) NOT NULL,
  `answers` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`answers`)),
  `deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `title` varchar(500) NOT NULL,
  `description` varchar(25000) NOT NULL,
  `protected_key` varchar(255) DEFAULT NULL,
  `protected` tinyint(1) NOT NULL DEFAULT 1,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `disabled` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`id`, `title`, `description`, `protected_key`, `protected`, `deleted`, `disabled`) VALUES
(1, 'Eesti keele üldine tasemetest.', 'Test määramaks inimese keeletaset.', 'TEST', 1, 0, 0),
(2, 'Eesti keele üldine tasemetest.', 'Test määramaks inimese keeletaset.', 'TEST2', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `test_parts`
--

CREATE TABLE `test_parts` (
  `id` int(11) NOT NULL,
  `test_id` int(11) NOT NULL,
  `part_id` int(11) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `disabled` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `test_parts`
--

INSERT INTO `test_parts` (`id`, `test_id`, `part_id`, `deleted`, `disabled`) VALUES
(1, 1, 1, 0, 0),
(2, 1, 2, 0, 0),
(3, 1, 3, 0, 0),
(4, 2, 1, 0, 0),
(5, 2, 2, 0, 0),
(6, 2, 3, 0, 0);


--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `test_answers_test_questions` (`question_id`);

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`),
  ADD KEY `grade_lang_level` (`lang_level_id`),
  ADD KEY `grade_part` (`part_id`);

--
-- Indexes for table `lang_level`
--
ALTER TABLE `lang_level`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `part`
--
ALTER TABLE `part`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_part` (`part_id`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Result_person` (`person_id`),
  ADD KEY `result_lang_level` (`lang_level_id`),
  ADD KEY `result_test_parts` (`test_parts_id`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `test_parts`
--
ALTER TABLE `test_parts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `test_parts_part` (`part_id`),
  ADD KEY `test_parts_test` (`test_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=241;

--
-- AUTO_INCREMENT for table `grade`
--
ALTER TABLE `grade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `lang_level`
--
ALTER TABLE `lang_level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `part`
--
ALTER TABLE `part`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `test_parts`
--
ALTER TABLE `test_parts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `test_answers_test_questions` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`);

--
-- Constraints for table `grade`
--
ALTER TABLE `grade`
  ADD CONSTRAINT `grade_lang_level` FOREIGN KEY (`lang_level_id`) REFERENCES `lang_level` (`id`),
  ADD CONSTRAINT `grade_part` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`);

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_part` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`);

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `Result_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `result_lang_level` FOREIGN KEY (`lang_level_id`) REFERENCES `lang_level` (`id`),
  ADD CONSTRAINT `result_test_parts` FOREIGN KEY (`test_parts_id`) REFERENCES `test_parts` (`id`);

--
-- Constraints for table `test_parts`
--
ALTER TABLE `test_parts`
  ADD CONSTRAINT `test_parts_part` FOREIGN KEY (`part_id`) REFERENCES `part` (`id`),
  ADD CONSTRAINT `test_parts_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
