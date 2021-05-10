package com.promotion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotion.dao.DaoGenerator;

/**
 * Implementation de la classe admin
 */
@WebServlet("/admin")
public class Admin extends HttpServlet {
       
    /**
	 * On g�n�re un identifiant unique pour cette servlet
	 */
	private static final long serialVersionUID = 1002918475188212581L;
    
    @Override
	public void init() throws ServletException {
		DaoGenerator.init(this.getServletContext());
	}
    
    /**
     * Constructeur par defaut de la classe Admin
     */
    public Admin() {
    	super();
    	
    }

	/**
	 * Redefinition de la methode doGet
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : D�s qu'une exception est rencontr�e.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//on effectue une redirection de la requete vers la jsp admin
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	}

	/**
	 * Red�finition de la methode doPost
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : D�s qu'une exception est rencontr�e.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
