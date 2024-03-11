-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 11, 2024 at 04:01 PM
-- Server version: 8.3.0
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crime_manage`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ID` bigint NOT NULL,
  `Username` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ID`, `Username`, `password`) VALUES
(1, 'Admin1', 'admin123'),
(2, 'Admin2', 'admin321');

-- --------------------------------------------------------

--
-- Table structure for table `criminals`
--

CREATE TABLE `criminals` (
  `ID` bigint NOT NULL,
  `Name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `Age` int NOT NULL,
  `Type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Address` varchar(300) COLLATE utf8mb4_general_ci NOT NULL,
  `Crime_Scene` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `Gender` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `NRC` varchar(30) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `criminals`
--

INSERT INTO `criminals` (`ID`, `Name`, `Age`, `Type`, `Address`, `Crime_Scene`, `Gender`, `NRC`) VALUES
(1, 'Ma Maw Maw Than', 38, 'Drug', 'Ma Yan Chaung Village,Kyite Hto Township,Mon State\r\n', 'while travelling  from Yangon to Kaw Thaung via express', 'F', '10/KHT(N)012563'),
(2, 'Mg Chan Myae Aung', 55, 'Robbery', 'Theikpan Road,68th & 69th Street,Mandalay\r\n', 'Between 27th and 29th steet of Kan Kaut Quarter,Chan Aye Thar San Township,Mandalay Region', 'M', '9/PKK(N)384753'),
(3, 'Mg Lu Ka', 34, 'Smuggling Drug', 'Naw-Phar Village,Kyine-Tone Township,Shan State\r\n', 'while Driving near Naw-Phar Village,Kyine-Tone Township,Shan State', 'M', '13/KT(N)395540'),
(4, 'Daw San San Htay', 66, 'Smuggling Drug', 'Chan Mya Thar Si Township,Mandalay Region\r\n', 'No.67,15th Street,Chan Mya Thar Si Township,Mandalay Region\r\n', 'F', '5/SG(N)475680'),
(5, 'U Kyaw Zay Ya', 29, 'Smuggling Drug', 'Chan Mya Thar Si Township,Mandalay Region\r\n', 'No.67,15th Street,Chan Mya Thar Si Township,Mandalay Region\r\n\r\n', 'M', '9/CMT(N)385394'),
(6, 'U Toe Wai Aung', 50, 'Murder', 'Dahma Darna Street,Tamwe Township,Yangon City,Yangon Region\r\n', 'Dahma Darna Street,Tamwe Township,Yangon City,Yangon Region\r\n', 'M', '12/TM(N)358658'),
(7, 'U Hla Htay ', 60, 'Robbery/Murder', 'Myaung Gyi Street,Puzun Taung Township,Yangon City,Yangon Region\r\n', 'No.60,Kan Nar Street,Botahtaug township,Yangon City,Yangon Region\r\n', 'M', '12/PZT(N)003067'),
(8, 'U Zaw Lin Htun', 38, 'Murder', 'Taungyi Village,Indaw Township,Katha District,Sagaing Region\r\n', 'Mandalay-Monywa road,Pabedan Ward,Sagaing Region\r\n', 'M', '5/AD(N)375937'),
(9, 'Mg Ye Min Htun', 45, 'Smuggling Weapons', 'Nyang Pin Gate Village,Dee Maw So Township,Kayar State\r\n', 'Yay Thar Shay Township,Bago Region\r\n', 'M', '2/DMS(N)096943'),
(10, 'U Ko Ko Myint', 69, 'Murder', 'Utaya Thiri Township,Nay Pyi Taw \r\n', 'Myo Ma Market,Pwe Yone tan City,Nay Pyi Taw\r\n', 'M', '12/CMT(N)00763');

-- --------------------------------------------------------

--
-- Table structure for table `operator`
--

CREATE TABLE `operator` (
  `Name` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `Username` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Confirmed_Password` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `Email_Address` varchar(200) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `operator`
--

INSERT INTO `operator` (`Name`, `Username`, `Password`, `Confirmed_Password`, `Email_Address`) VALUES
('Group5', 'AChawLyMyr', 'Admin123', 'Admin123', 'Kothureinlin2003@gmail.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
