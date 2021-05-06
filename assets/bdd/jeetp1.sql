-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 06 mai 2021 à 21:03
-- Version du serveur :  8.0.21
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `jeetp1`
--

-- --------------------------------------------------------

--
-- Structure de la table `comprend`
--

DROP TABLE IF EXISTS `comprend`;
CREATE TABLE IF NOT EXISTS `comprend` (
  `idMatiere` int NOT NULL,
  `idPromotion` int NOT NULL,
  PRIMARY KEY (`idMatiere`,`idPromotion`),
  KEY `comprend_Promotion0_FK` (`idPromotion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `comprend`
--

INSERT INTO `comprend` (`idMatiere`, `idPromotion`) VALUES
(1, 1),
(2, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `idEtudiant` int NOT NULL AUTO_INCREMENT,
  `admin` tinyint(1) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mot_de_passe` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `date_inscription` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idPromotion` int DEFAULT NULL,
  PRIMARY KEY (`idEtudiant`),
  KEY `Etudiant_Promotion_FK` (`idPromotion`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`idEtudiant`, `admin`, `email`, `mot_de_passe`, `nom`, `prenom`, `date_inscription`, `idPromotion`) VALUES
(1, 1, 'admin@gmail.com', '123', 'admin', 'admin', '2021-05-20 14:32:33', NULL),
(14, 0, 'etu3@gmail.com', '123', 'etu3', 'etu3', '2021-05-06 15:48:24', 1),
(15, 0, 'etu5@gmail.com', '123', 'aze', 'aze', '2021-05-06 17:51:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `etudie`
--

DROP TABLE IF EXISTS `etudie`;
CREATE TABLE IF NOT EXISTS `etudie` (
  `idNote` int NOT NULL AUTO_INCREMENT,
  `idMatiere` int NOT NULL,
  `idEtudiant` int NOT NULL,
  `note` double DEFAULT NULL,
  PRIMARY KEY (`idMatiere`,`idEtudiant`,`idNote`) USING BTREE,
  KEY `etudie_Etudiant0_FK` (`idEtudiant`),
  KEY `idNote` (`idNote`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `etudie`
--

INSERT INTO `etudie` (`idNote`, `idMatiere`, `idEtudiant`, `note`) VALUES
(9, 1, 14, 14.29),
(12, 1, 14, 14.27),
(13, 1, 15, 6.82),
(10, 2, 14, 16.73),
(14, 2, 15, 16.45),
(11, 3, 14, 17.76),
(15, 3, 15, 1.25);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
CREATE TABLE IF NOT EXISTS `matiere` (
  `idMatiere` int NOT NULL AUTO_INCREMENT,
  `nomMatiere` varchar(50) DEFAULT NULL,
  `coefficientMatiere` double DEFAULT NULL,
  PRIMARY KEY (`idMatiere`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`idMatiere`, `nomMatiere`, `coefficientMatiere`) VALUES
(1, 'maths', 3),
(2, 'Francais', 2),
(3, 'Espagnol', 8);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `idPromotion` int NOT NULL AUTO_INCREMENT,
  `nomPromotion` varchar(50) DEFAULT NULL,
  `annee` int DEFAULT NULL,
  PRIMARY KEY (`idPromotion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`idPromotion`, `nomPromotion`, `annee`) VALUES
(1, 'DI4', 2021),
(2, 'DI3', 2022);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comprend`
--
ALTER TABLE `comprend`
  ADD CONSTRAINT `comprend_Matiere_FK` FOREIGN KEY (`idMatiere`) REFERENCES `matiere` (`idMatiere`) ON DELETE CASCADE,
  ADD CONSTRAINT `comprend_Promotion0_FK` FOREIGN KEY (`idPromotion`) REFERENCES `promotion` (`idPromotion`) ON DELETE CASCADE;

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `Etudiant_Promotion_FK` FOREIGN KEY (`idPromotion`) REFERENCES `promotion` (`idPromotion`);

--
-- Contraintes pour la table `etudie`
--
ALTER TABLE `etudie`
  ADD CONSTRAINT `etudie_Etudiant0_FK` FOREIGN KEY (`idEtudiant`) REFERENCES `etudiant` (`idEtudiant`) ON DELETE CASCADE,
  ADD CONSTRAINT `etudie_Matiere_FK` FOREIGN KEY (`idMatiere`) REFERENCES `matiere` (`idMatiere`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
