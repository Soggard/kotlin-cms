-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 24 fév. 2019 à 14:24
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `cms`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `text` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id`, `title`, `text`) VALUES
(1, 'TOP 3 DES TECHNOLOGIES DE DEV ! (LE 5EME VA VOUS ETONNER)', 'Les meilleurs technologies du web classés de la meilleure à la 5ème meilleure !\r\n1- Kotlin\r\n2- KTOR\r\n3- jCenter\r\n4- Intellij Ultimate\r\n5- Kotlin'),
(2, 'Qu\'est-ce que Kotlin ?', 'Kotlin, c\'est un peu comme Java, mais en mieux. C\'est plus simple, moins verbeux, et beaucoup moins moche.'),
(3, 'Frequently asked questions', 'Q- Faut-il utiliser Kotlin ?\r\nR- Oui.\r\n\r\nQ- Faut-il utiliser Javascript\r\nR- Non.\r\n\r\nQ- Pourquoi ?\r\nR- Parce que.\r\n\r\nQ- Comment récupérer les paramètres posts envoyés via un formulaire HTML ?\r\nR- Aucune idée, il va falloir potasser la doc.');

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article` int(11) NOT NULL,
  `text` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article` (`article`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `article`, `text`) VALUES
(1, 3, 'Super article :D'),
(2, 1, 'Cool !'),
(3, 1, 'Super :)');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(60) COLLATE utf8_bin NOT NULL,
  `password` varchar(60) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`) VALUES
(1, 'emeric', '$2a$10$DNzED6lTEvG6kjq7AHLPDOxGfuVGCXRsK5WAolt4RFXjsJPHyNota'),
(2, 'salomon', '$2a$10$CiwiwttM7MW3TLAdX3bJEeis6Ms1S6Wf9b7y7W/UkZmWrNfTmvUSe');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`article`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
