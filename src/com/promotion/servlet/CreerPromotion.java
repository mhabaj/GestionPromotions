package com.promotion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.promotion.bean.PromotionBean;
import com.promotion.formulaire.CreerPromotionForm;

/**
 * Servlet implementation class CreerPromotion
 */
@WebServlet("/CreerPromotion")
public class CreerPromotion extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3918794883223358696L;
	public static final String ATT_FORM = "form";
	public static final String ADMIN_VUE = "/WEB-INF/admin.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerPromotion() {
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
		CreerPromotionForm form = new CreerPromotionForm();

		/*
		 * Appel au traitement et à la validation de la requête, et récupération du
		 * bean en résultant
		 */
		@SuppressWarnings("unused")
		PromotionBean promotion = form.createPromotion(request);
		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		if (form.getErreurs().isEmpty())
			this.getServletContext().getRequestDispatcher(ADMIN_VUE).forward(request, response);
		
	}

}
