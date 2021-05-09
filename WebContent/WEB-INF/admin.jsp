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
<p>Moyenne generale par promotion: </p>

	<table border="1">
		<tr>
			<th>Nom de la promotion</th>
			<th>Moyenne Générale</th>
		</tr>
		<c:forEach items="${sessionScope.promotions}" var="listItem">
			<tr>
				<td align="center"> ${listItem.nomPromotion}</td>
				<td align="center"> ${listItem.moyenneGeneralePromo}</td>
			</tr>
		</c:forEach>
	</table>


	<p>Liste des étudiants non Admin</p>

	<table border="1">
		<tr>
			<th>Email</th>
			<th>Mot de Passe</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Date d'inscription</th>
			<th>Moyenne Générale</th>
			<th> </th>
		</tr>
		<c:forEach items="${sessionScope.etudiants}" var="listItem">
			<tr>
				<td> ${listItem.email}</td>
				<td>${listItem.motDePasse}</td>
				<td align="center"> ${listItem.nom}</td>
				<td align="center">${listItem.prenom}</td>
				<td align="center"> ${listItem.dateDInscription}</td>
				<td align="center"> ${listItem.moyenneGenerale}</td>
				<td> <a href="DeleteEtudiant?etudiantId=${listItem.id}">Supprimer</a> </td>
			</tr>
		</c:forEach>
	</table>

	
	
	<form method="post" action="CreerPromotion" id="creerPromotionForm">
            <fieldset>
                <legend>créer une promotion</legend>

                <label for="nomPromotion">Nom de la promotion <span class="requis">*</span></label>
                <input type="text" id="nomPromotion" name="nomPromotion" value="<c:out value="${promotion.nomPromotion}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['nomPromotion']}</span>
                <br />

                <label for="annee">année d'obtention du diplôme <span class="requis">*</span></label>
                <input type="number" min="1900" id="annee" name="annee" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['annee']}</span>
                <br />
                
                <input type="submit" value="creer une promotion" class="sansLabel" />
                <br />
                
            </fieldset>
        </form>
        
        <form method="post" action="CreerMatiere" id="creerMatiereForm">
            <fieldset>
                <legend>créer une matière</legend>

                <label for="nomMatiere">Nom de la matière <span class="requis">*</span></label>
                <input type="text" id="nomMatiere" name="nomMatiere" value="<c:out value="${matiere.nomMatiere}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['nomMatiere']}</span>
                <br />

                <label for="coefficientMatiere">Coefficient de la matière <span class="requis">*</span></label>
                <input type="number" min="0.0" step="0.1" id="coefficientMatiere" name="coefficientMatiere" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['coefficientMatiere']}</span>
                <br />
                
                <label for="nomPromotionMatiere">promotion</label>
                <select form="creerMatiereForm" id="nomPromotionMatiere" name="nomPromotionMatiere"> 
                <c:forEach items="${sessionScope.promotions}" var="listItem">
						<option value = "${listItem.nomPromotion}">${listItem.nomPromotion} </option>
				</c:forEach>
                </select>                
                <span class="erreur">${form.erreurs['nomPromotionMatiere']}</span>
                <br />
                
                <input type="submit" value="creer une matière" class="sansLabel" />
                <br />
                
            </fieldset>
        </form>
	
	<a href="logout">Se Déconnecter</a>
</body>
</html>