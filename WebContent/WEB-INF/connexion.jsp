<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Connexion</title>
<link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>
	<form method="post" action="connexion">
		<fieldset>
			<legend>Connexion</legend>
			<p>Vous pouvez nous connecter via ce formulaire.</p>

			<label for="nom">Adresse email <span class="requis">*</span></label>
			<input type="email" id="email" name="email"
				value="<c:out value="${etudiant.email}"/>" size="20"
				maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>
			<br /> <label for="motdepasse">Mot de passe <span
				class="requis">*</span></label> <input type="password" id="motdepasse"
				name="motdepasse" value="" size="20" maxlength="20" /> <span
				class="erreur">${form.erreurs['motdepasse']}</span> <br /> <input
				type="submit" value="Connexion" class="sansLabel" /> <br />

			<p class="${empty form.erreurs ? 'success' : 'erreur'}">${form.resultat}</p>
			<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:if test="${!empty sessionScope.sessionEtudiant}">
				<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
				<p class="success">Vous êtes connecté(e) avec l'adresse :
					${sessionScope.sessionEtudiant.email}</p>
			</c:if>

		</fieldset>
	</form>
	<a href="inscription">S'inscrire</a>
</body>
</html>