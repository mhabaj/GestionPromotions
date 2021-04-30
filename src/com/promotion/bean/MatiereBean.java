package com.promotion.bean;

public class MatiereBean {

	private long id;
	private String nomMatiere;
	private Double coefficientMatiere;
	
	public MatiereBean() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
