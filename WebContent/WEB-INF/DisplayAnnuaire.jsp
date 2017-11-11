<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/search.js"></script>
<title>Annuaire</title>
</head>
<body>

<table style="width:100%">
 <tr>
    <th>Lastname</th>
    <th>Firstname</th>
    <th>Age</th>
    <th>Numéro de Téléphone</th>
    <th>Adresse</th>
    <th>Email</th>
    <th>Commentaire</th>
    <th>Actions</th>
  </tr>

${success != null ? "<div class=\"isa_success\"><i class=\"fa fa-check\"></i>".concat(success).concat("</div>") : '' }
${errors["email"] != null ? "<div class=\"isa_error\"><i class=\"fa fa-times-circle\"></i>".concat(errors["email"]).concat("</div>") : '' }

${ annuaire }




</table>

<a class="fakeButton" href="/annuaire/modify">Ajouter un contact</a>

<div id="searchFill">
</div>

<!--  TODO : Import Taglib -->

<form method="post" id="searchForm" action="<c:url value="/"/>">
  <img src="${pageContext.request.contextPath}/img/cross.png" alt="x" id="searchClose">
  <div>
    <input type="text" name="nom" placeholder="Nom">

    <input type="text" name="prenom" placeholder="Prénom">

    <input type="number" name="age" placeholder="Age">

    <input type="tel" name="telephone" placeholder="Numéro de Teléphone">

    <input type="text" name="adresse" placeholder="Adresse postale" >

    <input type="email" name="email" placeholder="Adresse Email">

    <textarea name = "comment" id = "comment" rows = "3" cols = "80" placeholder="Commentaires"></textarea>
    <input type="submit" value="Rechercher">
  </div>
</form>

<button id="searchButton">Faire une recherche</button>


</body>
</html>
