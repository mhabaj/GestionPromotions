package com.promotion.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.promotion.bean.EtudiantBean;
import com.promotion.bean.PromotionBean;
import com.promotion.dao.DaoEtudiant;

public class RegisterForm {
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS  = "motdepasse";
    private static final String CHAMP_CONF  = "confirmation";
    private static final String CHAMP_NOM   = "nom";
    private static final String CHAMP_ADMIN = "admin";
    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_PROMOTION = "nomPromotion";
    private static final String CHAMP_ANNEE = "annee";
    
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
    
    public EtudiantBean registerEtudiant(HttpServletRequest request) {
    	EtudiantBean etudiant = new EtudiantBean();
        String email = getValueField( request, CHAMP_EMAIL );
        String motDePasse = getValueField( request, CHAMP_PASS );
        String confirmation = getValueField( request, CHAMP_CONF );
        String nom = getValueField( request, CHAMP_NOM );
        String admin = getValueField( request, CHAMP_ADMIN );
        String prenom = getValueField(request, CHAMP_PRENOM);
        String promotion = getValueField(request, CHAMP_PROMOTION);
        String annee = getValueField(request, CHAMP_ANNEE);
        /* Validation du champ email. */
        try {
            validateEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        etudiant.setEmail(email);
        
        /* Validation des champs mot de passe et confirmation. */
        try {
            validatePassword( motDePasse, confirmation );
        } catch ( Exception e ) {
        	setErreur( CHAMP_PASS, e.getMessage() );
        }
        etudiant.setMotDePasse(motDePasse);
        
        /* Validation du champ nom. */
        try {
            validateName( nom );
        } catch ( Exception e ) {
        	setErreur( CHAMP_NOM, e.getMessage() );
        }   
        etudiant.setNom(nom);
        
        /*Validation du champ pr�nom*/
        try {
        	validateSurname(prenom);
        }catch(Exception e) {
        	setErreur(CHAMP_PRENOM, e.getMessage());
        }
        etudiant.setPrenom(prenom);
        
        /*Validation de la promo de l'�tudiant*/
        try {
        	if(DaoEtudiant.promotionExists(promotion)) {
        		etudiant.setNomPromotion(promotion);
        		etudiant.setAnnee(DaoEtudiant.getAnneeEtudiant(promotion));
        	}else {
        		etudiant.setNomPromotion(promotion);
        		etudiant.setAnnee(DaoEtudiant.getAnneeEtudiant(promotion));
        		DaoEtudiant.creerPromotion(new PromotionBean(promotion, Integer.parseInt(annee)));
        	}
        }catch(Exception e) {
        	setErreur(CHAMP_PROMOTION, e.getMessage());
        }
        
        /*V�rification du checkbox admin*/
        if(admin == null) {
        	etudiant.setAdmin(false);
        }else {
        	etudiant.setAdmin(true);
        }
        
        /* Initialisation du r�sultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succ�s de l'inscription.";
            //cr�er l'�tudiant dans la base de donn�es
            DaoEtudiant.creerEtudiant(etudiant);
            System.out.println(etudiant + " ESSAI");
        } else {
            resultat = "�chec de l'inscription.";
        }
		return etudiant;
    }

    private String getValueField(HttpServletRequest request, String field) {
		String value = request.getParameter( field );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value.trim();
        }
	}
	
	private void validateSurname( String prenom ) throws Exception {
        if ( prenom != null && prenom.length() < 3 ) {
            throw new Exception( "Le pr�nom de l'�tudiant doit contenir au moins 3 caract�res." );
        } else if ( prenom == null ) {
            throw new Exception( "Le pr�nom de l'�tudiant doit contenir au moins 3 caract�res." );
        }
    }
	
	private void validateEmail(String email) throws Exception{
		if ( email != null && email.trim().length() != 0 ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}
	
	private void validatePassword(String motDePasse, String confirmation) throws Exception{
		if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new Exception( "Les mots de passe entr�s sont diff�rents, merci de les saisir � nouveau." );
            } else if ( motDePasse.length() < 3 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caract�res." );
            }
        } else {
            throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
        }
	}
	
	private void validateName( String nom ) throws Exception {
        if ( nom != null && nom.length() < 3 ) {
            throw new Exception( "Le nom de l'�tudiant doit contenir au moins 3 caract�res." );
        } else if ( nom == null ) {
            throw new Exception( "Le nom de l'�tudiant doit contenir au moins 3 caract�res." );
        }
    }
}
