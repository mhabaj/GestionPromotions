package com.promotion.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @brief classe Bean des promotions qui recevront les données de la base de données.
 * @author Sean Anica & Alhabaj Mahmod & Rondeau Juliette
 * @file EtudiantBean.java
 *
 */
public class PromotionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 987883375310610908L;
	private String nomPromotion;
	private int annee;
	private double moyenneGeneralePromo;

	private ArrayList<EtudiantBean> etudiants;
	private ArrayList<MatiereBean> matieres;

	/**
	 * constructeur par défaut de la classe
	 */
	public PromotionBean() {

	}

	/**
	 * constructeur de confort de la classe
	 * @param nomPromotion
	 * @param annee
	 */
	public PromotionBean(String nomPromotion, int annee) {
		this.nomPromotion = nomPromotion;
		this.annee = annee;
	}

	/**
	 * getter de l'attribut nom de la promotion
	 * @return nomPromotion
	 */
	public String getNomPromotion() {
		return nomPromotion;
	}

	/**
	 * setter de l'attribut nom de la promotion
	 * @return 
	 */
	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}
	
	/**
	 * getter de l'attribut annee d'obtention du diplôme de la promotion
	 * @return annee
	 */
	public int getAnnee() {
		return annee;
	}

	/**
	 * setter de l'attribut annee d'obtention du diplôme de la promotion
	 * @return
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}

	/**
	 * getter de la liste des etudiants de la promotion
	 * @return etudiants
	 */
	public ArrayList<EtudiantBean> getEtudiants() {
		return etudiants;
	}

	/**
	 * setter de la liste des etudiants de la promotion
	 * @return
	 */
	public void setEtudiants(ArrayList<EtudiantBean> etudiants) {
		this.etudiants = etudiants;
	}

	/**
	 * getter de la liste des matieres de la promotion
	 * @return matieres
	 */
	public ArrayList<MatiereBean> getMatieres() {
		return matieres;
	}

	/**
	 * setter de la liste des matieres de la promotion
	 * @return 
	 */
	public void setMatieres(ArrayList<MatiereBean> matieres) {
		this.matieres = matieres;
	}

	/**
	 * getter de la moyenne generale de la promo
	 * @return the moyenneGeneralePromo
	 */
	public double getMoyenneGeneralePromo() {
		return moyenneGeneralePromo;
	}

	/**
	 * setter de la moyenne generale de la promo
	 * @param moyenneGeneralePromo the moyenneGeneralePromo to set
	 */
	public void setMoyenneGeneralePromo(double moyenneGeneralePromo) {
		this.moyenneGeneralePromo = moyenneGeneralePromo;
	}

	



}
