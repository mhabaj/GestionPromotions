package com.promotion.bean;

import java.sql.Timestamp;
import java.util.List;
/**
 * @brief classe Bean des étudiants qui recevront les données de la base de données.
 * @author Sean Anica & Alhabaj Mahmod & Rondeau Juliette
 *
 */
public class EtudiantBean {

	private long id;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	private Boolean admin;
	private Timestamp dateDInscription;
	
	private List<NoteBean> notes;
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

	public List<NoteBean> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteBean> notes) {
		notes = notes;
	}

	public String getNomPromotion() {
		return nomPromotion;
	}

	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}
	
}
