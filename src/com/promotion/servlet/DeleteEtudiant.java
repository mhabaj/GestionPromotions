package com.promotion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.promotion.dao.DaoEtudiant;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteEtudiant")
public class DeleteEtudiant extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1989475658989256006L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEtudiant() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoEtudiant.deleteEtudiant(Integer.parseInt(request.getParameter("etudiantId")));
		HttpSession session = request.getSession();
		session.setAttribute("etudiants", DaoEtudiant.getAllNonAdminEtudiants());
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}