package com.promotion.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.promotion.bean.EtudiantBean;
import com.promotion.dao.DaoEtudiant;
import com.promotion.dao.DaoGenerator;
import com.promotion.formulaire.ConnectForm;

@WebServlet("/connexion")
public class Connexion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -687776519470308972L;
	public static final String ATT_USER = "etudiant";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionEtudiant";
	public static final String ATT_USERS = "etudiants";
	public static final String VUE = "/WEB-INF/connexion.jsp";
	private static final String ADMIN = "/WEB-INF/admin.jsp";
	private static final String USER = "/WEB-INF/etudiant.jsp";

	@Override
	public void init() throws ServletException {
		DaoGenerator.init(this.getServletContext());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ConnectForm form = new ConnectForm();

		/* Traitement de la requête et récupération du bean en résultant */
		EtudiantBean etudiant = form.ConnectUSer(request);

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/**
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
		 * Utilisateur à la session, sinon suppression du bean de la session.
		 */
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, etudiant);
			session.setAttribute("admin",etudiant.getAdmin());
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
		}

		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, etudiant);

		if (form.getErreurs().isEmpty()) {
			if (etudiant.getAdmin()) {
				session.setAttribute(ATT_USERS, DaoEtudiant.getAllNonAdminEtudiants());
				this.getServletContext().getRequestDispatcher(ADMIN).forward(request, response);
			}

			else
				this.getServletContext().getRequestDispatcher(USER).forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}

	}
}