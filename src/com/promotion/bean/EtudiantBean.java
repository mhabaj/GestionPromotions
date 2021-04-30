package com.promotion.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * @brief classe Bean des étudiants qui recevront les données de la base de données.
 * @author Sean Anica & Alhabaj Mahmod & Rondeau Juliette
 *
 */
public class EtudiantBean {

	private long id;
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
	
	public EtudiantBean() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Timestamp getDateDInscription() {
		return dateDInscription;
	}
	public void setDateDInscription(Timestamp dateDInscription) {
		this.dateDInscription = dateDInscription;
	}

	public ArrayList<NoteBean> getNotes() {
		return this.notes;
	}

	public void setNotes(ArrayList<NoteBean> notes) {
		this.notes = notes;
	}

	public String getNomPromotion() {
		return nomPromotion;
	}

	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}

	public double getMoyenneGenerale() {
		return moyenneGenerale;
	}

	public void setMoyenneGenerale(double moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	@Override
	public String toString() {
		return "EtudiantBean [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", annee=" + annee + ", email="
				+ email + ", motDePasse=" + motDePasse + ", admin=" + admin + ", dateDInscription=" + dateDInscription
				+ ", moyenneGenerale=" + moyenneGenerale + ", notes=" + notes + ", nomPromotion=" + nomPromotion + "]";
	}


	
	
	
}
