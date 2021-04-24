package com.promotion.bean;

public class NoteBean {

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
	
	
}
