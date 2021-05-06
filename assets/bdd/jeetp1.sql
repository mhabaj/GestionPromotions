#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Matiere
#------------------------------------------------------------

CREATE TABLE Matiere(
        idMatiere          Int  Auto_increment  NOT NULL ,
        nomMatiere         Varchar (50) ,
        coefficientMatiere Double
	,CONSTRAINT Matiere_PK PRIMARY KEY (idMatiere)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Promotion
#------------------------------------------------------------

CREATE TABLE Promotion(
        idPromotion  Int  Auto_increment  NOT NULL ,
        nomPromotion Varchar (50) ,
        annee        Int
	,CONSTRAINT Promotion_PK PRIMARY KEY (idPromotion)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Etudiant
#------------------------------------------------------------

CREATE TABLE Etudiant(
        idEtudiant       Int  Auto_increment  NOT NULL ,
        admin            Bool NOT NULL ,
        email            Varchar (50) NOT NULL ,
        mot_de_passe     Varchar (50) NOT NULL ,
        nom              Varchar (50) NOT NULL ,
        prenom           Varchar (50) NOT NULL ,
        date_inscription TimeStamp NOT NULL ,
        idPromotion      Int
	,CONSTRAINT Etudiant_PK PRIMARY KEY (idEtudiant)

	,CONSTRAINT Etudiant_Promotion_FK FOREIGN KEY (idPromotion) REFERENCES Promotion(idPromotion) 
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: comprend
#------------------------------------------------------------

CREATE TABLE comprend(
        idMatiere   Int NOT NULL ,
        idPromotion Int NOT NULL
	,CONSTRAINT comprend_PK PRIMARY KEY (idMatiere,idPromotion)

	,CONSTRAINT comprend_Matiere_FK FOREIGN KEY (idMatiere) REFERENCES Matiere(idMatiere) on delete cascade
	,CONSTRAINT comprend_Promotion0_FK FOREIGN KEY (idPromotion) REFERENCES Promotion(idPromotion) on delete cascade
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: etudie
#------------------------------------------------------------

CREATE TABLE etudie(
        idMatiere  Int NOT NULL ,
        idEtudiant Int NOT NULL ,
        note       Double
	,CONSTRAINT etudie_PK PRIMARY KEY (idMatiere,idEtudiant)

	,CONSTRAINT etudie_Matiere_FK FOREIGN KEY (idMatiere) REFERENCES Matiere(idMatiere) on delete cascade
	,CONSTRAINT etudie_Etudiant0_FK FOREIGN KEY (idEtudiant) REFERENCES Etudiant(idEtudiant) on delete cascade
)ENGINE=InnoDB;

