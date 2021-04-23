<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Etudiant</title>
</head>
<body>
	 <p> Info Etudiant <p>
	 <c:if test="${!empty sessionScope.sessionEtudiant}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionEtudiant.email}</p>
                    <br>
                    <p> Vos Infos <p>
                    <p> Nom : ${sessionScope.sessionEtudiant.nom } <p>
                    <p> Date d'inscription : ${sessionScope.sessionEtudiant.dateDInscription } <p>
                    <%-- rajouter d'autres d'infos de l'étudiant ici PAS ENCORE TERMINE --%>                    
      </c:if>

	<a href="logout">Se Déconnecter</a>
</body>
</html>