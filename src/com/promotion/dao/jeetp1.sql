-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 21 avr. 2021 à 01:30
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
  `id` int(11) NOT NULL,
  `admin` tinyint(1) NOT NULL COMMENT 'si l''étudiant est admin ou non',
  `email` varchar(60) NOT NULL,
  `mot_de_passe` varchar(32) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT 'prenom de l''étudiant',
  `date_inscription` datetime NOT NULL,
  `annee` int(11) NOT NULL COMMENT 'année d''étude de l''étudiant',
  `nomPromotion` varchar(200) NOT NULL COMMENT 'nom de la promo de l''étudiant'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `admin`, `email`, `mot_de_passe`, `nom`, `prenom`, `date_inscription`, `annee`, `nomPromotion`) VALUES
(1, 1, 'user1@mail.acme', 'pass1', 'user1', 'sean', '2021-04-19 22:44:10', 4, 'DI4'),
(2, 0, 'user2@mail.acme', 'pass2', 'user2', 'mahmod', '2021-04-19 22:44:10', 4, 'DI4'),
(3, 0, 'user3@mail.acme', 'pass3', 'user3', 'juliette', '2021-04-20 01:10:26', 4, 'DI4');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `id` int(11) NOT NULL,
  `nomMatiere` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT 'nom de la matière',
  `coefficientMatiere` double NOT NULL COMMENT 'coeff de la matière'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`id`, `nomMatiere`, `coefficientMatiere`) VALUES
(1, 'QVT', 1);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `idMatiere` int(11) NOT NULL COMMENT 'matière de la note',
  `idEtudiant` int(11) NOT NULL COMMENT 'Etudiant à qui cette note est associée',
  `note` int(11) NOT NULL COMMENT 'note de l''étudiant'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id`, `idMatiere`, `idEtudiant`, `note`) VALUES
(3, 1, 1, 20);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk1` (`idEtudiant`),
  ADD KEY `fk2` (`idMatiere`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`idEtudiant`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `fk2` FOREIGN KEY (`idMatiere`) REFERENCES `matiere` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
