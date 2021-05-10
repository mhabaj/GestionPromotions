package com.promotion.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.promotion.bean.EtudiantBean;
import com.promotion.bean.PromotionBean;
import com.promotion.dao.DaoEtudiant;
import com.promotion.dao.DaoGenerator;
import com.promotion.formulaire.ConnectForm;

/**
 * Implementation de la classe connexion
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {

	/**
	 * On genere un identifiant unique pour cette servlet
	 */
	private static final long serialVersionUID = -687776519470308972L;
	
	//Attributs permettant de relier les differents elements de la page de connexion
	public static final String ATT_USER = "etudiant";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionEtudiant";
	public static final String ATT_USERS = "etudiants";
	public static final String ATT_PROMOS = "promotions";
	public static final String VUE_CONNEXION = "/WEB-INF/connexion.jsp";
	private static final String ADMIN = "/WEB-INF/admin.jsp";
	private static final String USER = "/WEB-INF/etudiant.jsp";
	private static final String NOTES = "notes";

	@Override
	public void init() throws ServletException {
		DaoGenerator.init(this.getServletContext());
	}

	/**
	 * Redefinition de la methode doGet
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : Des qu'une exception est rencontree
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VUE_CONNEXION).forward(request, response);
	}

	/**
	 * Redefinition de la methode doPost
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : Des qu'une exception est rencontree
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Preparation de l'objet formulaire */
		ConnectForm form = new ConnectForm();

		/* Traitement de la requete et recuperation du bean en resultant */
		EtudiantBean etudiant = form.connectUser(request);
		ArrayList<PromotionBean> promos = DaoEtudiant.getAllPromotions();
		
		for(PromotionBean currentPromo: promos) {
			currentPromo.setMoyenneGeneralePromo(
					DaoEtudiant.calculeMoyenneGeneralePromo(currentPromo));
		}
		
		/* Recuperation de la session depuis la requete */
		HttpSession session = request.getSession();

		/**
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean Utilisateur
		 * a la session, sinon suppression du bean de la session.
		 */
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, etudiant);
			session.setAttribute("admin", etudiant.getAdmin());
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
		}

		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, etudiant);

		// Si l'on ne recontre pas d'erreur dans le formulaire
		if (form.getErreurs().isEmpty()) {
			
			//si l'utilisateur est connecte en administrateur
			if (etudiant.getAdmin()) {
				
				session.setAttribute(ATT_PROMOS, promos);
				session.setAttribute(ATT_USERS, DaoEtudiant.getAllNonAdminEtudiants());
				this.getServletContext().getRequestDispatcher(ADMIN).forward(request, response);
			}
			
			// si l'utilisateur est un etudiant
			else {
				session.setAttribute(ATT_USER, etudiant);
				session.setAttribute(NOTES, etudiant.getNotes());
				this.getServletContext().getRequestDispatcher(USER).forward(request, response);
			}
		// on redirige vers la page de connexion
		} else {
			this.getServletContext().getRequestDispatcher(VUE_CONNEXION).forward(request, response);
		}

	}
}