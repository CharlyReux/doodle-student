-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  mar. 03 nov. 2020 à 18:32
-- Version du serveur :  8.0.19
-- Version de PHP :  7.3.11-0ubuntu0.19.10.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `poll`
--
CREATE DATABASE IF NOT EXISTS `poll` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `poll`;


-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(0);
 
-- --------------------------------------------------------

--
-- Structure de la table `poll`
--

CREATE TABLE `Poll` (
  `id` bigint NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- -------------------------------------------------------

--
-- Index pour la table `poll`
--
ALTER TABLE `Poll`
  ADD PRIMARY KEY (`id`);


-- -------------------------------------------------------
--
-- Structure de la table `Choice`
--

CREATE TABLE `Choice` (
  `id` bigint NOT NULL,
  `endDate` datetime(6) DEFAULT NULL,
  `startDate` datetime(6) DEFAULT NULL,
  `pollID` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Index pour la table `Choice`
--
ALTER TABLE `Choice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9pb9a172pl46be48ythes94cq` (`pollID`);

-- --------------------------------------------------------

--
-- Structure de la table `choice_user`
--

CREATE TABLE `choice_user` (
  `choice_id` bigint NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Index pour la table `choice_user`
--
ALTER TABLE `choice_user`
  ADD KEY `FK2m8oie88bmgxt3sm87i1mn1ao` (`user_id`),
  ADD KEY `FK9s1mrftmuef6lcexnlh89qgdn` (`choice_id`);

-- -------------------------------------------------
--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `id` bigint NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`);
