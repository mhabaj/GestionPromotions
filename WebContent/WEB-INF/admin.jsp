<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Etudiant admin</title>
</head>
<body>
	<p>Liste des étudiants non Admin</p>

	<table border="1">
		<tr>
			<th>Email</th>
			<th>Mot de Passe</th>
			<th>Nom</th>
			<th>Date d'inscription</th>
			<th> </th>
		</tr>
		<c:forEach items="${sessionScope.etudiants}" var="listItem">
			<tr>
				<td> ${listItem.email}</td>
				<td>${listItem.motDePasse}</td>
				<td align="center"> ${listItem.nom}</td>
				<td align="center"> ${listItem.dateDInscription}</td>
				<td> <a href="DeleteEtudiant?etudiantId=${listItem.id}">Supprimer</a> </td>
			</tr>
		</c:forEach>
	</table>

	<a href="logout">Se Déconnecter</a>
</body>
</html>