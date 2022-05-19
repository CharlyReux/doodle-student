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
-- Base de données :  `dashboard`
--
CREATE DATABASE IF NOT EXISTS `dashboard` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `dashboard`;


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
-- Structure de la table `Dashboard`
--

CREATE TABLE `Dashboard` (
  `id` bigint NOT NULL,
  `idTab` bigint NOT NULL,
  `idUser` bigint NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Index pour la table `Dashboard`
--
ALTER TABLE `Dashboard`
  ADD PRIMARY KEY (`id`);


--
-- Structure de la table `pollCopy`
--

CREATE TABLE `PollCopy` (
  `id` bigint NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `slugAdmin` varchar(255) DEFAULT NULL,
  `urlSondage` varchar(255) DEFAULT NULL,
  `urlSondageAdmin` varchar(255) DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Index pour la table `pollCopy`
--
ALTER TABLE `PollCopy`
  ADD PRIMARY KEY (`id`);

--
-- Structure de la table `dashboard_pollCopyAdmin`
--
/* 
CREATE TABLE `dashboard_pollCopyAdmin` (
  `dashboardAdmin_id` bigint NOT NULL,
  `pollCopyAdmin_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Index pour la table `dashboard_pollCopyAdmin`
--
ALTER TABLE `dashboard_pollCopyAdmin`
  ADD KEY `FK2m8oie88bmgxt3sm87i1mn1ao` (`pollCopyAdmin_id`),
  ADD KEY `FK9s1mrftmuef6lcexnlh89qgdn` (`dashboardAdmin_id`);
 */
/* --
-- Structure de la table `dashboard_pollCopyUser`
--

CREATE TABLE `dashboard_pollCopyUser` (
  `dashboardUser_id` bigint NOT NULL,
  `pollCopyUser_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Index pour la table `dashboard_pollCopyUser`
--
ALTER TABLE `dashboard_pollCopyUser`
  ADD KEY `FK2m8oie88bmgxt3sm87i1mn1ao` (`pollCopyUser_id`),
  ADD KEY `FK9s1mrftmuef6lcexnlh89qgdn` (`dashboard_id`);
 */