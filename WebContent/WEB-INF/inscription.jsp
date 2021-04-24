<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="css/form.css" />
    </head>
    <body>
        <form method="post" action="inscription">
            <fieldset>
                <legend>Inscription</legend>
                <p>Inscrivez-vous ici</p>

                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="<c:out value="${etudiant.email}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['email']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br />

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['confirmation']}</span>
                <br />

                <label for="nom">Nom de l'étudiant</label>
                <input type="text" id="nom" name="nom" value="<c:out value="${etudiant.nom}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['nom']}</span>
                <br />
                
                <label for="prenom">Prenom de l'étudiant</label>
                <input type="text" id="prenom" name="prenom" value="<c:out value="${etudiant.prenom}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['prenom']}</span>
                <br />
                
                <label for="nomPromotion">promotion de l'étudiant</label>
                <input type="text" id="nomPromotion" name="nomPromotion" value="<c:out value="${etudiant.nomPromotion}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['nomPromotion']}</span>
                <br />
                
                <label for="annee">annee de la promo</label>
                <input type="text" id="annee" name="annee" value="<c:out value="${etudiant.annee}"/>" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['annee']}</span>
                <br />
                
				<label for="admin">Admin ?</label>
				<input type="checkbox" id="admin" name="admin">
				<span class="erreur">${form.erreurs['isAdmin']}</span>
                <input type="submit" value="Inscription" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'success' : 'erreur'}">${form.result}</p>
            </fieldset>
        </form>
    </body>
</html>