-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 10 mai 2021 à 10:37
-- Version du serveur :  5.7.17
-- Version de PHP :  7.1.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `jeetp1`
--

-- --------------------------------------------------------

--
-- Structure de la table `comprend`
--

CREATE TABLE `comprend` (
  `idMatiere` int(11) NOT NULL,
  `idPromotion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `comprend`
--

INSERT INTO `comprend` (`idMatiere`, `idPromotion`) VALUES
(1, 1),
(2, 1),
(3, 1),
(9, 1),
(11, 1),
(14, 1),
(15, 1),
(27, 1),
(28, 1),
(10, 2),
(19, 2),
(20, 2),
(21, 2),
(22, 2),
(29, 2),
(17, 9),
(18, 9),
(12, 11),
(16, 11),
(23, 12),
(24, 13),
(25, 13),
(26, 14),
(30, 15),
(31, 16);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `idEtudiant` int(11) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mot_de_passe` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `date_inscription` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idPromotion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`idEtudiant`, `admin`, `email`, `mot_de_passe`, `nom`, `prenom`, `date_inscription`, `idPromotion`) VALUES
(1, 1, 'admin@gmail.com', '123', 'admin', 'admin', '2021-05-20 14:32:33', NULL),
(58, 0, 'etudiant7769@gmail.com', '123', 'etudiant7769', 'etudiant7769', '2021-05-09 19:39:15', 1),
(59, 0, 'etudiant33891@gmail.com', '123', 'etudiant33891', 'etudiant33891', '2021-05-09 19:39:15', 1),
(60, 0, 'etudiant84041@gmail.com', '123', 'etudiant84041', 'etudiant84041', '2021-05-09 19:45:20', 2),
(63, 0, 'etudiant81052@gmail.com', '123', 'etudiant81052', 'etudiant81052', '2021-05-09 19:52:57', 15),
(64, 0, 'etudiant21048@gmail.com', '123', 'etudiant21048', 'etudiant21048', '2021-05-09 19:52:57', 15),
(66, 0, 'etudiant15681@gmail.com', '123', 'etudiant15681', 'etudiant15681', '2021-05-10 08:30:37', 16),
(67, 0, 'etudiant50941@gmail.com', '123', 'etudiant50941', 'etudiant50941', '2021-05-10 08:30:37', 16),
(68, 0, 'etudiant15978@gmail.com', '123', 'etudiant15978', 'etudiant15978', '2021-05-10 08:30:37', 16);

-- --------------------------------------------------------

--
-- Structure de la table `etudie`
--

CREATE TABLE `etudie` (
  `idNote` int(11) NOT NULL,
  `idMatiere` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL,
  `note` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `etudie`
--

INSERT INTO `etudie` (`idNote`, `idMatiere`, `idEtudiant`, `note`) VALUES
(142, 1, 58, 6.06),
(151, 1, 59, 16.29),
(143, 2, 58, 12.33),
(152, 2, 59, 7.91),
(144, 3, 58, 15.11),
(153, 3, 59, 16.82),
(145, 9, 58, 12.7),
(154, 9, 59, 11.84),
(160, 10, 60, 14.77),
(146, 11, 58, 2.89),
(155, 11, 59, 3),
(147, 14, 58, 4.74),
(156, 14, 59, 19.49),
(148, 15, 58, 16),
(157, 15, 59, 5.48),
(161, 19, 60, 5.85),
(162, 20, 60, 4.78),
(163, 21, 60, 4.34),
(164, 22, 60, 19.54),
(149, 27, 58, 9.32),
(158, 27, 59, 4.46),
(150, 28, 58, 9.1),
(159, 28, 59, 4.6),
(165, 29, 60, 18.41),
(178, 30, 63, 2.72),
(179, 30, 64, 7.98),
(181, 31, 66, 14.75),
(182, 31, 67, 14.07),
(183, 31, 68, 19.65);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `idMatiere` int(11) NOT NULL,
  `nomMatiere` varchar(50) DEFAULT NULL,
  `coefficientMatiere` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`idMatiere`, `nomMatiere`, `coefficientMatiere`) VALUES
(1, 'maths', 3),
(2, 'Francais', 2),
(3, 'Espagnol', 8),
(9, 'japonais', 1),
(10, 'italien', 1),
(11, 'japonais', 1),
(12, 'assassinat', 2),
(14, 'italien', 1),
(15, 'assassinat', 2),
(16, 'italien', 2),
(17, 'japonais', 2),
(18, 'japonais', 2),
(19, 'assassinat', 4),
(20, 'assassinat', 2),
(21, 'assassinat', 2),
(22, 'japonais', 2),
(23, 'italien', 2),
(24, 'assassinat', 2),
(25, 'assassinat', 2),
(26, 'assassinat', 2),
(27, 'italien', 2),
(28, 'japonais', 2),
(29, 'assassinat', 2),
(30, 'japonais', 2),
(31, 'japonais', 2);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `idPromotion` int(11) NOT NULL,
  `nomPromotion` varchar(50) DEFAULT NULL,
  `annee` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`idPromotion`, `nomPromotion`, `annee`) VALUES
(1, 'DI4', 2021),
(2, 'DI3', 2022),
(9, 'DI5', 2025),
(11, 'DI6', 2029),
(12, 'DI7', 2021),
(13, 'DI8', 2021),
(14, 'DI9', 2021),
(15, 'DI10', 2021),
(16, 'DI11', 2025);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `comprend`
--
ALTER TABLE `comprend`
  ADD PRIMARY KEY (`idMatiere`,`idPromotion`),
  ADD KEY `comprend_Promotion0_FK` (`idPromotion`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`idEtudiant`),
  ADD KEY `Etudiant_Promotion_FK` (`idPromotion`);

--
-- Index pour la table `etudie`
--
ALTER TABLE `etudie`
  ADD PRIMARY KEY (`idMatiere`,`idEtudiant`,`idNote`) USING BTREE,
  ADD KEY `etudie_Etudiant0_FK` (`idEtudiant`),
  ADD KEY `idNote` (`idNote`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`idMatiere`);

--
-- Index pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`idPromotion`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `idEtudiant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT pour la table `etudie`
--
ALTER TABLE `etudie`
  MODIFY `idNote` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=184;
--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `idMatiere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT pour la table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `idPromotion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
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
