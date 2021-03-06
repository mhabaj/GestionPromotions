package com.promotion.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogOut extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -755592709481778814L;
	
	/**
	 * redefinition de la methode doget ou on ferme la session lors de la deconnexion
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
			session = null;
		}
		response.sendRedirect("connexion");
	}

}
