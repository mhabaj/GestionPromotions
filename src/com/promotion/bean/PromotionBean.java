package com.promotion.bean;

import java.util.ArrayList;

public class PromotionBean {
	private String nomPromotion;
	private int annee;
	private double moyenneGeneralePromo;

	private ArrayList<EtudiantBean> etudiants;
	private ArrayList<MatiereBean> matieres;

	public PromotionBean() {

	}

	public PromotionBean(String nomPromotion, int annee) {
		this.nomPromotion = nomPromotion;
		this.annee = annee;
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

	public ArrayList<EtudiantBean> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(ArrayList<EtudiantBean> etudiants) {
		this.etudiants = etudiants;
	}

	public ArrayList<MatiereBean> getMatieres() {
		return matieres;
	}

	public void setMatieres(ArrayList<MatiereBean> matieres) {
		this.matieres = matieres;
	}

	/**
	 * @return the moyenneGeneralePromo
	 */
	public double getMoyenneGeneralePromo() {
		return moyenneGeneralePromo;
	}

	/**
	 * @param moyenneGeneralePromo the moyenneGeneralePromo to set
	 */
	public void setMoyenneGeneralePromo(double moyenneGeneralePromo) {
		this.moyenneGeneralePromo = moyenneGeneralePromo;
	}

	



}
