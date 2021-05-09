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
 * Servlet implementation class CreerMatiere
 */
@WebServlet("/CreerMatiere")
public class CreerMatiere extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -6454972591321737805L;
	public static final String ATT_FORM = "form";
	public static final String ADMIN_VUE = "/WEB-INF/admin.jsp";

	/**
     * @see HttpServlet#HttpServlet()
     */
    public CreerMatiere() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		/* Préparation de l'objet formulaire */
		CreerMatiereForm form = new CreerMatiereForm();

		/*
		 * Appel au traitement et à la validation de la requête, et récupération du
		 * bean en résultant
		 */
		@SuppressWarnings("unused")
		MatiereBean matiere = form.createMatiere(request);
		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		if (form.getErreurs().isEmpty())
			this.getServletContext().getRequestDispatcher(ADMIN_VUE).forward(request, response);
		
	}

}
