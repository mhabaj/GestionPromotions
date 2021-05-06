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

	public double calculeMoyenneGeneralePromo() {
		Double moyenne = 0.0;
		Double sommeCoeff = 0.0;
		if (this.getEtudiants() != null) {
			for (EtudiantBean currentEtudiant : this.getEtudiants()) {
				moyenne += currentEtudiant.getMoyenneGenerale();
				sommeCoeff++;
			}
		}
		return Math.round((moyenne / sommeCoeff) * 100.0) / 100.0;
	}

	/**
	 * @return the moyenneGenerale
	 */
	public double getMoyenneGenerale() {
		return moyenneGeneralePromo;
	}

	/**
	 * @param moyenneGenerale the moyenneGenerale to set
	 */
	public void setMoyenneGenerale(double moyenneGenerale) {
		this.moyenneGeneralePromo = moyenneGenerale;
	}

}
