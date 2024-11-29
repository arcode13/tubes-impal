-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2024 at 04:33 PM
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
-- Database: `dormhub`
--

-- --------------------------------------------------------

--
-- Table structure for table `helpdesk`
--

CREATE TABLE `helpdesk` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `helpdesk`
--

INSERT INTO `helpdesk` (`id`, `user_id`) VALUES
(1, 3),
(2, 8),
(3, 13);

-- --------------------------------------------------------

--
-- Table structure for table `laporan_barang`
--

CREATE TABLE `laporan_barang` (
  `id` int(11) NOT NULL,
  `helpdesk_id` int(11) NOT NULL,
  `mahasiswa_id` int(11) NOT NULL,
  `jenis` varchar(10) NOT NULL,
  `bukti_foto` varchar(255) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laporan_barang`
--

INSERT INTO `laporan_barang` (`id`, `helpdesk_id`, `mahasiswa_id`, `jenis`, `bukti_foto`, `status`) VALUES
(1, 1, 1, 'paket', 'foto_paket1.jpg', 'menunggu'),
(2, 1, 3, 'makanan', 'foto_makanan1.jpg', 'terima'),
(3, 2, 4, 'paket', 'foto_paket2.jpg', 'menunggu'),
(4, 2, 6, 'makanan', 'foto_makanan2.jpg', 'terima'),
(5, 3, 7, 'paket', 'foto_paket3.jpg', 'menunggu'),
(6, 1, 9, 'makanan', 'foto_makanan3.jpg', 'terima'),
(7, 2, 11, 'paket', 'foto_paket4.jpg', 'menunggu'),
(8, 3, 13, 'makanan', 'foto_makanan4.jpg', 'terima'),
(9, 1, 14, 'paket', 'foto_paket5.jpg', 'menunggu'),
(10, 3, 15, 'makanan', 'foto_makanan5.jpg', 'terima');

-- --------------------------------------------------------

--
-- Table structure for table `laporan_umum`
--

CREATE TABLE `laporan_umum` (
  `id` int(11) NOT NULL,
  `helpdesk_id` int(11) NOT NULL,
  `mahasiswa_id` int(11) NOT NULL,
  `jenis` varchar(10) NOT NULL,
  `alasan` varchar(255) NOT NULL,
  `bukti_foto` varchar(255) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laporan_umum`
--

INSERT INTO `laporan_umum` (`id`, `helpdesk_id`, `mahasiswa_id`, `jenis`, `alasan`, `bukti_foto`, `status`) VALUES
(1, 1, 2, 'izin', 'Izin keluar kampus', 'foto_izin1.jpg', 'menunggu'),
(2, 2, 4, 'keluhan', 'Keran bocor di kamar mandi', 'foto_keluhan1.jpg', 'setuju'),
(3, 3, 6, 'izin', 'Izin tidak masuk asrama', 'foto_izin2.jpg', 'tolak'),
(4, 1, 8, 'keluhan', 'Lampu di kamar mati', 'foto_keluhan2.jpg', 'menunggu'),
(5, 2, 10, 'izin', 'Izin pulang ke rumah', 'foto_izin3.jpg', 'setuju'),
(6, 3, 12, 'keluhan', 'Kamar mandi kotor', 'foto_keluhan3.jpg', 'tolak');

-- --------------------------------------------------------

--
-- Table structure for table `level`
--

CREATE TABLE `level` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `level`
--

INSERT INTO `level` (`id`, `nama`) VALUES
(1, 'Mahasiswa'),
(2, 'Help Desk'),
(3, 'Senior Residence');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `no_kamar` int(11) NOT NULL,
  `no_kasur` int(11) NOT NULL,
  `jurusan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `user_id`, `no_kamar`, `no_kasur`, `jurusan`) VALUES
(1, 1, 101, 2, 'S1 Informatika'),
(2, 2, 102, 3, 'S1 Sistem Informasi'),
(3, 5, 103, 4, 'S1 Teknik Elektro'),
(4, 6, 104, 1, 'S1 Teknik Industri'),
(5, 7, 105, 2, 'D3 Desain Komunikasi Visual'),
(6, 10, 106, 3, 'S1 Manajemen Bisnis'),
(7, 11, 107, 4, 'S1 Akuntansi'),
(8, 12, 108, 1, 'S1 Teknik Industri'),
(9, 15, 109, 2, 'S1 Informatika'),
(10, 14, 110, 3, 'S1 Informatika'),
(11, 4, 111, 4, 'D3 Informatika'),
(12, 8, 112, 1, 'S1 Teknik Fisika'),
(13, 9, 113, 2, 'S1 Ilmu Komunikasi'),
(14, 13, 114, 3, 'S1 Teknik Komputer'),
(15, 3, 115, 4, 'S1 Manajemen Bisnis Komunikasi');

-- --------------------------------------------------------

--
-- Table structure for table `senior_residence`
--

CREATE TABLE `senior_residence` (
  `id` int(11) NOT NULL,
  `mahasiswa_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `senior_residence`
--

INSERT INTO `senior_residence` (`id`, `mahasiswa_id`) VALUES
(1, 4),
(2, 5),
(3, 9),
(4, 10),
(5, 11),
(6, 13),
(7, 14),
(8, 15);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nama_lengkap` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `foto_profil` varchar(255) NOT NULL,
  `nomor_hp` varchar(20) NOT NULL,
  `level_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nama_lengkap`, `email`, `password`, `foto_profil`, `nomor_hp`, `level_id`) VALUES
(1, 'Ahmad Setiawan', 'ahmad.setiawan@student.telkomuniversity.ac.id', 'password123', 'foto1.jpg', '081234567890', 1),
(2, 'Dewi Lestari', 'dewi.lestari@student.telkomuniversity.ac.id', 'password123', 'foto2.jpg', '081234567891', 1),
(3, 'Budi Santoso', 'budi.santoso.helpdesk@gmail.com', 'password123', 'foto3.jpg', '081234567892', 2),
(4, 'Rina Permata', 'rina.permata@student.telkomuniversity.ac.id', 'password123', 'foto4.jpg', '081234567893', 3),
(5, 'Arif Rahman', 'arif.rahman@student.telkomuniversity.ac.id', 'password123', 'foto5.jpg', '081234567894', 1),
(6, 'Siti Nurhaliza', 'siti.nurhaliza@student.telkomuniversity.ac.id', 'password123', 'foto6.jpg', '081234567895', 1),
(7, 'Indra Lesmana', 'indra.lesmana@student.telkomuniversity.ac.id', 'password123', 'foto7.jpg', '081234567896', 1),
(8, 'Lia Kusuma', 'lia.kusuma.helpdesk@gmail.com', 'password123', 'foto8.jpg', '081234567897', 2),
(9, 'Agus Prabowo', 'agus.prabowo@student.telkomuniversity.ac.id', 'password123', 'foto9.jpg', '081234567898', 3),
(10, 'Dian Sastro', 'dian.sastro@student.telkomuniversity.ac.id', 'password123', 'foto10.jpg', '081234567899', 1),
(11, 'Fajar Nugraha', 'fajar.nugraha@student.telkomuniversity.ac.id', 'password123', 'foto11.jpg', '081234567800', 1),
(12, 'Mega Wijaya', 'mega.wijaya@student.telkomuniversity.ac.id', 'password123', 'foto12.jpg', '081234567801', 1),
(13, 'Yusuf Hidayat', 'yusuf.hidayat.helpdesk@gmail.com', 'password123', 'foto13.jpg', '081234567802', 2),
(14, 'Nina Anggraini', 'nina.anggraini@student.telkomuniversity.ac.id', 'password123', 'foto14.jpg', '081234567803', 3),
(15, 'Rizki Putra', 'rizki.putra@student.telkomuniversity.ac.id', 'password123', 'foto15.jpg', '081234567804', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `helpdesk`
--
ALTER TABLE `helpdesk`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `laporan_barang`
--
ALTER TABLE `laporan_barang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `laporan_barang_ibfk_1` (`mahasiswa_id`),
  ADD KEY `helpdesk_id` (`helpdesk_id`);

--
-- Indexes for table `laporan_umum`
--
ALTER TABLE `laporan_umum`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mahasiswa_id` (`mahasiswa_id`),
  ADD KEY `helpdesk_id` (`helpdesk_id`);

--
-- Indexes for table `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `senior_residence`
--
ALTER TABLE `senior_residence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mahasiswa_id` (`mahasiswa_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`level_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `helpdesk`
--
ALTER TABLE `helpdesk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `laporan_barang`
--
ALTER TABLE `laporan_barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `laporan_umum`
--
ALTER TABLE `laporan_umum`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `level`
--
ALTER TABLE `level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `senior_residence`
--
ALTER TABLE `senior_residence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `helpdesk`
--
ALTER TABLE `helpdesk`
  ADD CONSTRAINT `helpdesk_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `laporan_barang`
--
ALTER TABLE `laporan_barang`
  ADD CONSTRAINT `laporan_barang_ibfk_1` FOREIGN KEY (`mahasiswa_id`) REFERENCES `mahasiswa` (`id`),
  ADD CONSTRAINT `laporan_barang_ibfk_2` FOREIGN KEY (`helpdesk_id`) REFERENCES `helpdesk` (`id`);

--
-- Constraints for table `laporan_umum`
--
ALTER TABLE `laporan_umum`
  ADD CONSTRAINT `laporan_umum_ibfk_1` FOREIGN KEY (`mahasiswa_id`) REFERENCES `mahasiswa` (`id`),
  ADD CONSTRAINT `laporan_umum_ibfk_2` FOREIGN KEY (`helpdesk_id`) REFERENCES `helpdesk` (`id`);

--
-- Constraints for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `mahasiswa_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `senior_residence`
--
ALTER TABLE `senior_residence`
  ADD CONSTRAINT `senior_residence_ibfk_1` FOREIGN KEY (`mahasiswa_id`) REFERENCES `mahasiswa` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`level_id`) REFERENCES `level` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
