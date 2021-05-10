package com.promotion.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotion.bean.EtudiantBean;
import com.promotion.dao.DaoEtudiant;
import com.promotion.dao.DaoGenerator;
import com.promotion.formulaire.RegisterForm;

/**
 * Servlet implementation de la classe Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	
	/**
	 * On genere un identifiant unique pour cette servlet
	 */
	private static final long serialVersionUID = -8596500040526198588L;
	
	//Attributs permettant de relier les differents elements de la page d'inscription
	public static final String ATT_USER = "etudiant";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/inscription.jsp";
	public static final String CONNEXION_VUE = "/WEB-INF/connexion.jsp";
	private static final String PROMOS = "promos";

	@Override
	public void init() throws ServletException {
		DaoGenerator.init(this.getServletContext());
		this.getServletContext().setAttribute(PROMOS, DaoEtudiant.getAllPromotions());

	}

	/**
	 * Redefinition de la methode doGet
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : Des qu'une exception est rencontree
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * Redefinition de la methode doPost
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : Des qu'une exception est rencontree
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Preparation de l'objet formulaire */
		RegisterForm form = new RegisterForm();

		/*
		 * Appel au traitement et a la validation de la requete, et recuperation du
		 * bean en resultant
		 */
		EtudiantBean etudiant = form.registerEtudiant(request);
		
		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, etudiant);
		
		// si il n'y a pas d'erreur 
		if (form.getErreurs().isEmpty())
			this.getServletContext().getRequestDispatcher(CONNEXION_VUE).forward(request, response);
		else
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}
}