-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 02, 2024 at 02:47 PM
-- Server version: 10.4.32-MariaDB
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
-- Table structure for table `criminals`
--

CREATE TABLE `criminals` (
  `ID` bigint(20) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `Age` int(3) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Address` varchar(300) NOT NULL,
  `Date` date DEFAULT NULL,
  `Crime_Scene` varchar(150) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `NRC` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `criminals`
--

INSERT INTO `criminals` (`ID`, `Name`, `Age`, `Type`, `Address`, `Date`, `Crime_Scene`, `Gender`, `NRC`) VALUES
(1, 'Mg Maw Than', 33, 'Drug', 'Ma Yan Chaung Village,Kyite Hto Township,Mon State', '2020-12-01', 'While travelling  from Yangon to Kaw Thaung via express', 'Male', '10/KH(N)012563'),
(2, 'Ma Chan Myae Aung', 12, 'Robbery', 'Theikpan Road,68th & 69th Street,Mandalay Region', '2022-04-18', 'Between 27th and 29th steet of Kan Kaut Quarter,Chan Aye Thar San Township,Mandalay Region', 'Female', '9/PKK(N)384753'),
(3, 'Ma Lu Ka', 0, 'Smuggling Drug', 'Naw-Phar Village,Kyine-Tone Township,Shan State', '2000-03-13', 'While Driving near Naw-Phar Village,Kyine-Tone Township,Shan State', 'Male', '13/KT(N)395540'),
(4, 'Daw San San Htay', 0, 'Smuggling Drug', 'No.67,15th Street,Chan Mya Thar Si Township,Mandalay Region', '2001-03-13', 'No.67,15th Street,Chan Mya Thar Si Township,Mandalay Region', 'Female', '5/SG(N)475680'),
(5, 'U Kyaw Zay Ya', 0, 'Smuggling Drug', 'No.44,20th Street,Chan Mya Thar Si Township,Mandalay Region', '2010-04-08', 'No.67,15th Street,Chan Mya Thar Si Township,Mandalay Region', 'Male', '9/CMT(N)385394'),
(6, 'U Toe Wai Aung', 0, 'Murder', 'Kyaut Myaung Street,Tamwe Township,Yangon City,Yangon Region', '2003-05-14', 'Dahma Darna Street,Tamwe Township,Yangon City,Yangon Region', 'Male', '12/TM(N)358658'),
(7, 'U Hla Htay ', 0, 'Robbery/Murder', 'Myaung Gyi Street,Puzun Taung Township,Yangon City,Yangon Region\r\n', '1990-10-25', 'No.60,Kan Nar Street,Botahtaug township,Yangon City,Yangon Region', 'Male', '12/PZT(N)003067'),
(8, 'U Zaw Lin Htun', 0, 'Murder', 'Taungyi Village,Indaw Township,Katha District,Sagaing Region\r\n', '2024-02-14', 'Mandalay-Monywa road,Pabedan Ward,Sagaing Region', 'Male', '5/AD(N)375937'),
(9, 'Mg Ye Min Htun', 0, 'Smuggling Weapons', 'Nyang Pin Gate Village,Dee Maw So Township,Kayar State\r\n', '2005-10-23', 'Yay Thar Shay Township,Bago Region', 'Male', '2/DMS(N)096943'),
(10, 'U Ko Ko Myint', 0, 'Murder', 'Utaya Thiri Township,Nay Pyi Taw Union Territory', '2020-05-23', 'Myo Ma Market,Pwe Yone tan City,Nay Pyi Taw', 'Male', '12/CMT(N)00763'),
(11, 'U Aye Linn', 0, 'Murder', 'Pay Khone Village,Myo Thit Township,Magway Region', '2014-05-09', 'Pay Khone Village,Myo Thit Township,Magway Region', 'Male', '12/ML(N)3793476'),
(12, 'Daw Taung Nwe', 0, 'Murder', 'Aung Zarni Khone,Wun Tho Township,Kawlin District,Sagaing Region', '2000-03-12', 'Aung Zarni Khone,Wun Tho Township,Kawlin District,Sagaing Region', 'Female', '5/KWT(N)573054'),
(13, 'Mg Thiri Maung', 0, 'Murder/Robbery', 'No.225,29th Street(Upper block),Pabedan Township,Yangon City,Yangon Region', '2019-06-17', '26 Quarter,Dagon(South) Township,Yangon City,Yangon Region', 'Male', '12/PBD(N)000364');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Name` varchar(40) NOT NULL,
  `Username` varchar(40) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `Email_Address` varchar(200) NOT NULL,
  `Type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Name`, `Username`, `Password`, `Email_Address`, `Type`) VALUES
('Group5', 'AChawLyMyr', '$2a$10$wYOanpNMj0.kByDBu5hI6OK4P.ojwq3ymdHAFmmr.TLnGnu1ywEO.', 'Kothureinlin2003@gmail.com', 'Operator'),
('Admin', 'Admin1', '$2a$10$.Ty4lwdwj461EqarrC6sB.jgbWxHeTCp6JJnJwpOYJOkSiMU/w9qO', 'admin@gmail.com', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `criminals`
--
ALTER TABLE `criminals`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `criminals`
--
ALTER TABLE `criminals`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
