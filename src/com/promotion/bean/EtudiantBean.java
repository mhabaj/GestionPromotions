package com.promotion.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * @brief classe Bean des etudiants qui recevront les données de la base de données.
 * @author Sean Anica & Alhabaj Mahmod & Rondeau Juliette
 * @file EtudiantBean.java
 *
 */
public class EtudiantBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2009106718895075183L;
	private int id;
	private String nom;
	private String prenom;
	private int annee;
	private String email;
	private String motDePasse;
	private Boolean admin;
	private Timestamp dateDInscription;
	private double moyenneGenerale = 0.0;
	
	private ArrayList<NoteBean> notes;
	private String nomPromotion;
	
	/**
	 * constructeur par défaut de la classe.
	 */
	public EtudiantBean() {
		
	}
	
	/**
	 * getter de l'attribut id de l'etudiant
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * setter de l'attribut id de l'etudiant
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * getter de l'attribut Nom de l'etudiant
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * setter de l'attribut Nom de l'etudiant
	 * @return
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * getter de l'attribut Prénom de l'etudiant
	 * @return prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * setter de l'attribut Prénom de l'etudiant
	 * @return
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * getter de l'attribut Année d'obtention du diplôme de l'etudiant
	 * @return annee
	 */
	public int getAnnee() {
		return annee;
	}
	
	/**
	 * setter de l'attribut Année d'obtention du diplôme de l'etudiant
	 * @return
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	/**
	 * getter de l'attribut email de l'etudiant
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * getter de l'attribut email de l'etudiant
	 * @return email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * getter de l'attribut mot de passe de l'etudiant
	 * @return motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	
	/**
	 * setter de l'attribut mot de passe de l'etudiant
	 * @return
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	/**
	 * getter de l'attribut admin de l'etudiant
	 * @return admin
	 */
	public Boolean getAdmin() {
		return admin;
	}
	
	/**
	 * setter de l'attribut admin de l'etudiant
	 * @return
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	/**
	 * getter de l'attribut date d'inscription de l'etudiant
	 * @return dateDInscription
	 */
	public Timestamp getDateDInscription() {
		return dateDInscription;
	}
	
	/**
	 * setter de l'attribut date d'inscription de l'etudiant
	 * @return
	 */
	public void setDateDInscription(Timestamp dateDInscription) {
		this.dateDInscription = dateDInscription;
	}

	/**
	 * getter de la liste de notes de l'etudiant
	 * @return notes
	 */
	public ArrayList<NoteBean> getNotes() {
		return this.notes;
	}

	/**
	 * setter de la liste de notes de l'etudiant
	 * @return
	 */
	public void setNotes(ArrayList<NoteBean> notes) {
		this.notes = notes;
	}

	/**
	 * getter l'attribut nom de la promotion de l'etudiant
	 * @return nomPromotion
	 */
	public String getNomPromotion() {
		return nomPromotion;
	}

	/**
	 * setter l'attribut nom de la promotion de l'etudiant
	 * @return
	 */
	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}

	/**
	 * getter l'attribut moyenne générale de l'etudiant
	 * @return moyenneGenerale
	 */
	public double getMoyenneGenerale() {
		return moyenneGenerale;
	}

	/**
	 * setter l'attribut moyenne générale de l'etudiant
	 * @return
	 */
	public void setMoyenneGenerale(double moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}
	
	/**
	 * redéfinition de la fonction toString de la classe.
	 */
	@Override
	public String toString() {
		return "EtudiantBean [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", annee=" + annee + ", email="
				+ email + ", motDePasse=" + motDePasse + ", admin=" + admin + ", dateDInscription=" + dateDInscription
				+ ", moyenneGenerale=" + moyenneGenerale + ", notes=" + notes + ", nomPromotion=" + nomPromotion + "]";
	}


	
	
	
}
