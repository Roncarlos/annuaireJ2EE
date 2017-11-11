<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" type="text/css" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>${ contact.getEmail().equals("") ? "Ajouter un contact" : "Modifier un contact" }</title>
</head>
<body>
	<h1>${ contact.getEmail().equals("") ? "Ajouter un contact" : "Modifier un contact" }</h1>
	${success != null ? "<div class=\"isa_success\"><i class=\"fa fa-check\"></i>".concat(success).concat("</div>") : '' }
	<form method="post">
  	  <fieldset>
    	<legend>Informations sur le contact</legend>
    	Nom:<br>
    	${errors["nom"] != null ? "<div class=\"isa_error\"><i class=\"fa fa-times-circle\"></i>".concat(errors["nom"]).concat("</div>") : '' }
    	<input type="text" name="nom" value="${ contact.getLastName() }" placeholder="De France"><br>
    	<br>
    	
    	Prénom:<br>
    	${errors["prenom"] != null ? "<div class=\"isa_error\"><i class=\"fa fa-times-circle\"></i>".concat(errors["prenom"]).concat("</div>") : '' }
    	<input type="text" name="prenom" value="${ contact.getFirstName() }" placeholder="Charles"><br>
    	<br>
    	
    	Age:<br>
    	<input type="number" name="age" value="${ contact.getAge() }" placeholder="45"><br><br>
    	
    	Téléphone:<br>
    	<input type="tel" name="telephone" value="${ contact.getTelNum() }" placeholder="+262 - 692 - 785 - 546"><br><br>
    	
    	Adresse:<br>
    	<input type="text" name="adresse" value="${ contact.getAdress() }" placeholder="22 chemin de l'arbre"><br><br>
    	
    	Email:<br>
    	${errors["email"] != null ? "<div class=\"isa_error\"><i class=\"fa fa-times-circle\"></i>".concat(errors["email"]).concat("</div>") : '' }
    	<input type="email" name="email" value="${ contact.getEmail() }" placeholder="email@email.com"
    	${contact.getEmail().equals("") ? "" : "disabled" }
    	><br>
    	<br>
    	
    	Commentaires:<br>
    	<textarea name = "comment" id = "comment" rows = "3" cols = "80">${ contact.getComment() }</textarea>
    	
    	
    	<br>
	  </fieldset>
	  <input type="submit" value="Submit">
	  <a class="fakeButton" href="/annuaire">Retour à l'annuaire</a>
	</form>

</body>
</html>