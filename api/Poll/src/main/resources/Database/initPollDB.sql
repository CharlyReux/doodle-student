SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de données :  `poll_test`
--
CREATE DATABASE IF NOT EXISTS `pollTest` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `pollTest`;

-- --------------------------------------------------------

--
-- Structure de la table `Poll`
--

CREATE TABLE `Poll` (
  `id` bigint NOT NULL,
  `title` varchar(255) DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--  tests pour la base de données
INSERT INTO Poll(id, title) VALUES (1, 'oui');