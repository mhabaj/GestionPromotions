package com.promotion.bean;

import java.io.Serializable;

<<<<<<< HEAD
public class NoteBean implements Serializable{
=======
public class NoteBean implements Serializable {
>>>>>>> sean

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833254614925218485L;
	private long idNote;
	private MatiereBean matiere;
	private Double note;
	
	public NoteBean() {
	}

	public long getIdNote() {
		return idNote;
	}

	public void setIdNote(long idNote) {
		this.idNote = idNote;
	}

	public MatiereBean getMatiere() {
		return matiere;
	}

	public void setMatiere(MatiereBean matiere) {
		this.matiere = matiere;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}
	
	public String toString() {
		return "Matiere : " + matiere + " ,Note :" + note + "\n";
	}
	
	
}
