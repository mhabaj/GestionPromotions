<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Etudiant</title>
<link type="text/css" rel="stylesheet" href="css/form.css" />
</head>
<body>
	<p>Info Etudiant
	<p>
		<c:if test="${!empty sessionScope.sessionEtudiant}">
			<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
			<p class="succes">Vous êtes connecté(e) avec l'adresse :
				${sessionScope.sessionEtudiant.email}</p>
			<br>
			<p>Vos Infos
			<p>
			<p>Nom : ${sessionScope.sessionEtudiant.nom } </p>
			<p>Prenom : ${sessionScope.sessionEtudiant.prenom }</p>
			<p>
			<p>Date d'inscription :
				${sessionScope.sessionEtudiant.dateDInscription }
			<p>
			<p>Année de la promotion : ${sessionScope.sessionEtudiant.annee }</p>
			<p>Moyenne générale de l'étudiant : ${sessionScope.sessionEtudiant.moyenneGenerale }</p>
			<p>
			
			<p>Liste des notes de l'étudiant</p>

			<table border="1">
				<tr>
					<th>Note</th>
					<th>Matière</th>
					<th>Coefficient</th>
				</tr>
				<c:forEach items="${sessionScope.notes}" var="listItem">
					<tr>
						<td> ${listItem.note}</td>
						<td> ${listItem.matiere.nomMatiere}</td>
						<td> ${listItem.matiere.coefficientMatiere}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<a href="logout">Se Déconnecter</a>
</body>
</html>