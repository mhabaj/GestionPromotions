package com.promotion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.promotion.dao.DaoEtudiant;

/**
 * Servlet implementation de la classe DeleteEtudiant
 */
@WebServlet("/DeleteEtudiant")
public class DeleteEtudiant extends HttpServlet {
       
    /**
	 * On genere un identifiant unique pour cette servlet
	 */
	private static final long serialVersionUID = 1989475658989256006L;

	/**
	 * Constructeur par défaut de la classe DeleteEtudiant
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEtudiant() {
        super();
    }

	/**
	 * Redefinition de la methode doGet
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : Des qu'une exception est rencontree
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//on efface l'etudiant dont le resultat de la requete correspond a son id
		DaoEtudiant.deleteEtudiant(Integer.parseInt(request.getParameter("etudiantId")));	
		
		// on recupere la session a partir de la requete
		request.getSession().setAttribute( "promotions", DaoEtudiant.getAllPromotions());
		request.getSession().setAttribute( "etudiants", DaoEtudiant.getAllNonAdminEtudiants());
		
		// on effectue une redirection de la requete vers la jsp admin
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	}

	/**
	 * Redefinition de la methode doPost
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : Des qu'une exception est rencontree
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}