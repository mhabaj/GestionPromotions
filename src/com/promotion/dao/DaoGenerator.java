package com.promotion.dao;

import javax.servlet.ServletContext;

/**
 * classe principale de dao permettant de spécifier les identifiants de connexion à la base de données, ces données sont spécifiées dans le web.xml.
 * @author Sean
 *
 */
public class DaoGenerator {
	protected static String dbURL;
	protected static String dbLogin;
	protected static String dbPassword;

	public static void init(ServletContext context) {
		try {

			Class.forName(context.getInitParameter("JDBC_DRIVER"));
			dbURL = context.getInitParameter("JDBC_URL");
			dbLogin = context.getInitParameter("JDBC_LOGIN");
			dbPassword = context.getInitParameter("JDBC_PASSWORD");

		} catch (Exception exception) {

			exception.printStackTrace();

		}
	}
}
