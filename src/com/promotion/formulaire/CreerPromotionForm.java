package com.promotion.formulaire;

import java.util.ArrayList;
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
    
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

	public PromotionBean createPromotion(HttpServletRequest request) {
		
		PromotionBean promotion = new PromotionBean();
		String nomPromotion = getValueField(request, NOM_PROMOTION);
		String annee = getValueField(request, ANNEE);
		
		promotion.setNomPromotion(nomPromotion);
		promotion.setAnnee(Integer.parseInt(annee));
		
		if(erreurs.isEmpty()) {
			DaoEtudiant.creerPromotion(promotion);
			ArrayList<PromotionBean> promos = DaoEtudiant.getAllPromotions();
			for(PromotionBean currentPromo: promos) {
				currentPromo.setMoyenneGeneralePromo(
						DaoEtudiant.calculeMoyenneGeneralePromo(currentPromo));
			}
			request.getSession().setAttribute("promotions", promos);
		} else {
			return null;
		}
		

		return promotion;
	}
	
	
	private String getValueField(HttpServletRequest request, String field) {
		String value = request.getParameter( field );
        if ( value == null || value.trim().length() == 0 ) {
        	setErreur(NOM_PROMOTION, "erreur de saisie nom promotion");
        	setErreur(ANNEE, "erreur de saisie de l'année");
            return null;
        } else {
            return value.trim();
        }
	}
    

}
