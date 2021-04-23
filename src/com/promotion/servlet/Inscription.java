package com.promotion.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotion.bean.EtudiantBean;
import com.promotion.dao.DaoGenerator;
import com.promotion.formulaire.RegisterForm;


@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8596500040526198588L;
	public static final String ATT_USER = "etudiant";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/inscription.jsp";
	public static final String CONNEXION_VUE = "/WEB-INF/connexion.jsp";

	@Override
	public void init() throws ServletException {
		DaoGenerator.init(this.getServletContext());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		RegisterForm form = new RegisterForm();

		/*
		 * Appel au traitement et à la validation de la requête, et récupération
		 * du bean en résultant
		 */
		EtudiantBean etudiant = form.registerEtudiant(request);

		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, etudiant);
		if (form.getErreurs().isEmpty())
			this.getServletContext().getRequestDispatcher(CONNEXION_VUE).forward(request, response);
		else
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}