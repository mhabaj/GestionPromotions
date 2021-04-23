#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Matiere
#------------------------------------------------------------

CREATE TABLE Matiere(
        id                 Int  Auto_increment  NOT NULL ,
        nomMatiere         Varchar (50) NOT NULL ,
        coefficientMatiere Double NOT NULL
	,CONSTRAINT Matiere_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Promotion
#------------------------------------------------------------

CREATE TABLE Promotion(
        id           Int  Auto_increment  NOT NULL ,
        nomPromotion Varchar (50) NOT NULL ,
        annee        Int 
	,CONSTRAINT Promotion_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Etudiant
#------------------------------------------------------------

CREATE TABLE Etudiant(
        id               Int  Auto_increment  NOT NULL ,
        admin            Bool NOT NULL ,
        email            Varchar (50) NOT NULL ,
        mot_de_passe     Varchar (50) NOT NULL ,
        nom              Varchar (50) NOT NULL ,
        prenom           Varchar (50) NOT NULL ,
        date_inscription TimeStamp NOT NULL ,
        id_Promotion     Int
	,CONSTRAINT Etudiant_PK PRIMARY KEY (id)

	,CONSTRAINT Etudiant_Promotion_FK FOREIGN KEY (id_Promotion) REFERENCES Promotion(id) ON DELETE CASCADE
	
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: etudie
#------------------------------------------------------------

CREATE TABLE etudie(
        id          Int NOT NULL ,
        id_Etudiant Int NOT NULL ,
        note        Int 
	,CONSTRAINT etudie_PK PRIMARY KEY (id,id_Etudiant)

	,CONSTRAINT etudie_Matiere_FK FOREIGN KEY (id) REFERENCES Matiere(id)
	,CONSTRAINT etudie_Etudiant0_FK FOREIGN KEY (id_Etudiant) REFERENCES Etudiant(id)
)ENGINE=InnoDB;

