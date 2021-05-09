package com.promotion.bean;

import java.io.Serializable;

<<<<<<< HEAD
public class MatiereBean implements Serializable{
=======
public class MatiereBean implements Serializable {
>>>>>>> sean

	/**
	 * 
	 */
	private static final long serialVersionUID = -5914553338072416517L;
	private int id;
	private String nomMatiere;
	private Double coefficientMatiere;
	
	public MatiereBean() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomMatiere() {
		return nomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}

	public Double getCoefficientMatiere() {
		return coefficientMatiere;
	}

	public void setCoefficientMatiere(Double coefficientMatiere) {
		this.coefficientMatiere = coefficientMatiere;
	}

	@Override
	public String toString() {
		return " nomMatiere=" + nomMatiere + ", coefficientMatiere=" + coefficientMatiere;
	}
	
	
}
