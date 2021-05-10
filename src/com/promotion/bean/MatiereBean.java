package com.promotion.bean;

import java.io.Serializable;

/**
 * @brief classe Bean des matieres qui recevront les données de la base de données.
 * @author Sean Anica & Alhabaj Mahmod & Rondeau Juliette
 * @file EtudiantBean.java
 *
 */
public class MatiereBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5914553338072416517L;
	private int id;
	private String nomMatiere;
	private Double coefficientMatiere;
	
	/**
	 * constructeur par défaut de la classe
	 */
	public MatiereBean() {
		
	}

	/**
	 * getter de l'attribut id de la matiere
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * setter de l'attribut id de la matiere
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter de l'attribut nom de la matiere
	 * @return nomMatiere
	 */
	public String getNomMatiere() {
		return nomMatiere;
	}

	/**
	 * setter de l'attribut nom de la matiere
	 * @return
	 */
	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}

	/**
	 * getter de l'attribut coefficient de la matiere
	 * @return coefficientMatiere
	 */
	public Double getCoefficientMatiere() {
		return coefficientMatiere;
	}
	/**
	 * setter de l'attribut coefficient de la matiere
	 * @return
	 */
	public void setCoefficientMatiere(Double coefficientMatiere) {
		this.coefficientMatiere = coefficientMatiere;
	}

	/**
	 * redefinition de la fonction toString de la classe
	 */
	@Override
	public String toString() {
		return " nomMatiere=" + nomMatiere + ", coefficientMatiere=" + coefficientMatiere;
	}
	
	
}
