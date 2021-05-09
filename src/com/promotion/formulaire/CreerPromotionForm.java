package com.promotion.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.promotion.bean.PromotionBean;
import com.promotion.dao.DaoEtudiant;

public class CreerPromotionForm {
	private static final String NOM_PROMOTION = "nomPromotion";
	private static final String ANNEE = "annee";
	
	   
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    private void setErreurs( String champ, String message ) {
        erreurs.put( champ, message );
    }

	public PromotionBean createPromotion(HttpServletRequest request) {
		PromotionBean promotion = new PromotionBean();
		String nomPromotion;
		try {
			nomPromotion = getValueField(request, NOM_PROMOTION);
			promotion.setNomPromotion(nomPromotion);
		} catch (Exception e) {
			setErreurs(NOM_PROMOTION, e.getMessage());
			return null;
		}
		String annee;
		try {
			annee = getValueField(request, ANNEE);
			promotion.setAnnee(Integer.parseInt(annee));
		} catch (Exception e) {
			setErreurs(ANNEE, e.getMessage());
			return null;
		}
		
		if(erreurs.isEmpty()) {
			DaoEtudiant.creerPromotion(promotion);
			request.getSession().setAttribute( "promotions", DaoEtudiant.getAllPromotions());
			request.getSession().setAttribute( "etudiants", DaoEtudiant.getAllNonAdminEtudiants());
		} else {
			return null;
		}
		return promotion;
	}
	
	private String getValueField(HttpServletRequest request, String field) throws Exception {
		String value = request.getParameter( field );
        if ( value == null || value.trim().length() == 0 ) {
        	throw new Exception("champ invalide");
        } else {
            return value.trim();
        }
	}
    

}
