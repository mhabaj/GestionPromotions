package com.promotion.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.promotion.bean.EtudiantBean;
import com.promotion.bean.MatiereBean;
import com.promotion.dao.DaoEtudiant;

public class CreerMatiereForm {
	private static final String NOM_MATIERE = "nomMatiere";
	private static final String COEFFICIENT_MATIERE = "coefficientMatiere";
	private static final String PROMOTION_SELECTIONNEE = "nomPromotionMatiere";
	
	   
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

    /**
     * créer une matiere et l'ajouter dans la base de données à partir des champs du formulaire correspondant.
     * @param request
     * @return matiere
     */
	public MatiereBean createMatiere(HttpServletRequest request) {
		
		MatiereBean matiere = new MatiereBean();
		String nomMatiere;
		try {
			nomMatiere = getValueField(request, NOM_MATIERE);
			matiere.setNomMatiere(nomMatiere);
		} catch (Exception e1) {
			setErreur(NOM_MATIERE, "erreur de saisie nom de la matière");
			return null;
		}
		String coefficientMatiere;
		try {
			coefficientMatiere = getValueField(request, COEFFICIENT_MATIERE);
			matiere.setCoefficientMatiere(Double.parseDouble(coefficientMatiere));
		} catch (Exception e1) {
        	setErreur(COEFFICIENT_MATIERE, "erreur de saisie de l'année");
        	return null;
		}
		String nomPromotion="";
		try {
			nomPromotion = getValueField(request, PROMOTION_SELECTIONNEE);
		} catch (Exception e1) {
			setErreur(PROMOTION_SELECTIONNEE, "promotion Selectionnée invalide.");
			return null;
		}
		
		if(erreurs.isEmpty()) {
			try {
				DaoEtudiant.creerMatiere(matiere, nomPromotion);
				
				/*on ajoute des étudiants et leurs notes générées au hasard dans la base de données, ces étudiants seront liés à la matiere ajoutée*/
				int numeroRandom = (int) (Math.random() * 100000-5+1);
				
				EtudiantBean etudiant1 = new EtudiantBean();
				EtudiantBean etudiant2 = new EtudiantBean();
				EtudiantBean etudiant3 = new EtudiantBean();
				etudiant1.setAdmin(false);
				etudiant1.setEmail("etudiant" + numeroRandom + "@gmail.com");
				etudiant1.setMotDePasse("123");
				etudiant1.setNom("etudiant" + numeroRandom);
				etudiant1.setPrenom("etudiant" + numeroRandom);
				etudiant1.setNomPromotion(nomPromotion);
				DaoEtudiant.creerEtudiant(etudiant1);
				
				numeroRandom = (int) (Math.random() * 100000-5+1);
				etudiant2.setAdmin(false);
				etudiant2.setEmail("etudiant" + numeroRandom + "@gmail.com");
				etudiant2.setMotDePasse("123");
				etudiant2.setNom("etudiant" + numeroRandom);
				etudiant2.setPrenom("etudiant" + numeroRandom);
				etudiant2.setNomPromotion(nomPromotion);
				DaoEtudiant.creerEtudiant(etudiant2);
				
				numeroRandom = (int) (Math.random() * 100000-5+1);
				etudiant3.setAdmin(false);
				etudiant3.setEmail("etudiant" + numeroRandom + "@gmail.com");
				etudiant3.setMotDePasse("123");
				etudiant3.setNom("etudiant" + numeroRandom);
				etudiant3.setPrenom("etudiant" + numeroRandom);
				etudiant3.setNomPromotion(nomPromotion);
				DaoEtudiant.creerEtudiant(etudiant3);
				
				request.getSession().setAttribute( "promotions", DaoEtudiant.getAllPromotions());
				request.getSession().setAttribute( "etudiants", DaoEtudiant.getAllNonAdminEtudiants());

				
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		

		return matiere;
	}
	
	/**
	 * obtenir les valeurs des champs du formulaire correspondant
	 * @param request
	 * @param field
	 * @return value
	 * @throws Exception
	 */
	private String getValueField(HttpServletRequest request, String field) throws Exception {
		String value = request.getParameter( field );
        if ( value == null || value.trim().length() == 0 ) {
            throw new Exception("saisie invalide.");
        } else {
            return value.trim();
        }
	}
    
}
