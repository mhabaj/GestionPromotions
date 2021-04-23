package com.promotion.bean;

import java.util.List;

public class PromotionBean {
	private String nomPromotion;
	private int annee;
	
	private List<EtudiantBean> etudiants;
	private List<MatiereBean> matieres;
	
	public PromotionBean() {
		
	}

	public String getNomPromotion() {
		return nomPromotion;
	}

	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public List<EtudiantBean> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<EtudiantBean> etudiants) {
		this.etudiants = etudiants;
	}

	public List<MatiereBean> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<MatiereBean> matieres) {
		this.matieres = matieres;
	}
	
}
