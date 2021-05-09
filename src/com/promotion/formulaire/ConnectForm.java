package com.promotion.formulaire;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.promotion.bean.EtudiantBean;
import com.promotion.dao.DaoEtudiant;

public class ConnectForm {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "motdepasse";

	
	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(String champ, String message) {
		erreurs.put(champ, message);
	}
	public void clearErreurs() {
		erreurs.clear();
	}

	public EtudiantBean connectUser(HttpServletRequest request) {
		// Récupération des champs du formulaire
		String email = getValueField(request, CHAMP_EMAIL);
		String motDePasse = getValueField(request, CHAMP_PASS);

		EtudiantBean etudiant = new EtudiantBean();
		// valider le champ du mail
		try {
			validateEmail(email);
		} catch (Exception e) {
			setErreurs(CHAMP_EMAIL, e.getMessage());
		}
		etudiant.setEmail(email);

		// valider le champ de mot de passe
		try {
			validatePassword(motDePasse);
		} catch (Exception e) {
			setErreurs(CHAMP_PASS, e.getMessage());
		}
		etudiant.setMotDePasse(motDePasse);

		// générer le résultat de la validation
		if (erreurs.isEmpty() && DaoEtudiant.loginExistsInDatabase(email, motDePasse)) {

			if (etudiant != null) {
				etudiant.setAdmin(DaoEtudiant.etudiantIsAdmin(etudiant));
			}
			etudiant.setNom(DaoEtudiant.getEtudiant(etudiant.getEmail()).getNom());
			etudiant.setPrenom(DaoEtudiant.getEtudiant(etudiant.getEmail()).getPrenom());
			etudiant.setDateDInscription(DaoEtudiant.getEtudiant(etudiant.getEmail()).getDateDInscription());
			// obtenir les notes de l'étudiant et sa promotion
			etudiant.setNomPromotion(
					DaoEtudiant.getNomPromotionFromIdPromotion(DaoEtudiant.getIdPromotionFromEmailEtudiant(email)));
			etudiant.setId(DaoEtudiant.getIdEtudiant(email));
			etudiant.setNotes(DaoEtudiant.getNotesEtudiant((int) etudiant.getId()));
			etudiant.setAnnee(DaoEtudiant.getAnneePromotionFromNomPromotion(etudiant.getNomPromotion()));

			// calculer la moyenne generale
			etudiant.setMoyenneGenerale(DaoEtudiant.calculeMoyenneGeneraleEtudiant(email));
			// System.out.println(etudiant); //faire afficher les infos de l'étudiant qui se connecte pour les tests
		} else {
			setErreurs(CHAMP_PASS, "Mot de passe invalide");
			return null;
		}
		clearErreurs();
		return etudiant;
	}

	private String getValueField(HttpServletRequest request, String nomChamp) {
		String value = request.getParameter(nomChamp);
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}

	private void validatePassword(String motDePasse) throws Exception {
		if (motDePasse != null) {
			if (motDePasse.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caracteres.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}

	private void validateEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		} else if (DaoEtudiant.emailExistsInDatabase(email) == false) { // verifier que l'email existe bien dans la
																		// base de donnees
			throw new Exception("Email introuvable.");
		}
	}
}
