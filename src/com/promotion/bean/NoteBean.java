package com.promotion.bean;

import java.io.Serializable;

/**
 * @brief classe Bean des notes qui recevront les données de la base de données.
 * @author Sean Anica & Alhabaj Mahmod & Rondeau Juliette
 * @file EtudiantBean.java
 *
 */
public class NoteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833254614925218485L;
	private long idNote;
	private MatiereBean matiere;
	private Double note;
	
	/**
	 * constructeur par defaut de la classe 
	 */
	public NoteBean() {
	}

	/**
	 * getter de l'attribut id de la note
	 * @return idNote
	 */
	public long getIdNote() {
		return idNote;
	}

	/**
	 * setter de l'attribut id de la note
	 * @return
	 */
	public void setIdNote(long idNote) {
		this.idNote = idNote;
	}

	/**
	 * getter de l'attribut matiere liee a la note
	 * @return matiere
	 */
	public MatiereBean getMatiere() {
		return matiere;
	}

	/**
	 * getter de l'attribut matiere liee a la note
	 * @return
	 */
	public void setMatiere(MatiereBean matiere) {
		this.matiere = matiere;
	}

	/**
	 * getter de l'attribut valeur de la note
	 * @return note
	 */
	public Double getNote() {
		return note;
	}

	/**
	 * setter de l'attribut valeur de la note
	 * @return
	 */
	public void setNote(Double note) {
		this.note = note;
	}
	
	/**
	 * redefinition de la fonction toString de la classe
	 */
	public String toString() {
		return "Matiere : " + matiere + " ,Note :" + note + "\n";
	}
	
	
}
