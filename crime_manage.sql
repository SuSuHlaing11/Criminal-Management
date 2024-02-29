-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 29, 2024 at 01:45 PM
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
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ID` bigint(20) NOT NULL,
  `Username` varchar(40) NOT NULL,
  `password` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ID`, `Username`, `password`) VALUES
(1, 'Admin1', '$2a$10$.Ty4lwdwj461EqarrC6sB.jgbWxHeTCp6JJnJwpOYJOkSiMU/w9qO'),
(2, 'Admin2', '$2a$10$l2y8uGrKjscZmQxrG8CCwefY5fMz1L5iU/vgQwvLSA359hKWe/oKi');

-- --------------------------------------------------------

--
-- Table structure for table `criminals`
--

CREATE TABLE `criminals` (
  `ID` bigint(20) NOT NULL,
  `Name` varchar(40) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Address` varchar(300) NOT NULL,
  `Crime_Scene` varchar(150) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `NRC` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `criminals`
--

INSERT INTO `criminals` (`ID`, `Name`, `Type`, `Address`, `Crime_Scene`, `Gender`, `NRC`) VALUES
(1, 'Mg Maw Than', 'Drug', 'Ma Yan Chaung Village,Kyite Hto Township,Mon State', 'While travelling  from Yangon to Kaw Thaung via express', 'Male', '10/KH(N)012563'),
(2, 'Ma Chan Myae Aung', 'Robbery', 'Theikpan Road,68th & 69th Street,Mandalay Region', 'Between 27th and 29th steet of Kan Kaut Quarter,Chan Aye Thar San Township,Mandalay Region', 'Female', '9/PKK(N)384753'),
(3, 'Ma Lu Ka', 'Smuggling Drug', 'Naw-Phar Village,Kyine-Tone Township,Shan State', 'While Driving near Naw-Phar Village,Kyine-Tone Township,Shan State', 'Male', '13/KT(N)395540'),
(4, 'Daw San San Htay', 'Smuggling Drug', 'No.67,15th Street,Chan Mya Thar Si Township,Mandalay Region', 'No.67,15th Street,Chan Mya Thar Si Township,Mandalay Region', 'Female', '5/SG(N)475680'),
(5, 'U Kyaw Zay Ya', 'Smuggling Drug', 'No.44,20th Street,Chan Mya Thar Si Township,Mandalay Region', 'No.67,15th Street,Chan Mya Thar Si Township,Mandalay Region', 'Male', '9/CMT(N)385394'),
(6, 'U Toe Wai Aung', 'Murder', 'Kyaut Myaung Street,Tamwe Township,Yangon City,Yangon Region', 'Dahma Darna Street,Tamwe Township,Yangon City,Yangon Region', 'Male', '12/TM(N)358658'),
(7, 'U Hla Htay ', 'Robbery/Murder', 'Myaung Gyi Street,Puzun Taung Township,Yangon City,Yangon Region\r\n', 'No.60,Kan Nar Street,Botahtaug township,Yangon City,Yangon Region', 'Male', '12/PZT(N)003067'),
(8, 'U Zaw Lin Htun', 'Murder', 'Taungyi Village,Indaw Township,Katha District,Sagaing Region\r\n', 'Mandalay-Monywa road,Pabedan Ward,Sagaing Region', 'Male', '5/AD(N)375937'),
(9, 'Mg Ye Min Htun', 'Smuggling Weapons', 'Nyang Pin Gate Village,Dee Maw So Township,Kayar State\r\n', 'Yay Thar Shay Township,Bago Region', 'Male', '2/DMS(N)096943'),
(10, 'U Ko Ko Myint', 'Murder', 'Utaya Thiri Township,Nay Pyi Taw Union Territory', 'Myo Ma Market,Pwe Yone tan City,Nay Pyi Taw', 'Male', '12/CMT(N)00763'),
(11, 'U Aye Linn', 'Murder', 'Pay Khone Village,Myo Thit Township,Magway Region', 'Pay Khone Village,Myo Thit Township,Magway Region', 'Male', '12/ML(N)3793476'),
(12, 'Daw Taung Nwe', 'Murder', 'Aung Zarni Khone,Wun Tho Township,Kawlin District,Sagaing Region', 'Aung Zarni Khone,Wun Tho Township,Kawlin District,Sagaing Region', 'Female', '5/KWT(N)573054'),
(13, 'Mg Thiri Maung', 'Murder/Robbery', 'No.225,29th Street(Upper block),Pabedan Township,Yangon City,Yangon Region', '26 Quarter,Dagon(South) Township,Yangon City,Yangon Region', 'Male', '12/PBD(N)000364'),
(14, 'Crime1', 'Tyoe1', 'add1', 'crime scene1', 'Female', 'nrc1'),
(15, 'Kyi sin1', 'Murder', '160/164', 'San Chaung', 'Female', 'abcdefgh');

-- --------------------------------------------------------

--
-- Table structure for table `operator`
--

CREATE TABLE `operator` (
  `Name` varchar(40) NOT NULL,
  `Username` varchar(40) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `Email_Address` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `operator`
--

INSERT INTO `operator` (`Name`, `Username`, `Password`, `Email_Address`) VALUES
('Group5', 'AChawLyMyr', '$2a$10$wYOanpNMj0.kByDBu5hI6OK4P.ojwq3ymdHAFmmr.TLnGnu1ywEO.', 'Kothureinlin2003@gmail.com'),
('afe', 'afger', '$2a$10$l2y8uGrKjscZmQxrG8CCwefY5fMz1L5iU/vgQwvLSA359hKWe/oKi', 'werwe'),
('qwe', 'dfg', '$2a$10$t9Hco2myjzrF28jeWbuYa.Ll6EYjskf.uR5L2pHu5fkzwF/txs4BC', 'qweqweqw@sgh'),
('Eaint Pyae Phyo', 'eaint pyae phyo', '$2a$10$.hFpJA4xugJjd0A74XHL1OJwFU02KMj8GgsaiYpFzss8C.6NfNe6.', 'epp@gmail.com'),
('jj', 'fffff', '', 'eeeeeeeeeeee@gmil.com'),
('uu', 'jj', '$2a$10$aNxMGijC.psJXWeHjyNwK.0kssyAPBEMsNzk66wL5hypXaVaaX7Fq', 'nn'),
('jj', 'jjk', '$2a$10$M2O5vhbgVmMWRDxd8y8BZeiOsosQwwBLK9X4EdT6JIqb/HG3/oKA6', 'jj'),
('name1', 'lll', '$2a$10$yEVxcQ8ppfPnsxFQc9Gdl.rqTSZNhbNvgXrSi.J0WdA6C0Znev75i', 'example@gmail.com'),
('nwet', 'nwet', 'nwet123', 'nwet@gmail.com'),
('ssssss', 'sa', '$2a$10$bMytTthz464B7lm4BD.FmugvMJUTtJAx4sKP6UtYw6ZJU.MjT/J86', 'ssss');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD UNIQUE KEY `ID` (`ID`);

--
-- Indexes for table `criminals`
--
ALTER TABLE `criminals`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `operator`
--
ALTER TABLE `operator`
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `criminals`
--
ALTER TABLE `criminals`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
