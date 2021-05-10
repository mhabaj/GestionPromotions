package com.promotion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotion.bean.MatiereBean;
import com.promotion.formulaire.CreerMatiereForm;

/**
 * Servlet implementation de la classe CreerMatiere
 */
@WebServlet("/CreerMatiere")
public class CreerMatiere extends HttpServlet {
       
	/**
	 * On génère un identifiant unique pour cette servlet
	 */
	private static final long serialVersionUID = -6454972591321737805L;
	
	//Attributs permettant de relier les differents elements pour creer une matiere
	public static final String ATT_FORM = "form";
	public static final String ADMIN_VUE = "/WEB-INF/admin.jsp";

	/**
	 * Constructeur par défaut de la classe CrerMatiere
     * @see HttpServlet#HttpServlet()
     */
    public CreerMatiere() {
        super();
    }

	/**
	 * Redefinition de la methode doPost
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		/* Preparation de l'objet formulaire */
		CreerMatiereForm form = new CreerMatiereForm();

		/*
		 * Appel au traitement et a la validation de la requete, et recuperation du
		 * bean en resultant
		 */
		@SuppressWarnings("unused")
		MatiereBean matiere = form.createMatiere(request);
		
		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		this.getServletContext().getRequestDispatcher(ADMIN_VUE).forward(request, response);	
	}

}