-- phpMyAdmin SQL Dump
-- version 5.0.4deb2
-- https://www.phpmyadmin.net/
--
-- Hôte : mysql.info.unicaen.fr:3306
-- Généré le : lun. 28 nov. 2022 à 19:09
-- Version du serveur :  10.5.11-MariaDB-1
-- Version de PHP : 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `22009614_bd`
--

-- --------------------------------------------------------

--
-- Structure de la table `WOMANKING`
--

CREATE TABLE `WOMANKING` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `WOMANKING`
--

INSERT INTO `WOMANKING` (`id`, `name`, `role`, `age`) VALUES
('1sde', 'Viola', 'Nanisca', 57),
('2r34', 'Gbenoukpo', 'Guerrier du roi', 19),
('3op4', 'John Boyega', 'Roi Ghezo', 30),
('a3dd', 'Elie Josias', 'Figurant Enfant', 11),
('b174', 'Angelique Kidjo', 'la Meunon', 62),
('b1b2', 'Kafui', 'Dada', 29),
('b1c8', 'Thuso Mbedu', 'Nawi', 31),
('b522', 'Mahutodji', 'Griot', 22),
('bc79', 'Marie Do', 'Reine Mère', 50),
('cc83', 'Joseph de Cupertino', 'protecteur', 60),
('dac8', 'Ahuefa', 'Dada', 28),
('e3ac', 'Lashana Lynch', 'Izogie', 34),
('e501', 'Test', 'COO', 23),
('f07a', 'Jayme Lawson', 'Shante', 25),
('f31f', 'Jordan Bolger', 'Malik', 28),
('fa2d', 'Elie Anna', 'Figurant Enfant', 9),
('fc4c', 'Jade F', 'Bébé', 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `WOMANKING`
--
ALTER TABLE `WOMANKING`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
