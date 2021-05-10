package com.promotion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation de la classe Etudiant
 */
@WebServlet("/etudiant")
public class Etudiant extends HttpServlet {
       
    /**
	 * On genere un identifiant unique pour cette servlet
	 */
	private static final long serialVersionUID = -9071986150205673670L;

	/**
	 * Constructeur par défaut de la classe Etudiant
     * @see HttpServlet#HttpServlet()
     */
    public Etudiant() {
        super();
    }

	/**
	 * Redefinition de la methode doGet
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : Des qu'une exception est rencontree
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/etudiant.jsp").forward(request, response);
	}

	/**
	 * Redefinition de la methode doPost
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : Des qu'une exception est rencontree
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// on effectue une redirection de la requete vers la jsp etudiant
        this.getServletContext().getRequestDispatcher("/WEB-INF/etudiant.jsp").forward(request, response);
	}

}