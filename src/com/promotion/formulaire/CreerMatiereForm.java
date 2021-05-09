package com.promotion.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.promotion.bean.MatiereBean;
import com.promotion.dao.DaoEtudiant;

public class CreerMatiereForm {
	private static final String NOM_MATIERE = "nomMatiere";
	private static final String COEFFICIENT_MATIERE = "coefficientMatiere";
	private static final String PROMOTION_SELECTIONNEE = "nomPromotion";
	
	   
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

	public MatiereBean createMatiere(HttpServletRequest request) {
		
		MatiereBean matiere = new MatiereBean();
		String nomMatiere = getValueField(request, NOM_MATIERE);
		String coefficientMatiere = getValueField(request, COEFFICIENT_MATIERE);
		String nomPromotion = getValueField(request, PROMOTION_SELECTIONNEE);
		
		matiere.setNomMatiere(nomMatiere);
		matiere.setCoefficientMatiere(Double.parseDouble(coefficientMatiere));
		
		if(erreurs.isEmpty()) {
			DaoEtudiant.creerMatiere(matiere, nomPromotion);
		} else {
			return null;
		}
		

		return matiere;
	}
	
	
	private String getValueField(HttpServletRequest request, String field) {
		String value = request.getParameter( field );
        if ( value == null || value.trim().length() == 0 ) {
        	setErreur(NOM_MATIERE, "erreur de saisie nom promotion");
        	setErreur(COEFFICIENT_MATIERE, "erreur de saisie de l'année");
            return null;
        } else {
            return value.trim();
        }
	}
    
}
