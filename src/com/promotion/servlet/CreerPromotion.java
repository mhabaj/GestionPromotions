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
 * Servlet implementation de la classe CreerPromotion
 */
@WebServlet("/CreerPromotion")
public class CreerPromotion extends HttpServlet {
	
	/**
	 * On génère un identifiant unique pour cette servlet
	 */
	private static final long serialVersionUID = 3918794883223358696L;
	
	//Attributs permettant de relier les differents elements pour creer une promotion
	public static final String ATT_FORM = "form";
	public static final String ADMIN_VUE = "/WEB-INF/admin.jsp";
       
    /**
     * Constructeur par défaut de la classe CrerPromotion
     * @see HttpServlet#HttpServlet()
     */
    public CreerPromotion() {
        super();
    }

	/**
	 * Redefinition de la methode doPost
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @throws ServletException : Des qu'une exception est rencontree
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Preparation de l'objet formulaire */
		CreerPromotionForm form = new CreerPromotionForm();

		/*
		 * Appel au traitement et Ã  la validation de la requete, et recuperation du
		 * bean en resultant
		 */
		@SuppressWarnings("unused")
		PromotionBean promotion = form.createPromotion(request);
		
		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		this.getServletContext().getRequestDispatcher(ADMIN_VUE).forward(request, response);
	}

}
