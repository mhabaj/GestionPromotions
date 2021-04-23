-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 24 avr. 2021 à 00:42
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
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `idEtudiant` int(11) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mot_de_passe` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `date_inscription` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idPromotion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`idEtudiant`, `admin`, `email`, `mot_de_passe`, `nom`, `prenom`, `date_inscription`, `idPromotion`) VALUES
(1, 1, 'admin@gmail.com', '123', 'admin', 'admin', '2021-04-23 22:41:29', 1),
(2, 0, 'user@gmail.com', '123', 'user', 'user', '2021-04-23 22:41:29', 1);

-- --------------------------------------------------------

--
-- Structure de la table `etudie`
--

CREATE TABLE `etudie` (
  `idPromotion` int(11) NOT NULL,
  `idMatiere` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL,
  `note` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `idMatiere` int(11) NOT NULL,
  `nomMatiere` varchar(50) DEFAULT NULL,
  `coefficientMatiere` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`idMatiere`, `nomMatiere`, `coefficientMatiere`) VALUES
(1, 'J2e', 1.5);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `idPromotion` int(11) NOT NULL,
  `nomPromotion` varchar(50) DEFAULT NULL,
  `annee` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`idPromotion`, `nomPromotion`, `annee`) VALUES
(1, 'di4', 2021);

--
-- Index pour les tables déchargées
--

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
  ADD PRIMARY KEY (`idPromotion`,`idMatiere`,`idEtudiant`),
  ADD KEY `etudie_Matiere0_FK` (`idMatiere`),
  ADD KEY `etudie_Etudiant1_FK` (`idEtudiant`);

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
  MODIFY `idEtudiant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `idMatiere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `idPromotion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `Etudiant_Promotion_FK` FOREIGN KEY (`idPromotion`) REFERENCES `promotion` (`idPromotion`);

--
-- Contraintes pour la table `etudie`
--
ALTER TABLE `etudie`
  ADD CONSTRAINT `etudie_Etudiant1_FK` FOREIGN KEY (`idEtudiant`) REFERENCES `etudiant` (`idEtudiant`) ON DELETE CASCADE,
  ADD CONSTRAINT `etudie_Matiere0_FK` FOREIGN KEY (`idMatiere`) REFERENCES `matiere` (`idMatiere`) ON DELETE CASCADE,
  ADD CONSTRAINT `etudie_Promotion_FK` FOREIGN KEY (`idPromotion`) REFERENCES `promotion` (`idPromotion`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
